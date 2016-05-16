package ren.solid.materialdesigndemo.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ren.solid.library.activity.ViewPicActivity;
import ren.solid.library.activity.WebViewActivity;
import ren.solid.library.adapter.SolidRVBaseAdapter;
import ren.solid.library.fragment.XRecyclerViewFragment;
import ren.solid.library.http.HttpClientManager;
import ren.solid.library.http.ImageLoader;
import ren.solid.library.http.request.ImageRequest;
import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.bean.GanHuoBean;
import ren.solid.materialdesigndemo.constants.Apis;
import ren.solid.library.utils.HttpUtils;

/**
 * Created by _SOLID
 * Date:2016/4/19
 * Time:10:57
 */
public class GanHuoListFragment extends XRecyclerViewFragment {

    private static String TAG = "GanHuoListFragment";
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
        String url = Apis.GanHuo + "/" + mType + "/10/" + mCurrentPageIndex;
        Log.i(TAG, url);
        return url;
    }

    @Override
    protected SolidRVBaseAdapter setAdapter() {
        return new SolidRVBaseAdapter<GanHuoBean>(getMContext(), new ArrayList<GanHuoBean>()) {

            @Override
            protected void onBindDataToView(SolidCommonViewHolder holder, GanHuoBean bean, int position) {
                holder.getView(R.id.tv_desc).setVisibility(View.GONE);
                holder.getView(R.id.iv_img).setVisibility(View.GONE);
                holder.getView(R.id.fl_head_date_wrap).setVisibility(View.GONE);

                if (position == 0) {
                    holder.getView(R.id.fl_head_date_wrap).setVisibility(View.VISIBLE);
                } else {
                    boolean isEqual = mBeans.get(position - 1).getPublishedAt().equals(mBeans.get(position).getPublishedAt());
                    if (!isEqual) {
                        holder.getView(R.id.fl_head_date_wrap).setVisibility(View.VISIBLE);
                    } else {
                        holder.getView(R.id.fl_head_date_wrap).setVisibility(View.GONE);
                    }
                }


                if (bean.getUrl().endsWith(".jpg")) {//if it's image
                    holder.getView(R.id.iv_img).setVisibility(View.VISIBLE);
                    ImageView imageView = holder.getView(R.id.iv_img);
                    HttpClientManager.displayImage(imageView, bean.getUrl());
                } else {
                    holder.getView(R.id.tv_desc).setVisibility(View.VISIBLE);
                    holder.setText(R.id.tv_desc, bean.getDesc());
                }

                holder.setText(R.id.tv_head_date, bean.getPublishedAt());
                holder.setText(R.id.tv_source, bean.getSource());
                holder.setText(R.id.tv_people, bean.getWho());
                holder.setText(R.id.tv_time, bean.getPublishedAt().substring(0, 10));
                holder.setText(R.id.tv_tag, bean.getType());
            }

            @Override
            public int getItemLayoutID(int viewType) {
                return R.layout.item_rv_ganhuo;
            }

            @Override
            protected void onItemClick(int position) {

                String url = mBeans.get(position - 1).getUrl();
                ArrayList<String> images = new ArrayList<String>();
                images.add(url);
                if (!url.endsWith(".jpg")) {
                    Intent intent = new Intent(getMContext(), WebViewActivity.class);
                    intent.putExtra(WebViewActivity.WEB_URL, url);
                    intent.putExtra(WebViewActivity.TITLE, mBeans.get(position - 1).getDesc());
                    getMContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(getMContext(), ViewPicActivity.class);
                    intent.putStringArrayListExtra(ViewPicActivity.IMG_URLS, images);
                    getMContext().startActivity(intent);
                }
            }
        };
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(getMContext());
    }
}
