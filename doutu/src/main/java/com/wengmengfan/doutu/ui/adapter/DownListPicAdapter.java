package com.wengmengfan.doutu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.bean.NewestBean;
import com.wengmengfan.doutu.utils.ImgLoadUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class DownListPicAdapter extends BaseQuickAdapter<File, BaseViewHolder> {

    private Context mContext;
    private  String Type;

    public DownListPicAdapter(List<File> data, Context mContext ) {
        super(R.layout.item_downlist_pic, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final File item) {
        ImageView iv = helper.getView(R.id.ivSubCateCover);
        Glide.with(mContext).load(item).into(iv);
    }

    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(NewestBean item);
    }

}
