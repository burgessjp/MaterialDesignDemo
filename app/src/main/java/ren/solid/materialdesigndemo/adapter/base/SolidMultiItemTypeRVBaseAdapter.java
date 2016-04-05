package ren.solid.materialdesigndemo.adapter.base;

import android.content.Context;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/4/5
 * Time:17:36
 * <p/>
 * 支持多种ItemType的Adapter（适用于RecyclerView）
 */
public abstract class SolidMultiItemTypeRVBaseAdapter<T> extends SolidRVBaseAdapter<T> {

    private MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public SolidMultiItemTypeRVBaseAdapter(Context context, List<T> beans, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, beans);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return mMultiItemTypeSupport.getLayoutId(viewType);
    }

    @Override
    public int getItemCount() {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getViewTypeCount();
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position, mBeans.get(position));
        return super.getItemViewType(position);
    }

    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int viewType);

        int getViewTypeCount();

        int getItemViewType(int position, T t);
    }

}
