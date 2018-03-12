package com.wengmengfan.doutu.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.base.BaseFragment;
import com.wengmengfan.doutu.base.Constant;
import com.wengmengfan.doutu.component.AppComponent;
import com.wengmengfan.doutu.component.DaggerMainComponent;
import com.wengmengfan.doutu.ui.activity.AboutActivity;
import com.wengmengfan.doutu.ui.activity.DownListPicActivity;
import com.wengmengfan.doutu.ui.activity.FeedbackActivity;
import com.wengmengfan.doutu.ui.activity.MainActivity;
import com.wengmengfan.doutu.utils.DeviceUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.down_pic)
    RelativeLayout downPic;



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

    @OnClick({R.id.updae, R.id.baout, R.id.feedback, R.id.exit, R.id.down_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updae:
                ToastUtils.showLongToast("已是最新版本");
                break;
            case R.id.baout:

                Intent i = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(i);

                break;
            case R.id.feedback:
                Intent f = new Intent(getActivity(), FeedbackActivity.class);
                getActivity().startActivity(f);

                break;

            case R.id.exit:
                MainActivity.mainActivity.killAll();
                break;

            case R.id.down_pic:

                Intent d = new Intent(getActivity(), DownListPicActivity.class);
                getActivity().startActivity(d);
                break;

        }
    }
}
