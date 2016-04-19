package ren.solid.materialdesigndemo.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.adapter.base.SolidRVBaseAdapter;
import ren.solid.materialdesigndemo.utils.HttpUtils;

/**
 * Created by _SOLID
 * Date:2016/4/18
 * Time:17:36
 */
public abstract class BaseRecyclerViewFragment<T> extends BaseFragment {

    private static final int ACTION_REFRESH = 1;
    private static final int ACTION_LOAD_MORE = 2;

    private XRecyclerView mRecyclerView;
    private LinearLayout mLLReloadWarp;
    private Button mBtnReload;

    private SolidRVBaseAdapter<T> mAdapter;


    private int mCurrentAction = ACTION_REFRESH;
    private int mCurrentPageIndex = 1;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initView() {

        mLLReloadWarp = customFindViewById(R.id.ll_reload_wrap);
        mBtnReload = customFindViewById(R.id.btn_reload);

        mRecyclerView = customFindViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);

        //设置适配器
        mAdapter = setAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(ACTION_LOAD_MORE);
            }
        });

        mBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActionAndLoadData(ACTION_REFRESH);
                v.setVisibility(View.GONE);
                // mRecyclerView.setRefreshing(true);

            }
        });

    }


    @Override
    protected void initData() {
        mRecyclerView.setRefreshing(true);

    }

    private void loadData() {
        final String reqUrl = getUrl(mCurrentPageIndex);

        HttpUtils.getInstance().loadString(reqUrl, new HttpUtils.HttpCallBack() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onSuccess(String result) {
                mLLReloadWarp.setVisibility(View.GONE);
                List<T> list = parseData(result);
                mAdapter.addAll(list);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Snackbar.make(mRecyclerView, "数据转换异常:" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
//                }


                if (mCurrentAction == ACTION_REFRESH)
                    mRecyclerView.refreshComplete();
                if (mCurrentAction == ACTION_LOAD_MORE)
                    mRecyclerView.loadMoreComplete();
            }

            @Override
            public void onError(Exception e) {
                mLLReloadWarp.setVisibility(View.VISIBLE);
            }
        });
    }

    protected abstract List<T> parseData(String result);

    private void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case ACTION_REFRESH:
                mAdapter.clear();
                mCurrentPageIndex = 1;
                break;
            case ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                break;
        }
        loadData();
    }

    /**
     * 请求的URL
     *
     * @param mCurrentPageIndex
     * @return
     */
    protected abstract String getUrl(int mCurrentPageIndex);

    /**
     * 设置RecyclerView的适配器
     *
     * @return
     */
    protected abstract SolidRVBaseAdapter<T> setAdapter();

    /**
     * RecyclerView的布局方式
     *
     * @return
     */
    protected abstract RecyclerView.LayoutManager setLayoutManager();
}
