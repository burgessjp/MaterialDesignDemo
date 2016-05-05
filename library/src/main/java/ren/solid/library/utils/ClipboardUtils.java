package ren.solid.library.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by _SOLID
 * Date:2016/4/22
 * Time:11:45
 */
public class ClipboardUtils {
    private static ClipboardManager mClipboardManager;
    private static android.content.ClipboardManager mNewCliboardManager;


    private static boolean isNew() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    private static void instance(Context context) {
        if (isNew()) {
            if (mNewCliboardManager == null)
                mNewCliboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        } else {
            if (mClipboardManager == null)
                mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
    }

    /**
     * 为剪切板设置内容
     *
     * @param context
     * @param text
     */
    public static void setText(Context context, CharSequence text) {
        if (isNew()) {
            instance(context);
            // Creates a new text clip to put on the clipboard
            ClipData clip = ClipData.newPlainText("simple text", text);

            // Set the clipboard's primary clip.
            mNewCliboardManager.setPrimaryClip(clip);
        } else {
            instance(context);
            mClipboardManager.setText(text);
        }
    }

    /**
     * 获取剪切板的内容
     *
     * @param context
     * @return
     */
    public static CharSequence getText(Context context) {
        StringBuilder sb = new StringBuilder();
        if (isNew()) {
            instance(context);
            if (!mNewCliboardManager.hasPrimaryClip()) {
                return sb.toString();
            } else {
                ClipData clipData = (mNewCliboardManager).getPrimaryClip();
                int count = clipData.getItemCount();

                for (int i = 0; i < count; ++i) {

                    ClipData.Item item = clipData.getItemAt(i);
                    CharSequence str = item.coerceToText(context);
                    sb.append(str);
                }
            }

        } else {
            instance(context);
            sb.append(mClipboardManager.getText());
        }
        return sb.toString();
    }
}
