package com.wengmengfan.btwang.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.bean.DownRaningBean;
import com.wengmengfan.btwang.bean.HomeSectionBean;
import com.wengmengfan.btwang.bean.HomeTitleItem;
import com.wengmengfan.btwang.bean.VideoDetailsBean;
import com.wengmengfan.btwang.utils.ImgLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Home_Title_Play_Adapter extends BaseQuickAdapter<VideoDetailsBean.VideoLinks, BaseViewHolder> {

    private Context mContext;
    private List<VideoDetailsBean.VideoLinks> data;

    public Home_Title_Play_Adapter(List<VideoDetailsBean.VideoLinks> data, Context mContext) {
        super(R.layout.item_title_play, data);
        this.mContext = mContext;
        this.data = data;
    }


    @Override
    protected void convert(BaseViewHolder helper, final VideoDetailsBean.VideoLinks item) {

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_label, item.getLabel());
        Button buPlay = helper.getView(R.id.bu_play);
        Button buDown = helper.getView(R.id.bu_down);
        buPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayItemClickListener.OnPlayItemClickListener(item);
            }
        });

        buDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDownItemClickListener.OnDownItemClickListener(item);
            }
        });
    }


    private OnPlayItemClickListener onPlayItemClickListener;
    private OnDownItemClickListener onDownItemClickListener;

    public void setOnPlayItemClickListener(OnPlayItemClickListener onPlayItemClickListener) {
        this.onPlayItemClickListener = onPlayItemClickListener;
    }

    public interface OnPlayItemClickListener {
        void OnPlayItemClickListener(VideoDetailsBean.VideoLinks item);
    }

    public void setOnDownItemClickListener(OnDownItemClickListener onDownItemClickListener) {
        this.onDownItemClickListener = onDownItemClickListener;
    }

    public interface OnDownItemClickListener {
        void OnDownItemClickListener(VideoDetailsBean.VideoLinks item);
    }


}
