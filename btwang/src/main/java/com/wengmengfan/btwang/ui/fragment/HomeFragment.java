package com.wengmengfan.btwang.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseFragment;
import com.wengmengfan.btwang.base.Constant;
import com.wengmengfan.btwang.bean.DownRaningBean;
import com.wengmengfan.btwang.bean.HomeInfoBean;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.HomeContract;
import com.wengmengfan.btwang.presenter.impl.HomeFragmentPresenter;
import com.wengmengfan.btwang.ui.activity.ViewBoxActivity;
import com.wengmengfan.btwang.ui.adapter.DownRanking_Adapter;
import com.wengmengfan.btwang.ui.adapter.Home_Title_Adapter;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsFilmFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsMangaFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsTeleplayFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsVarietyFragment;
import com.wengmengfan.btwang.view.MyLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * sayid ....
 * Created by wengmf on 2018/3/12.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    HomeFragmentPresenter mPresenter;

    @BindView(R.id.tl_4)
    SegmentTabLayout tabLayout;

    @BindView(R.id.vp_2)
    ViewPager vp;

    @BindView(R.id.rv_info)
    RecyclerView rvInfo;

    private Home_Title_Adapter mAdapter;

    private static final String sttBaseUrl = "https://www.80s.tt/";

    private String[] mTitles = {"电影", "电视剧", "综艺", "动漫"};

    private ArrayList<Fragment> mFragments= new ArrayList<>();

    @Override
    protected void initView(Bundle bundle) {

        mAdapter = new Home_Title_Adapter(null, getActivity());
        rvInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInfo.setAdapter(mAdapter);

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
        mPresenter.Fetch_80sHomeInfo(sttBaseUrl);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_hotfilm;
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
        showDialog(message);
    }

    @Override
    public void Fetch_80sHomeInfo_Success(HomeInfoBean data) {

        initHotsView(data);
        mAdapter.addData(data);

    }

    private void initHotsView(HomeInfoBean data) {
        List<HomeInfoBean.HotsInfoBean> filmData  = new ArrayList<>();
        List<HomeInfoBean.HotsInfoBean> teleplayData  = new ArrayList<>();
        List<HomeInfoBean.HotsInfoBean> mangaData  = new ArrayList<>();
        List<HomeInfoBean.HotsInfoBean> varietyData  = new ArrayList<>();

        for(HomeInfoBean.HotsInfoBean d :data.getHotsInfoBeans()){
           switch (d.getType()){
               case  "电影":
                   filmData.add(d);
                   break;
               case  "电视剧":
                   teleplayData.add(d);
                   break;
               case  "综艺":
                   varietyData.add(d);
                  break;
               case  "动漫":
                   mangaData.add(d);
                   break;
           }
        }
        mFragments.add(HotsFilmFragment.getInstance(filmData));
        mFragments.add(HotsTeleplayFragment.getInstance(teleplayData));
        mFragments.add(HotsVarietyFragment.getInstance(varietyData));
        mFragments.add(HotsMangaFragment.getInstance(mangaData));

        vp.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
