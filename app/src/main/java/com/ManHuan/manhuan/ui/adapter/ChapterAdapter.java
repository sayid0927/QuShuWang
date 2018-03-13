package com.ManHuan.manhuan.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.bean.BookInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class ChapterAdapter extends BaseQuickAdapter<BookInfoBean.BookChapterBean, BaseViewHolder> {

    private Context mContext;

    public ChapterAdapter(List<BookInfoBean.BookChapterBean> data, Context mContext) {
        super(R.layout.item_chapter, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final BookInfoBean.BookChapterBean item) {

        helper.setText(R.id.bu_chapter, item.getNum());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(item);
            }
        });

    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(BookInfoBean.BookChapterBean item);
    }
}
