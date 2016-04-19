package ren.solid.materialdesigndemo.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.base.WebViewActivity;
import ren.solid.materialdesigndemo.adapter.base.SolidRVBaseAdapter;
import ren.solid.materialdesigndemo.bean.GanHuoBean;
import ren.solid.materialdesigndemo.constants.Apis;
import ren.solid.materialdesigndemo.fragment.base.BaseRecyclerViewFragment;
import ren.solid.materialdesigndemo.utils.HttpUtils;

/**
 * Created by _SOLID
 * Date:2016/4/19
 * Time:10:57
 */
public class GanHuoListFragment extends BaseRecyclerViewFragment {

    private String mType;

    @Override
    protected List parseData(String result) {

        List<GanHuoBean> list = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            Gson gson = new Gson();
            list = gson.fromJson(
                    jsonObject.getString("results"),
                    new TypeToken<List<GanHuoBean>>() {
                    }.getType());
        } catch (JSONException e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
        return list;

    }

    @Override
    protected String getUrl(int mCurrentPageIndex) {
        mType = getArguments().getString("type");
        return Apis.GanHuo + "/" + mType + "/10/" + mCurrentPageIndex;
    }

    @Override
    protected SolidRVBaseAdapter setAdapter() {
        return new SolidRVBaseAdapter<GanHuoBean>(getMContext(), new ArrayList<GanHuoBean>()) {

            @Override
            protected void onBindDataToView(SolidCommonViewHolder holder, GanHuoBean bean) {

                holder.getView(R.id.tv_desc).setVisibility(View.GONE);
                holder.getView(R.id.iv_img).setVisibility(View.GONE);
                if (mType.equals("福利")) {
                    holder.getView(R.id.iv_img).setVisibility(View.VISIBLE);
                    ImageView imageView = holder.getView(R.id.iv_img);
                    HttpUtils.getInstance().loadImage(bean.getUrl(), imageView);
                } else {
                    holder.getView(R.id.tv_desc).setVisibility(View.VISIBLE);
                    holder.setText(R.id.tv_desc, bean.getDesc());
                }


                holder.setText(R.id.tv_source, bean.getSource());
                holder.setText(R.id.tv_people, bean.getWho());
                holder.setText(R.id.tv_time, bean.getPublishedAt().substring(0,10));
            }

            @Override
            public int getItemLayoutID(int viewType) {
                return R.layout.item_rv_ganhuo;
            }

            @Override
            protected void onItemClick(int position) {
                Intent intent = new Intent(getMContext(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL, mBeans.get(position - 1).getUrl());
                getMContext().startActivity(intent);
            }
        };
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(getMContext());
    }
}
