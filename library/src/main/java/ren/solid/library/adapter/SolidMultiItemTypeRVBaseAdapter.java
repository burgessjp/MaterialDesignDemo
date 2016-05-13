package ren.solid.library.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/4/5
 * Time:17:36
 * <p>
 * 支持多种ItemType的Adapter（适用于RecyclerView）
 */
public abstract class SolidMultiItemTypeRVBaseAdapter<T> extends SolidRVBaseAdapter<T> {


    public SolidMultiItemTypeRVBaseAdapter(Context context, List<T> beans) {
        super(context, beans);
    }

    @Override
    public abstract int getItemViewType(int position);


}
