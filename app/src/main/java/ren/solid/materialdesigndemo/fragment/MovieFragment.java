package ren.solid.materialdesigndemo.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.SettingActivity;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:54
 */
public class MovieFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {
        super.initView();
        customFindViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getMContext(), SettingActivity.class));
            }
        });
    }
}
