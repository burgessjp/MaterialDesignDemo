package ren.solid.materialdesigndemo.fragment;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.R;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:03
 */
public class AboutFragment extends BaseFragment {


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_about;
    }

    @Override
    protected void setUpView() {
        TextView tv_content = $(R.id.tv_content);
        tv_content.setAutoLinkMask(Linkify.ALL);
        tv_content.setMovementMethod(LinkMovementMethod
                .getInstance());
    }
}
