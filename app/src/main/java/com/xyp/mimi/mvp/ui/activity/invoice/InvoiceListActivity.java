package com.xyp.mimi.mvp.ui.activity.invoice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.view.MyDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.module.invoice.InvoiceModule;
import com.xyp.mimi.mvp.contract.invoice.InvoiceContract;
import com.xyp.mimi.mvp.event.RefreshInvoiceEvent;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.presenter.invoice.InvoicePresenter;
import com.xyp.mimi.mvp.ui.adapter.invoice.InvoiceListAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InvoiceListActivity extends BaseSupportActivity<InvoicePresenter> implements InvoiceContract.View {

    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.rv_invoice)
    RecyclerView rvInvoice;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String userId;
    private String token;
    private int page=1;
    private int pageSize=10;

    InvoiceListAdapter invoiceListAdapter;

    List<InvoiceListResult.DataBean> data = new ArrayList<>();

    private Boolean refresh = true;

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .keyboardEnable(false)
                .init();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerInvoiceComponent.builder()
//                .appComponent(appComponent)
//                .invoiceModule(new InvoiceModule(this))
//                .build().injectInvoiceList(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setAppTitle("发票信息");
        return R.layout.activity_invoice_list;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        mPresenter.getInvoiceList(new InvoiceListPost(userId, token, page, pageSize));

        invoiceListAdapter = new InvoiceListAdapter(data);
        LinearLayoutManager xLinearLayoutManager = new LinearLayoutManager(mContext);
        rvInvoice.setAdapter(invoiceListAdapter);

        //设置默认发票
        invoiceListAdapter.setOnInvoiceDefaultListener(new InvoiceListAdapter.OnClickInvoiceDefaultListener() {
            @Override
            public void setDefaultInvoice(int id) {
                mPresenter.setDefaultInvoice(new InvoiceDeletePost(userId,token,id));
            }
        });

        invoiceListAdapter.setOnInvoiceDeleteListener(new InvoiceListAdapter.OnClickInvoiceDeleteListener() {
            @Override
            public void deleteInvoice(int id) {
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
                        mPresenter.deleteInvoice(new InvoiceDeletePost(userId,token,id));
                    }
                });
                mDialog.create().show();
            }
        });
        //编辑
        invoiceListAdapter.setOnInvoiceEditorListener(new InvoiceListAdapter.OnClickInvoiceEditorListener() {
            @Override
            public void editorInvoice(InvoiceListResult.DataBean dataBean) {
                Intent intent = new Intent();
                intent.putExtra("TYPE", "EDITOR");
                intent.putExtra("INVOICE_INFO", dataBean);
                STActivity(intent, InvoiceAddAndEditorActivity.class);
            }


        });

        rvInvoice.setLayoutManager(xLinearLayoutManager);

        rvInvoice.addItemDecoration(getItemDivider(ConvertUtils.dp2px(8),R.color.gray_f4));

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
        mPresenter.getInvoiceList(new InvoiceListPost(userId, token, page, pageSize));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshInvoiceEvent event) {
        refresh = true;  //刷新
        page =1;  //初始值为1
        getData(page, 10);
    }


    @OnClick({R.id.tv_invoice_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_invoice_add:
                Intent intent = new Intent();
                intent.putExtra("TYPE", "ADD");
                STActivity(intent, InvoiceAddAndEditorActivity.class);

                break;
        }
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getInvoiceList(InvoiceListResult invoiceListResult) {
        showLoadSuccess();
        if (refresh) {
            data.clear();
            if (invoiceListResult.getData().size() > 0) {
                data.addAll(invoiceListResult.getData());
                invoiceListAdapter.notifyDataSetChanged();
            } else {
                View emptyView=getLayoutInflater().inflate(R.layout.empty_view_invoice, null);
                emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                invoiceListAdapter.setEmptyView(emptyView);

                invoiceListAdapter.notifyDataSetChanged();
            }
            refreshLayout.finishRefresh();
            refreshLayout.setNoMoreData(false);
        }else {
            if (invoiceListResult.getData().size()  > 0) {
//                        history.clear();
                //模拟网络请求到的数据
                data.addAll(invoiceListResult.getData());
                invoiceListAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            } else if (invoiceListResult.getData().size() < 10) {
                Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
            }
        }
    }

    @Override
    public void deleteInvoiceResult(BaseResponse baseResponse) {
        showLoadSuccess();
        EventBus.getDefault().post(new RefreshInvoiceEvent());//删除发票
    }

    @Override
    public void setDefaultResult(BaseResponse baseResponse) {
        showLoadSuccess();
    }

}