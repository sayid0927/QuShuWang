package com.qushuwang.qushuwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.bean.FenleiImgBean;
import com.qushuwang.qushuwang.bean.TuPianHomeBean;
import com.qushuwang.qushuwang.ui.fragment.TuPian_Title;
import com.qushuwang.qushuwang.utils.ImgLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class TuPian_Home_Adapter extends BaseQuickAdapter<TuPianHomeBean, BaseViewHolder> {

   private Context mContext;

    public TuPian_Home_Adapter(List<TuPianHomeBean> data, Context mContext) {
        super(R.layout.item_meinvha_title, data);
        this.mContext= mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TuPianHomeBean item) {
        ImageView iv = helper.getView(R.id.ivSubCateCover);
        ImgLoadUtils.loadImage(mContext, item.getImgUrl(),iv);

//        helper.setText(R.id.tvSubCateTitle,item.getBookName());
//        helper.setText(R.id.tv_num,item.getBookNum());
//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item);
//            }
//        });

    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(FenleiImgBean item);
    }

}
