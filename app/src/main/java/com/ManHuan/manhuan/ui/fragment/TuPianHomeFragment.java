package com.ManHuan.manhuan.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseFragment;
import com.ManHuan.manhuan.base.BaseFragmentPageAdapter;
import com.ManHuan.manhuan.base.Constant;
import com.ManHuan.manhuan.bean.TuPianHomeBean;
import com.ManHuan.manhuan.component.AppComponent;
import com.ManHuan.manhuan.component.DaggerMainComponent;
import com.ManHuan.manhuan.presenter.contract.TuPianHomeContract;
import com.ManHuan.manhuan.presenter.impl.TuPianHomePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class TuPianHomeFragment extends BaseFragment  implements TuPianHomeContract.View {

    @Inject
    TuPianHomePresenter mPresenter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    private BaseFragmentPageAdapter myAdapter;
    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void loadData() {
        mPresenter.Jousp_Home();
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_manhuai_home;
    }

    @Override
    protected void initView() {
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
    public void JouspHome_Success(List<TuPianHomeBean> data) {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                mTitleList.add(data.get(i).getTitle());
                mFragments.add(TuPian_Title.newInstance(data.get(i).getId(),data.get(i).getUrl()));
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
