package com.wengmengfan.btwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.bean.DownVideoBean;
import com.wengmengfan.btwang.bean.VideoDetailsBean;
import com.wengmengfan.btwang.utils.ImgLoadUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class DownListApadter extends BaseQuickAdapter<DownVideoBean, BaseViewHolder> {

    private Context mContext;
    private List<DownVideoBean> data;

    public DownListApadter(List<DownVideoBean> data, Context mContext) {
        super(R.layout.item_down_list, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final DownVideoBean item) {
        ImageView iv = helper.getView(R.id.iv_down);
        Button but = helper.getView(R.id.bu_delete);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 onDeleteItemListenter.OnDeleteItemListenter(item);
            }
        });
        ImgLoadUtils.GifloadImage(mContext, item.getPlayimgUrl(), iv);
        helper.setText(R.id.down_title, item.getPlayTitle());

    }

    private  OnDeleteItemListenter onDeleteItemListenter;

    public void OnDeleteItemListenter(OnDeleteItemListenter onDeleteItemListenter) {
        this.onDeleteItemListenter = onDeleteItemListenter;
    }

    public interface OnDeleteItemListenter{
        void OnDeleteItemListenter(DownVideoBean item);
    }
}
