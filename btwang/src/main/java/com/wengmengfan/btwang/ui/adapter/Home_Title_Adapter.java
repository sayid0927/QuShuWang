package com.wengmengfan.btwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.bean.DownRaningBean;
import com.wengmengfan.btwang.bean.HomeInfoBean;
import com.wengmengfan.btwang.utils.ImgLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Home_Title_Adapter extends BaseQuickAdapter<HomeInfoBean, BaseViewHolder> {

    private Context mContext;
    private HomeInfoBean data;

    public Home_Title_Adapter(HomeInfoBean data, Context mContext) {
        super(R.layout.item_home_title);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeInfoBean item) {
        int tt = helper.getLayoutPosition();
        helper.setText(R.id.tv_title, data.getColTitleBean().get(helper.getLayoutPosition()).getTitle());

    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(DownRaningBean item);
    }

}
