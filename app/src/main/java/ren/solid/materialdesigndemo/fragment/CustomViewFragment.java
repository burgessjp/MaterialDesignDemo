package ren.solid.materialdesigndemo.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.view.WindowsLayout;

/**
 * Created by _SOLID
 * Date:2016/4/10
 * Time:12:54
 */
public class CustomViewFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_custom_view;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
//        QQHealthView qqHealthView = $(R.id.qqhealthview);
//        qqHealthView.setSteps(new int[]{5025, 15280, 8900, 9200, 6500, 5660, 9450});
        // qqHealthView.setThemeColor(Color.parseColor("#87CEEB"));

//        final WindowsLayout windowsLayout = $(R.id.windows_layout);
//
//        LinearLayout ll_bottom = $(R.id.ll_bottom);
//        ll_bottom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int[] location = new int[2];
//                v.getLocationOnScreen(location);
//
//                View popupView = LayoutInflater.from(getMContext()).inflate(R.layout.pop_windows, null);
//                PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT, true);
//                popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e0000000")));
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.setFocusable(true);
//                int height=popupView.getMeasuredHeight();
//                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]-height);
//            }
//        });
        


    }
}
