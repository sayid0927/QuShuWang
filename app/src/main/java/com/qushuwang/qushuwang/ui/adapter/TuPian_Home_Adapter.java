package com.qushuwang.qushuwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
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
    private  String Type;

    public TuPian_Home_Adapter(List<TuPianHomeBean> data, Context mContext,String Type) {
        super(R.layout.item_meinvha_title, data);
        this.mContext = mContext;
        this.Type = Type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TuPianHomeBean item) {

        ImageView iv = helper.getView(R.id.ivSubCateCover);
        helper.getView(R.id.tv_num).setVisibility(View.GONE);
        helper.setText(R.id.tvSubCateTitle, item.getTitle());

        String hh = item.getImgUrl();
        Logger.e("TuPian_Home  >>>   " + "Title >>>  "+item.getTitle() +"\n"+
                "TuPian_Home  >>>   " + "Url >>>  "+item.getImgUrl() );

        switch (Type){
            case "DongTu":
               ImgLoadUtils.GifloadImage(mContext, item.getImgUrl(), iv);
                break;

            case "TuPian":
                ImgLoadUtils.loadImage(mContext, item.getImgUrl(), iv);
                break;

        }

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
        void onItemClickListener(TuPianHomeBean item);
    }

}
