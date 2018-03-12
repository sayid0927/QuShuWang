package com.ManHuan.manhuan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseFragment;
import com.ManHuan.manhuan.base.Constant;
import com.ManHuan.manhuan.component.AppComponent;
import com.ManHuan.manhuan.component.DaggerMainComponent;
import com.ManHuan.manhuan.ui.activity.AboutActivity;
import com.ManHuan.manhuan.ui.activity.FeedbackActivity;
import com.ManHuan.manhuan.ui.activity.MainActivity;
import com.blankj.utilcode.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class SettingHomeFragment extends BaseFragment {


    @BindView(R.id.updae)
    RelativeLayout updae;
    @BindView(R.id.baout)
    RelativeLayout baout;
    @BindView(R.id.feedback)
    RelativeLayout feedback;
    @BindView(R.id.exit)
    RelativeLayout exit;


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_setting_home;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @OnClick({R.id.updae, R.id.baout, R.id.feedback, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updae:
                ToastUtils.showLongToast("已是最新版本");
                break;
            case R.id.baout:

                Intent i = new Intent(getActivity(), AboutActivity. class);
                getActivity().startActivity(i);

                break;
            case R.id.feedback:
                Intent f = new Intent(getActivity(), FeedbackActivity. class);
                getActivity().startActivity(f);

                break;

            case R.id.exit:
                MainActivity.mainActivity.killAll();
                break;



        }
    }
}
