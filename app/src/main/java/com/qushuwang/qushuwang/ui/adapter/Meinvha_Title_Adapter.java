package com.qushuwang.qushuwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.bean.FenleiImgBean;
import com.qushuwang.qushuwang.bean.Meinvha_Title;
import com.qushuwang.qushuwang.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Meinvha_Title_Adapter extends BaseQuickAdapter< FenleiImgBean, BaseViewHolder> {

   private Context mContext;

    public Meinvha_Title_Adapter(List<FenleiImgBean> data,Context mContext) {
        super(R.layout.item_meinvha_title, data);
        this.mContext= mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final FenleiImgBean item) {
        ImageView iv = helper.getView(R.id.ivSubCateCover);

        GlideUtils.loadMovieTopImg(iv,item.getImgUrl());

        helper.setText(R.id.tvSubCateTitle,item.getBookName());
        helper.setText(R.id.tv_num,item.getBookNum());
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
        void onItemClickListener(FenleiImgBean item);
    }
}
