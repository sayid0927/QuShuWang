package com.ManHuan.manhuan.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseActivity;
import com.ManHuan.manhuan.base.BaseFragmentPageAdapter;
import com.ManHuan.manhuan.bean.ImgContent;
import com.ManHuan.manhuan.bean.request.Meinvha_Title_request;
import com.ManHuan.manhuan.component.AppComponent;
import com.ManHuan.manhuan.component.DaggerMainComponent;
import com.ManHuan.manhuan.presenter.contract.ImgContentContract;
import com.ManHuan.manhuan.presenter.impl.ImgContentPresenter;
import com.ManHuan.manhuan.ui.fragment.ImageBrowseFragment;
import com.ManHuan.manhuan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class ImgContentActivity extends BaseActivity implements ImgContentContract.View {


    @Inject
    ImgContentPresenter mPresenter;

    @BindView(R.id.images_view)
    ViewPager Images_View;
    @BindView(R.id.llExit)
    LinearLayout llExit;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.llRight)
    LinearLayout llRight;
    @BindView(R.id.connection_title)
     RelativeLayout connectionTitle;

    private int id;
    private List<String> imagePath;
    private int position;
    private Meinvha_Title_request request;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private BaseFragmentPageAdapter myAdapter;
    public static ImgContentActivity install;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_imgcontent;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    @Override
    public void initView() {

        id = getIntent().getIntExtra("Id", 1);
        imagePath = new ArrayList<>();
        request = new Meinvha_Title_request();
        request.setId(id);
        mPresenter.Fetch_ImgContent_List(request);
        install=this;
        connectionTitle.setVisibility(View.INVISIBLE);

    }

    public  void  setConnectionTitle(int v){
        connectionTitle.setVisibility(v);
    }
    public  int  getConnectionTitle(){
       return connectionTitle.getVisibility();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void Fetch_ImgContent_List_Success(final List<ImgContent.DataBean> data) {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                mFragments.add(ImageBrowseFragment.newInstance(data.get(i).getImg_url()));
            }
        }
        myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, null);
        Images_View.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        Images_View.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String title = String.valueOf(position + 1) + " / " + data.size();
                 tvTitle.setText(title);

                if ((position + 1) == data.size()) {
                    ToastUtils.showSingleToast("最后一页...");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.llExit, R.id.tvTitle, R.id.llRight, R.id.connection_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llExit:
                this.finish();
                break;
            case R.id.tvTitle:
                break;
            case R.id.llRight:
                break;
            case R.id.connection_title:
                break;
        }
    }
}
