package ren.solid.skinloader.attr;

import android.graphics.drawable.Drawable;
import android.view.View;

import ren.solid.skinloader.load.SkinManager;
import ren.solid.skinloader.util.L;

/**
 * Created by _SOLID
 * Date:2016/4/13
 * Time:21:46
 */
public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view) {

        if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
            view.setBackgroundColor(SkinManager.getInstance().getColor(attrValueRefId));
            L.i("applyAttr", "—————————————————————————————————————————————————————————");
            L.i("applyAttr", "apply as color");
            L.i("applyAttr", "—————————————————————————————————————————————————————————");
        } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
            Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
            // view.setBackground(bg);
            view.setBackgroundDrawable(bg);
            L.i("applyAttr", "—————————————————————————————————————————————————————————");
            L.i("applyAttr", "apply as drawable");
            L.i("applyAttr", "bg.toString()  " + bg.toString());
            L.i("applyAttr", this.attrValueRefName + " 是否可变换状态? : " + bg.isStateful());
            L.i("applyAttr", "—————————————————————————————————————————————————————————");
        }
    }
}
