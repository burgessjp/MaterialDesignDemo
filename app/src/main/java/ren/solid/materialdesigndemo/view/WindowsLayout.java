package ren.solid.materialdesigndemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by _SOLID
 * Date:2016/4/27
 * Time:15:59
 */
public class WindowsLayout extends ViewGroup {

    private String TAG = "WindowsLayout";


    public WindowsLayout(Context context) {
        this(context, null);
    }

    public WindowsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WindowsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        //去测量子View的宽度的高度
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //设置ViewGroup的大小，如果长宽的mode是AT_MOST
        // 也就是在布局文件中写成了wrap_content,就直接将ViewGroup的大小设置为0
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : 0, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();//自定义ViewGroup的宽度
        int height = getMeasuredHeight();//自定义ViewGroup的高度
        int childCount = getChildCount();//子View的数量
        int row = 0, column = 0;//用来记录当前内容的行数和列数
        View bottomView = getChildAt(childCount - 1);//取得底部状态栏的View
        int contentHeight = height - bottomView.getMeasuredHeight();//内容高度
        bottomView.layout(0, height - bottomView.getMeasuredHeight(), width, height);//将底部状态栏放置到ViewGroup的最下面
        for (int i = 0; i < childCount - 1; i++) {
            View child = getChildAt(i);
            int ct, cr, cb, cl = column * child.getMeasuredWidth();
            int cWidth = child.getMeasuredWidth();
            int cHeight = child.getMeasuredHeight();
            ct = cHeight * row;
            cr = cl + cWidth;
            cb = ct + cHeight;
            if (cb > contentHeight) {
                column++;
                row = 0;
                i--;
            } else {
                row++;
                child.layout(cl, ct, cr, cb);
            }
        }
    }


}
