package ren.solid.materialdesigndemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListenerV1;

import java.io.File;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.base.ContentActivity;
import ren.solid.materialdesigndemo.fragment.base.ViewPicFragment;
import ren.solid.materialdesigndemo.utils.FileUtils;
import ren.solid.materialdesigndemo.utils.HttpUtils;
import ren.solid.materialdesigndemo.utils.SystemShareUtils;

/**
 * Created by _SOLID
 * Date:2016/4/22
 * Time:14:28
 * <p/>
 * view full picture
 */
public class ViewPicActivity extends ContentActivity {

    private static String TAG = "ViewPicActivity";
    public final static String IMG_URL = "ViewPicActivity";
    private String mUrl = "";
    private String mSavePath;

    @Override
    protected void init() {
        super.init();
        mUrl = getIntent().getExtras().getString(IMG_URL);
    }

    @Override
    protected Fragment setFragment() {
        ViewPicFragment f = new ViewPicFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMG_URL, mUrl);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_viewpic_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            downloadPicture(0);
            return true;
        } else if (id == R.id.action_share) {
            downloadPicture(1);
        }

        return super.onOptionsItemSelected(item);
    }

    private void downloadPicture(final int action) {
        mSavePath = FileUtils.getSaveImagePath(this) + File.separator + FileUtils.getFileName(mUrl);
        Log.i(TAG, mSavePath);
        HttpUtils.downloadFile(mUrl, mSavePath, new DownloadStatusListenerV1() {
            @Override
            public void onDownloadComplete(DownloadRequest downloadRequest) {
                if (action == 0) {
                    Snackbar.make(mToolbar, "已保存至:" + mSavePath, Snackbar.LENGTH_SHORT).show();
                } else {
                    SystemShareUtils.shareImage(ViewPicActivity.this, Uri.parse(mSavePath));
                }
            }

            @Override
            public void onDownloadFailed(DownloadRequest downloadRequest, int errorCode, String errorMessage) {
                if (action == 0)
                    Snackbar.make(mToolbar, "保存失败:" + errorMessage, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(DownloadRequest downloadRequest, long totalBytes, long downloadedBytes, int progress) {

            }
        });
    }


}
