package ren.solid.library.fragment.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ren.solid.skinloader.base.SkinBaseFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:30
 */
public abstract class BaseFragment extends SkinBaseFragment {

    private View mContentView;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);//setContentView(inflater, container);
        mContext = getContext();
        mProgressDialog = new ProgressDialog(getMContext());
        mProgressDialog.setCanceledOnTouchOutside(false);
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    protected abstract int setLayoutResourceID();

    protected void setUpData() {

    }

    protected void init() {

    }

    protected void setUpView() {
    }

    protected <T extends View> T $(int id) {
        return (T) mContentView.findViewById(id);
    }

    // protected abstract View setContentView(LayoutInflater inflater, ViewGroup container);

    protected View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    protected ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }
}
