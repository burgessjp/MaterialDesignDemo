package ren.solid.materialdesigndemo.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.adapter.BookAdapter;
import ren.solid.materialdesigndemo.bean.BookBean;
import ren.solid.materialdesigndemo.constants.Apis;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.utils.SolidHttpUtils;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:54
 */
public class BookFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "BookFragmentTAG";
    private static final int ACTION_INIT = 0;
    private static final int ACTION_REFLESH = 1;
    private static final int ACTION_LOAD_MORE = 2;


    private XRecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private FloatingActionButton mFABSearch;
    private EditText mETInput;
    private AlertDialog mInputDialog;

    private int mCurrentAction = ACTION_INIT;
    private String mCurrentKeyWord;
    private int mPageSize = 20;
    private int mCurrentPageIndex = 1;


    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    protected void initView() {
        mFABSearch = (FloatingActionButton) getContentView().findViewById(R.id.fab_search);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (XRecyclerView) getContentView().findViewById(R.id.recyclerview);
        mBookAdapter = new BookAdapter(getMContext(), new ArrayList<BookBean>());
        mRecyclerView.setAdapter(mBookAdapter);
        mRecyclerView.setLayoutManager(LayoutManager);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchAction(ACTION_REFLESH);
            }

            @Override
            public void onLoadMore() {
                switchAction(ACTION_LOAD_MORE);
            }
        });

        mFABSearch.setOnClickListener(this);
        initInputDialog();
    }

    @Override
    protected void initData() {
        String[] keyWords = {"Android", "文艺青年", "科技", ".NET", "创业之路"};
        Random random = new Random();
        int n = random.nextInt(keyWords.length);
        mCurrentKeyWord = keyWords[n];
        switchAction(ACTION_INIT);

    }

    private void getData() {
        String reqUrl = Apis.SearchBookApi + "?q=" + mCurrentKeyWord + "&start=" + (mCurrentPageIndex - 1) * mPageSize +
                "&count=" + mPageSize;
        SolidHttpUtils.getInstance().loadString(reqUrl, new SolidHttpUtils.HttpCallBack() {
            @Override
            public void onLoading() {
                if (mCurrentAction == ACTION_INIT)
                    getProgressDialog().show();
            }

            @Override
            public void onSuccess(String result) {
                getProgressDialog().hide();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Gson gson = new Gson();
                    List<BookBean> list = gson.fromJson(
                            jsonObject.getString("books"),
                            new TypeToken<List<BookBean>>() {
                            }.getType());

                    mBookAdapter.addAll(list);
                    mRecyclerView.refreshComplete();
                    if (mCurrentAction == ACTION_LOAD_MORE)
                        mRecyclerView.loadMoreComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                getProgressDialog().hide();
                System.out.println("onError:" + e);
            }
        });
    }

    private void switchAction(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case ACTION_INIT:
                mBookAdapter.clear();
                mCurrentPageIndex = 1;
                break;
            case ACTION_REFLESH:
                mBookAdapter.clear();
                mCurrentPageIndex = 1;
                break;
            case ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                break;
        }
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_search:
                mInputDialog.show();
                break;
            default:
                break;
        }
    }

    /***
     * 初始化输入对框框
     */
    private void initInputDialog() {
        mETInput = new EditText(getMContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getMContext());
        builder.setTitle("请输入关键字");
        builder.setView(mETInput);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mCurrentKeyWord = mETInput.getText().toString();
                if ("".equals(mCurrentKeyWord)) {//如果用户输入的关键字为空，我们就按照最开始的数据加载方式加载
                    initData();
                } else {
                    switchAction(ACTION_INIT);
                }
                mETInput.setText("");

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mETInput.setText("");
            }
        });
        mInputDialog = builder.create();

    }

}
