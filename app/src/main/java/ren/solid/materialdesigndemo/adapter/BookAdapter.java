package ren.solid.materialdesigndemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.BookDetailActivity;
import ren.solid.materialdesigndemo.bean.BookBean;
import ren.solid.materialdesigndemo.utils.SolidHttpUtils;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:16:41
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<BookBean> mBookBeans;
    private Context mContext;

    public BookAdapter(Context context, List<BookBean> bookBeanList) {
        this.mContext = context;
        this.mBookBeans = bookBeanList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_book, parent, false);
        BookViewHolder holder = new BookViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        BookBean bookBean = mBookBeans.get(position);
        holder.tv_title.setText(bookBean.getTitle());
        holder.tv_price.setText("￥" + bookBean.getPrice());
        holder.tv_author.setText("作者:" + bookBean.getAuthor() + "");
        holder.tv_date.setText("出版日期:" + bookBean.getPubdate());
        holder.tv_publisher.setText("出版社:" + bookBean.getPublisher());
        holder.tv_num_rating.setText(bookBean.getRating().getNumRaters() + "人评分");

        SolidHttpUtils.getInstance().loadImage(bookBean.getImage(), holder.iv_image);


    }

    @Override
    public int getItemCount() {
        return mBookBeans.size();
    }

    public void add(BookBean bean) {
        mBookBeans.add(bean);
        notifyDataSetChanged();
    }

    public void addAll(List<BookBean> beans) {
        mBookBeans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clear() {
        mBookBeans.clear();
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_image;
        public TextView tv_title;
        public TextView tv_price;
        public TextView tv_author;
        public TextView tv_date;
        public TextView tv_publisher;
        public TextView tv_num_rating;

        public BookViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_publisher = (TextView) itemView.findViewById(R.id.tv_publisher);
            tv_num_rating = (TextView) itemView.findViewById(R.id.tv_num_rating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BookDetailActivity.class);
                    intent.putExtra("url", mBookBeans.get(getAdapterPosition() - 1).getUrl());
                    mContext.startActivity(intent);
                }
            });
        }


    }
}
