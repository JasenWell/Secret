package com.xyp.mimi.mvp.ui.activity.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.history.HistoryContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.history.HistoryResult;
import com.xyp.mimi.mvp.presenter.history.HistoryPresenter;

public class HistoryActivity extends BaseSupportActivity<HistoryPresenter> implements HistoryContract.View {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void HistoryResult(HistoryResult s) {

    }

    @Override
    public void deleteHistory(BaseResponse baseResponse) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

//  /*  @BindView(R.id.app_title)
//    TextView appTitle;
//
//    @BindView(R.id.rv_history)
//    RecyclerView rvHistory;
//
//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
//
//    @BindView(R.id.app_right_tv)
//    TextView appRightTv;
//
//    @BindView(R.id.tv_delete)
//    TextView tvDelete;
//
//    @BindView(R.id.ll_history_bottom)
//    LinearLayout llHistoryBottom;
//
//    @BindView(R.id.cb_check)
//    MyCheckBox cbCheck;
//
//    private String userId;
//    private String token;
//
//    private int page = 1;
//    private int pageSize = 10;
//
//    HistoryAdapter adapter;
//
//    List<HistoryResult.DataBean> history = new ArrayList<>();
//
//    String historyId;//选中的id
//
//    List<HistoryResult.DataBean>  selectIndex = new ArrayList<>();
//
//
//    private Boolean refresh = true;
//
//
//    @Override
//    public void initImmersionBar() {
//        super.initImmersionBar();
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.white)
//                .statusBarDarkFont(true)
//                .fitsSystemWindows(true)
//                .init();
//    }
//
//    @Override
//    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerHistoryComponent.builder()
//                .appComponent(appComponent)
//                .historyModule(new HistoryModule(this))
//                .build().inject(this);
//    }
//
//    @Override
//    public boolean useEventBus() {
//        return true;
//    }
//
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(DeleteCollectEvent event) {
//        Timber.e("HistoryActivity");
//        if (event.getNumber() > 0) {
//            historyId = "";
//            historyId = event.getVideoId();
//            tvDelete.setClickable(true);
//            selectIndex = event.getSelectIndex();//选中的下标
//            tvDelete.setText("删除(" + event.getNumber() + ")");
//            removeDuplicate(selectIndex);
//        } else {
//            tvDelete.setText("删除");
//            tvDelete.setClickable(false);
//        }
//        if (event.isSelectAll()) {//全选了
//            cbCheck.setChecked(true);
//        } else {
//            cbCheck.setChecked(false);
//        }
//    }
//
//
//    public   static   List  removeDuplicate(List list)  {
//        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
//            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
//                if  (list.get(j).equals(list.get(i)))  {
//                    list.remove(j);
//                }
//            }
//        }
//        return list;
//    }
//    @Override
//    public int initView(@Nullable Bundle savedInstanceState) {
//        return R.layout.activity_history;
//    }
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
//        appRightTv.setText("编辑");
//        setAppTitle("我的足迹");
//        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
//        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
//        getData(1, 10);
//        rvHistory.setLayoutManager(new LinearLayoutManager(mContext));
//        adapter = new HistoryAdapter(R.layout.item_history, history);
//        rvHistory.setAdapter(adapter);
//        //底部选择框
//        cbCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cbCheck.isChecked()) {
//                    adapter.setSelectAll(true);
////                    tvCheckedAll.setText("取消全选");
//                } else {
////                    tvCheckedAll.setText("全选");
//                    adapter.setSelectAll(false);
//                }
//            }
//        });
//
//
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refresh = true;
//                getData(1, 10);
//            }
//        });
//
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                page += 1;
//                refresh = false;
//                getData(page, 10);  //上拉加载添加数据
//            }
//        });
//
//
//
//    }
//
//    private void getData(int page, int pageSize) {
//        mPresenter.getHistoryData(new HistoryPost(userId, token, page, pageSize));
//    }
//
//
//    @Override
//    public void showMessage(@NonNull String message) {
//
//    }
//
//    @Override
//    public void HistoryResult(HistoryResult historyResult) {
//        showLoadSuccess();
////        history.addAll(s.getData());
////        adapter.notifyDataSetChanged();
////        refreshLayout.finishRefresh();
////        refreshLayout.setNoMoreData(false);
//
//        if (refresh) {
//            history.clear();
//            if (historyResult.getData().size() > 0) {
//                history.addAll(historyResult.getData());
//                adapter.notifyDataSetChanged();
//            } else {
//                View emptyView=getLayoutInflater().inflate(R.layout.empty_view_history, null);
//                emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT));
//                adapter.setEmptyView(emptyView);
//                adapter.notifyDataSetChanged();
//            }
//            refreshLayout.finishRefresh();
//            refreshLayout.setNoMoreData(false);
//        }else {
//            if (historyResult.getData().size()  > 0) {
////                        history.clear();
//                //模拟网络请求到的数据
//                history.addAll(historyResult.getData());
//                adapter.notifyDataSetChanged();
//                refreshLayout.finishLoadMore();
//            } else if (historyResult.getData().size() < 10) {
//                Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
//                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
//            }
//        }
//    }
//
//    @Override
//    public void deleteHistory(BaseResponse baseResponse) {
//        showLoadSuccess();  //删除成功
//        refresh = true;  //刷新
//        page =1;  //初始值为1
//        getData(page, 10);
//
//        history.removeAll(selectIndex);
//        adapter.notifyDataSetChanged();
//        adapter.setSelectAll(false);//全取消选中
//        adapter.setEdit(!adapter.isEdit());
//
//        if (adapter.isEdit()) {
//            app_right_tv.setText("取消");
//            llHistoryBottom.setVisibility(View.VISIBLE);
//            refreshLayout.setEnableLoadMore(false);
//            refreshLayout.setEnableRefresh(false);
//        } else {
//            app_right_tv.setText("编辑");
//            llHistoryBottom.setVisibility(View.GONE);
//            refreshLayout.setEnableLoadMore(true);
//            refreshLayout.setEnableRefresh(true);
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    @OnClick({R.id.app_right_tv, R.id.tv_delete})
//    protected void onViewClick(View v) {
////        Intent intent;
//        switch (v.getId()) {
//            case R.id.app_right_tv:
//                if (history.isEmpty()) {//当没数据时候不操作
//                    return;
//                }
//                adapter.setEdit(!adapter.isEdit());
//                if (adapter.isEdit()) {
//                    appRightTv.setText("取消");
//                    llHistoryBottom.setVisibility(View.VISIBLE);
//                    refreshLayout.setEnableLoadMore(false);
//                    refreshLayout.setEnableRefresh(false);
//                } else {
//                    appRightTv.setText("编辑");
//                    llHistoryBottom.setVisibility(View.GONE);
//                    refreshLayout.setEnableLoadMore(true);
//                    refreshLayout.setEnableRefresh(true);
//                }
//                adapter.notifyDataSetChanged();
//                break;
////            case R.id.cb_check:
////                if (cbCheck.isChecked()) {
////                    adapter.setSelectAll(true);
//////                    tvCheckedAll.setText("取消全选");
////                } else {
//////                    tvCheckedAll.setText("全选");
////                    adapter.setSelectAll(false);
////                }
////                break;
//            case R.id.tv_delete:
//               mPresenter.deleteHistory(new HistoryDeletePost(userId,token,historyId));
//                break;
//        }
//    }*/

}
