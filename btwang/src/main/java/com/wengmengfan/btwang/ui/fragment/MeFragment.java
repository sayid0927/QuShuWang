package com.wengmengfan.btwang.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.utils.ToastUtils;
import com.pgyersdk.activity.FeedbackActivity;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseFragment;
import com.wengmengfan.btwang.base.Constant;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.ui.activity.AboutActivity;
import com.wengmengfan.btwang.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * sayid ....
 * Created by wengmf on 2018/3/12.
 */

public class MeFragment extends BaseFragment{


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
        return R.layout.fragmeng_me_home;
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
