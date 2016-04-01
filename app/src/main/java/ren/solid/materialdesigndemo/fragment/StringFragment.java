package ren.solid.materialdesigndemo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:21:43
 */
public class StringFragment extends BaseFragment {
    private String mText;
    private TextView mTvText;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_string, container, false);
    }

    @Override
    protected void initView() {
        mText = getArguments().getString("text");
        mTvText = (TextView) getContentView().findViewById(R.id.tv_text);
        if (!mText.equals(""))
            mTvText.setText(mText);
        else
            mTvText.setText("暂无信息");
    }
}
