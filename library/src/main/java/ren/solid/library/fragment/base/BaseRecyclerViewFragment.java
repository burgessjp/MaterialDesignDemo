package ren.solid.library.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.File;
import java.util.List;

import ren.solid.library.R;
import ren.solid.library.adapter.base.SolidRVBaseAdapter;
import ren.solid.library.utils.FileUtils;
import ren.solid.library.utils.HttpUtils;
import ren.solid.library.utils.NetworkUtils;
import ren.solid.library.utils.StringUtils;
import ren.solid.library.utils.ToastUtils;


/**
 * Created by _SOLID
 * Date:2016/4/18
 * Time:17:36
 * <p>
 * common fragment for list data display ,and you can extends this fragment for everywhere you want to display list data
 */
public abstract class BaseRecyclerViewFragment<T> extends BaseFragment {

    private static String TAG = "BaseRecyclerViewFragment";

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
                mLLReloadWarp.setVisibility(View.GONE);
                mRecyclerView.setRefreshing(true);
            }
        });
    }


    @Override
    protected void initData() {
        mRecyclerView.setRefreshing(true);
    }

    private void loadData() {
        final String reqUrl = getUrl(mCurrentPageIndex);
        if (!NetworkUtils.isNetworkConnected(getMContext())) {//no network
            String result = obtainOfflineData(getUrl(1));
            onDataSuccessReceived(result);
            ToastUtils.getInstance().showToast("当前无网络连接");
        } else
            HttpUtils.getInstance().loadString(reqUrl, new HttpUtils.HttpCallBack() {
                @Override
                public void onLoading() {
                    Log.i(TAG, "onLoading");
                }

                @Override
                public void onSuccess(String result) {
                    if (mCurrentAction == ACTION_REFRESH) {//store the first page data
                        storeOfflineData(getUrl(1), result);
                    }
                    onDataSuccessReceived(result);
                }

                @Override
                public void onError(Exception e) {
                    onDataErrorReceived();
                }
            });
    }

    private void onDataErrorReceived() {
        Log.i(TAG, "onDataErrorReceived");
        mLLReloadWarp.setVisibility(View.VISIBLE);
        loadComplete();
    }

    private void onDataSuccessReceived(String result) {
        Log.i(TAG, "onDataSuccessReceived:" + result);
        if (!StringUtils.isNullOrEmpty(result)) {
            List<T> list = parseData(result);
            mAdapter.addAll(list);
            loadComplete();
            mLLReloadWarp.setVisibility(View.GONE);
        } else {
            onDataErrorReceived();
        }
    }

    private void loadComplete() {
        if (mCurrentAction == ACTION_REFRESH)
            mRecyclerView.refreshComplete();
        if (mCurrentAction == ACTION_LOAD_MORE)
            mRecyclerView.loadMoreComplete();
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
     * store offline data
     *
     * @param url
     * @param result
     */
    public void storeOfflineData(String url, String result) {
        try {
            FileUtils.writeFile(getOfflineDir(url), result, "UTF-8", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * obtain offline data
     *
     * @param url
     * @return
     */
    public String obtainOfflineData(String url) {
        String result = null;
        try {
            result = FileUtils.readFile(getOfflineDir(url), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * get the dir of the offline data
     *
     * @param url
     * @return
     */
    protected String getOfflineDir(String url) {
        return FileUtils.getCacheDir(getMContext()) + File.separator + "offline_gan_huo_cache" + File.separator + StringUtils.md5(url);
    }

    /**
     * the url of request
     *
     * @param mCurrentPageIndex
     * @return
     */
    protected abstract String getUrl(int mCurrentPageIndex);

    /**
     * set RecyclerView's Adapter
     *
     * @return
     */
    protected abstract SolidRVBaseAdapter<T> setAdapter();

    /**
     * set RecyclerView's LayoutManager
     *
     * @return
     */
    protected abstract RecyclerView.LayoutManager setLayoutManager();
}
