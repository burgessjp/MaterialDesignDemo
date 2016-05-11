package ren.solid.materialdesigndemo.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.R;
import ren.solid.library.utils.FileUtils;
import ren.solid.skinloader.listener.ILoaderListener;
import ren.solid.skinloader.load.SkinManager;

/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:44
 */
public class ChangeSkinFragment extends BaseFragment {

    private static String TAG = "ChangeSkinFragment";

    private static String SKIN_BROWN_NAME = "skin_brown.skin";
    private static String SKIN_BLACK_NAME = "skin_black.skin";
    private static String SKIN_DIR;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_chang_skin;
    }

    @Override
    protected void init() {
        SKIN_DIR = FileUtils.getSkinDirPath(getMContext());
    }

    @Override
    protected void setUpView() {
        $(R.id.ll_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().restoreDefaultTheme();

            }
        });
        $(R.id.ll_brown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skinFullName = SKIN_DIR + File.separator + "skin_brown.skin";
                FileUtils.moveRawToDir(getMContext(), "skin_brown.skin", skinFullName);
                File skin = new File(skinFullName);
                if (!skin.exists()) {
                    Toast.makeText(getMContext(), "请检查" + skinFullName + "是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                SkinManager.getInstance().load(skin.getAbsolutePath(),
                        new ILoaderListener() {
                            @Override
                            public void onStart() {
                                Log.e(TAG, "loadSkinStart");
                            }

                            @Override
                            public void onSuccess() {
                                Log.i(TAG, "loadSkinSuccess");
                                Toast.makeText(getMContext(), "切换成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed() {
                                Log.i(TAG, "loadSkinFail");
                                Toast.makeText(getMContext(), "切换失败", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        $(R.id.ll_black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skinFullName = SKIN_DIR + File.separator + "skin_black.skin";
                FileUtils.moveRawToDir(getMContext(), "skin_black.skin", skinFullName);
                File skin = new File(skinFullName);
                if (!skin.exists()) {
                    Toast.makeText(getMContext(), "请检查" + skinFullName + "是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                SkinManager.getInstance().load(skin.getAbsolutePath(),
                        new ILoaderListener() {
                            @Override
                            public void onStart() {
                                Log.e(TAG, "loadSkinStart");
                            }

                            @Override
                            public void onSuccess() {
                                Log.e(TAG, "loadSkinSuccess");
                                Toast.makeText(getMContext(), "切换成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed() {
                                Log.e(TAG, "loadSkinFail");
                                Toast.makeText(getMContext(), "切换失败", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}
