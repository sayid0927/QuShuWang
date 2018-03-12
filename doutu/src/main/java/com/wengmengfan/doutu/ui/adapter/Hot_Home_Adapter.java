package com.wengmengfan.doutu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.bean.NewestBean;
import com.wengmengfan.doutu.utils.ImgLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Hot_Home_Adapter extends BaseQuickAdapter<NewestBean, BaseViewHolder> {

    private Context mContext;
    private  String Type;

    public Hot_Home_Adapter(List<NewestBean> data, Context mContext ) {
        super(R.layout.item_newest_title, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final NewestBean item) {

        ImageView iv = helper.getView(R.id.ivSubCateCover);
        Button button =  helper.getView(R.id.but_down);
        helper.setText(R.id.tvSubCateTitle, item.getImgTitle());
        ImgLoadUtils.GifloadImage(mContext, item.getImgUrl(), iv);

        button.setOnClickListener(new View.OnClickListener() {
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
        void onItemClickListener(NewestBean item);
    }

}
