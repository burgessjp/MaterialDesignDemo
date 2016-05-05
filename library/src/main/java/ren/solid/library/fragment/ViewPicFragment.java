package ren.solid.library.fragment;

import ren.solid.library.R;
import ren.solid.library.activity.ViewPicActivity;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.utils.HttpUtils;
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
