package com.qushuwang.qushuwang.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.bean.MhContentBean;
import com.qushuwang.qushuwang.utils.ImgLoadUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class MhContentAdapter extends BaseQuickAdapter<MhContentBean, BaseViewHolder> {

    private Context mContext;

    public MhContentAdapter(List<MhContentBean> data, Context mContext) {
        super(R.layout.item_mhcontent, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MhContentBean data) {

        ImageView iv = helper.getView(R.id.img_url);
        String imgUrl;
       switch (data.getType()){
           case "ManHuan":
               if (data.getDataSrc().equals("") || data.getDataSrc() == null) {
                   imgUrl = data.getImgSrc();
               } else {
                   imgUrl = data.getDataSrc();
               }
               ImgLoadUtils.loadImage(mContext,imgUrl,iv);
               break;

           case "TuPian":
               ImgLoadUtils.loadImage(mContext,data.getImgSrc(),iv);

               break;
       }


    }
}
