package com.ManHuan.manhuan.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseFragment;
import com.ManHuan.manhuan.base.BaseFragmentPageAdapter;
import com.ManHuan.manhuan.base.Constant;
import com.ManHuan.manhuan.bean.DongTuHomeBean;
import com.ManHuan.manhuan.component.AppComponent;
import com.ManHuan.manhuan.component.DaggerMainComponent;
import com.ManHuan.manhuan.presenter.contract.DongTuHomeContract;
import com.ManHuan.manhuan.presenter.impl.DongTuHomePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class DongTuHomeFragment extends BaseFragment  implements DongTuHomeContract.View {



    @Inject
    DongTuHomePresenter mPresenter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;


    private BaseFragmentPageAdapter myAdapter;
    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    public void loadData() {
//        mPresenter.DongTu_Title(DongTuHomeUrl);
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    protected void initView(Bundle bundle) {

        mTitleList.add("邪恶动态图");
        mTitleList.add("邪恶图片");
        mTitleList.add("美女图片");

        mFragments.add(DongTu_Title.newInstance("http://www.yaoqmhw.net/xedtt/",4));
        mFragments.add(DongTu_Title.newInstance("http://www.yaoqmhw.net/xetp/",5));
        mFragments.add(DongTu_Title.newInstance("http://www.yaoqmhw.net/mntp/",6));

        myAdapter = new BaseFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitleList);

        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_wo_home;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void DongTu_Title_Success(List<DongTuHomeBean> data) {
//        if (data != null && data.size() != 0) {
//            for (int i = 0; i < data.size(); i++) {
//                mTitleList.add(data.get(i).getTitle());
//                String subUrl = data.get(i).getUrl();
//                subUrl = StringUtlis.subString(subUrl, "/");
//                subUrl = StringUtlis.subString(subUrl, "/");
//                mFragments.add(DongTu_Title.newInstance(DongTuHomeUrl+subUrl));
//
//            }
//        }

//        myAdapter = new BaseFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitleList);
//        vp.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
//        tabLayout.setupWithViewPager(vp);
    }

}
