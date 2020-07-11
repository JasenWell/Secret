package com.xyp.mimi.mvp.ui.activity.address;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.BaseDividerListItem;
import com.github.baseclass.view.MyDialog;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.address.DaggerAddressComponent;
import com.xyp.mimi.di.module.address.AddressModule;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.event.RefreshAddressEvent;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddressDeletePost;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.http.entity.address.AddressListPost;
import com.xyp.mimi.mvp.presenter.address.AddressPresenter;
import com.xyp.mimi.mvp.ui.adapter.address.AddressAdapter;
import com.xyp.mimi.mvp.ui.adapter.address.AddressListAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class AddressListActivity extends BaseSupportActivity<AddressPresenter>  implements AddressContract.View {


    @BindView(R.id.rv_address_list)
    RecyclerView rvAddressList;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String userId;
    private String token;
    private int page=1;
    private int pageSize=10;


    AddressListAdapter addressAdapter;

    List<AddressListResult.DataBean> data = new ArrayList<>();

    private Boolean refresh = true;

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddressComponent.builder()
                .appComponent(appComponent)
                .addressModule(new AddressModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setAppTitle("收货地址");
        return R.layout.activity_address_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        addressAdapter = new AddressListAdapter(data);
        rvAddressList.setLayoutManager(new LinearLayoutManager(mContext));
        rvAddressList.addItemDecoration(new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST, ConvertUtils.dp2px(1),R.color.gray_f4));
        rvAddressList.setAdapter(addressAdapter);

        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        mPresenter.getAddressList(new AddressListPost(userId,token,1,10));

        addressAdapter.setOnAddressDeleteListener(new AddressAdapter.OnClickAddressDeleteListener() {
            @Override
            public void deleteAddress(int id) {
                MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认删除?");
                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mPresenter.deleteAddress(new AddressDeletePost(userId,token,id));
                    }
                });
                mDialog.create().show();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh = true;
                getData(1, 10);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                refresh = false;
                getData(page, 10);  //上拉加载添加数据
            }
        });
    }

    private void getData( int page, int pageSize ) {
        mPresenter.getAddressList(new AddressListPost(userId,token,page,pageSize));


    }


    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
    }

    @Override
    public void addressListResult(AddressListResult addressListResult) {
        showLoadSuccess();
        if (refresh) {
            data.clear();
            if (addressListResult.getData().size() > 0) {
                data.addAll(addressListResult.getData());
                addressAdapter.notifyDataSetChanged();
            } else {
                View emptyView=getLayoutInflater().inflate(R.layout.empty_view_address, null);
                emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                addressAdapter.setEmptyView(emptyView);

                addressAdapter.notifyDataSetChanged();
            }
            refreshLayout.finishRefresh();
            refreshLayout.setNoMoreData(false);
        }else {
            if (addressListResult.getData().size()  > 0) {
//                        history.clear();
                //模拟网络请求到的数据
                data.addAll(addressListResult.getData());
                addressAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            } else if (addressListResult.getData().size() < 10) {
                Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
            }
        }
    }

    @SuppressLint("TimberArgCount")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshAddressEvent event) {
        Timber.e("Activity","11111");
        refresh = true;  //刷新
        page =1;  //初始值为1
        getData(page, 10);
    }


    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void deleteResult(BaseResponse baseResponse) {
        showLoadSuccess();
        EventBus.getDefault().post(new RefreshAddressEvent());//删除地址
    }



    @OnClick({R.id.tv_address_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address_add:
                STActivityForResult(AddAddressActivity.class,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    getData(1, 10);
                    break;
            }
        }
    }

}
