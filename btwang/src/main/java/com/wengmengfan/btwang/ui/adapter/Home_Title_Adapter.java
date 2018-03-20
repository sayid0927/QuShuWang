package com.wengmengfan.btwang.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.bean.HomeInfoBean;
import com.wengmengfan.btwang.bean.HomeSectionBean;
import com.wengmengfan.btwang.bean.HomeTitleItem;
import com.wengmengfan.btwang.utils.ImgLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Home_Title_Adapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    private Context mContext;
    private HomeInfoBean data;

    public Home_Title_Adapter(List<MultiItemEntity> data, Context mContext) {
        super(data);
        this.mContext = mContext;
        addItemType(TYPE_LEVEL_0, R.layout.item_text_type);
        addItemType(TYPE_LEVEL_1, R.layout.item_img_type);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final HomeTitleItem homeTitleItem = (HomeTitleItem) item;
                helper.setText(R.id.tv_title,homeTitleItem.getTitle());

                break;
            case TYPE_LEVEL_1:
                final HomeSectionBean homeSectionBean = (HomeSectionBean) item;
               ImageView iv = helper.getView(R.id.iv_home_item);
                ImgLoadUtils.GifloadImage(mContext,homeSectionBean.getImgUrl(),iv);

                break;
        }
    }
}
