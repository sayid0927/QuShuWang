package com.qushuwang.qushuwang.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseFragment;
import com.qushuwang.qushuwang.base.BaseFragmentPageAdapter;
import com.qushuwang.qushuwang.bean.FenleiLeimuBean;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerMainComponent;
import com.qushuwang.qushuwang.presenter.contract.ManHuanHomeContract;
import com.qushuwang.qushuwang.presenter.impl.MainActivityPresenter;
import com.qushuwang.qushuwang.presenter.impl.ManHuanHomePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class ManHuanHomeFragment extends BaseFragment implements ManHuanHomeContract.View {

    @Inject
    ManHuanHomePresenter mPresenter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    private BaseFragmentPageAdapter myAdapter;
    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void loadData() {
        mPresenter.Jousp_Test();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_manhuai_home;
    }

    @Override
    protected void initView() {
//        mPresenter.Jousp_Test();
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
    public void JouspTest_Success(List<FenleiLeimuBean> data) {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                mTitleList.add(data.get(i).getLeimu());
                mFragments.add(Meinvha_Title.newInstance(data.get(i).getId(),data.get(i).getUrl()));
            }
        }

        myAdapter = new BaseFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);

    }

    @Override
    public void showError(String message) {

    }

}
