package com.qushuwang.qushuwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.bean.BookInfoBean;
import com.qushuwang.qushuwang.bean.MhContentBean;
import com.qushuwang.qushuwang.utils.GlideUtils;

import java.util.ArrayList;
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
        if (data.getDataSrc().equals("") || data.getDataSrc() == null) {
            imgUrl = data.getImgSrc();
        } else {
            imgUrl = data.getDataSrc();
        }
        GlideUtils.loadMovieTopImg(iv,imgUrl);
    }
}
