package ren.solid.library.fragment;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;

import ren.solid.library.R;
import ren.solid.library.activity.ViewPicActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.http.HttpHelper;
import ren.solid.library.http.ImageLoader;
import ren.solid.library.http.callback.adapter.FileHttpCallBack;
import ren.solid.library.http.callback.adapter.StringHttpCallBack;
import ren.solid.library.http.request.ImageRequest;
import ren.solid.library.utils.FileUtils;
import ren.solid.library.utils.Logger;
import ren.solid.library.utils.SnackBarUtils;
import ren.solid.library.utils.SystemShareUtils;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by _SOLID
 * Date:2016/4/22
 * Time:14:30
 */
public class ViewPicFragment extends BaseFragment {

    private ViewPager mViewPager;

    private ArrayList<String> mUrlList;

    private String mSavePath;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_view_pic;
    }

    @Override
    protected void init() {
        mUrlList = getArguments().getStringArrayList(ViewPicActivity.IMG_URLS);
    }

    @Override
    protected void setUpView() {
        mViewPager = $(R.id.viewpager);
        mViewPager.setAdapter(new MyViewPager());

    }

    private class MyViewPager extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            PhotoView photoView = new PhotoView(getMContext());

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            photoView.setLayoutParams(layoutParams);

            //setUpPhotoViewAttacher(photoView);

            ImageRequest imageRequest = new ImageRequest.Builder().imgView(photoView).url(mUrlList.get(position)).create();
            ImageLoader.getProvider().loadImage(imageRequest);

            container.addView(photoView);

            return photoView;
        }

        private void setUpPhotoViewAttacher(PhotoView photoView) {
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photoView);
            photoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float v, float v1) {
                    ViewPicActivity activity = (ViewPicActivity) getMContext();
                    activity.hideOrShowToolbar();
                }
            });
        }

        @Override
        public int getCount() {
            return mUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * download image
     *
     * @param action 0:save 1:share
     */
    public void downloadPicture(final int action) {
        mSavePath = FileUtils.getSaveImagePath(getMContext()) + File.separator + FileUtils.getFileName(mUrlList.get(0));
        Logger.i(this, mSavePath);

        HttpHelper.getProvider().download(mUrlList.get(0), mSavePath, new FileHttpCallBack() {
            @Override
            public void onSuccess(String filePath) {
                if (action == 0) {
                    SnackBarUtils.makeLong(mViewPager, "已保存至:" + filePath).warning();
                } else {
                    SystemShareUtils.shareImage(getMContext(), Uri.parse(filePath));
                }
            }

            @Override
            public void onProgress(long totalBytes, long downloadedBytes, int progress) {
                Logger.i(this, "totalBytes:" + totalBytes + " downloadedBytes:" + downloadedBytes + " progress:" + progress);
            }

            @Override
            public void onError(Exception e) {
                if (action == 0)
                    SnackBarUtils.makeLong(mViewPager, "保存失败:" + e.getMessage()).danger();
            }
        });
    }
}
