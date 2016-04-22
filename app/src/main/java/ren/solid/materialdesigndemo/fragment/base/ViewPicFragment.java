package ren.solid.materialdesigndemo.fragment.base;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.ViewPicActivity;
import ren.solid.materialdesigndemo.utils.HttpUtils;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by _SOLID
 * Date:2016/4/22
 * Time:14:30
 */
public class ViewPicFragment extends BaseFragment {

    private PhotoView mPhotoView;
    private String mUrl;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_view_pic;
    }

    @Override
    protected void init() {
        mUrl = getArguments().getString(ViewPicActivity.IMG_URL);
    }

    @Override
    protected void initView() {
        mPhotoView = customFindViewById(R.id.photo_view);
        HttpUtils.getInstance().loadImage(mUrl, mPhotoView);
    }
}
