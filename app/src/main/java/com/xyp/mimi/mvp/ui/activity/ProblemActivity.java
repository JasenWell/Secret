//package com.yiwuzhijia.ddyp.mvp.ui.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ExpandableListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.blankj.utilcode.util.SPUtils;
//import com.gyf.barlibrary.ImmersionBar;
//import com.jess.arms.di.component.AppComponent;
//import com.jess.arms.utils.ArmsUtils;
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//import com.yiwuzhijia.ddyp.R;
//import com.yiwuzhijia.ddyp.app.base.BaseSupportActivity;
//import com.yiwuzhijia.ddyp.di.component.DaggerProblemComponent;
//import com.yiwuzhijia.ddyp.mvp.contract.ProblemContract;
//import com.yiwuzhijia.ddyp.mvp.http.entity.BaseResponse;
//import com.yiwuzhijia.ddyp.mvp.http.entity.problem.ProblemListResult;
//import com.yiwuzhijia.ddyp.mvp.http.entity.user.UserPost;
//import com.yiwuzhijia.ddyp.mvp.presenter.ProblemPresenter;
//import com.yiwuzhijia.ddyp.mvp.utils.AppConstant;
//import com.yiwuzhijia.ddyp.mvp.view.ExpandableListviewAdapter;
//
//import butterknife.BindView;
//
//import static com.jess.arms.utils.Preconditions.checkNotNull;
//
//public class ProblemActivity extends BaseSupportActivity<ProblemPresenter> implements ProblemContract.View {
//
//    @BindView(R.id.app_title)
//    TextView appTitle;
//
//    @BindView(R.id.expand_list_id)
//    ExpandableListView expendList;
//
//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
//
//    private String userId;
//    private String token;
//    private int page=1;
//    private int pageSize=10;
//
//    private Boolean refresh = true;
//    //Model：定义的数据
//    private String[] groups;
//
//    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
//    private String[][] childs;
//
//    ExpandableListviewAdapter adapter;
//    @Override
//    public void initImmersionBar() {
//        super.initImmersionBar();
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.white)
//                .statusBarDarkFont(true)
//                .fitsSystemWindows(true)
//                .keyboardEnable(false)
//                .init();
//    }
//
//    @Override
//    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerProblemComponent //如找不到该类,请编译一下项目
//                .builder()
//                .appComponent(appComponent)
//                .view(this)
//                .build()
//                .inject(this);
//    }
//
//    @Override
//    public int initView(@Nullable Bundle savedInstanceState) {
//        return R.layout.activity_problem;
//    }
//
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
//        setAppTitle("常见问题");
//        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
//        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
//        mPresenter.getHelpList(new UserPost(userId, token, page, pageSize));
//
//
//
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                hideLoading();
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
////        //设置分组的监听
////        expendList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
////            @Override
////            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
////                Toast.makeText(getApplicationContext(), groupString[groupPosition], Toast.LENGTH_SHORT).show();
////                return false;
////            }
////        });
////        //设置子项布局监听
////        expendList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
////            @Override
////            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
////                Toast.makeText(getApplicationContext(), childString[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
////                return true;
////            }
////        });
//    }
//
//    private void getData( int page, int pageSize ) {
//        mPresenter.getHelpList(new UserPost(userId, token, page, pageSize));
//
//    }
//
//
//
//    @Override
//    public void showMessage(@NonNull String message) {
//        checkNotNull(message);
//        ArmsUtils.snackbarText(message);
//    }
//
//    @Override
//    public void launchActivity(@NonNull Intent intent) {
//        checkNotNull(intent);
//        ArmsUtils.startActivity(intent);
//    }
//
//    @Override
//    public void killMyself() {
//        finish();
//    }
//
//    @Override
//    public void helpListResult(ProblemListResult problemListResult) {
//        showLoadSuccess();
//        if (refresh) {
////            groups = null;
////            childs = null;
//            if (problemListResult.getData().size() > 0) {
//                groups =  new String[problemListResult.getData().size()-1];
//                childs = new String[problemListResult.getData().size()-1][1];
//                for(int i=0;i<problemListResult.getData().size()-1;i++){
//                    groups[i] = problemListResult.getData().get(i).getTitle();
//                    childs[i][0] = problemListResult.getData().get(i).getContents();
//                }
//////                adapter.notifyDataSetChanged();
//
//            }
////
////            groups= new String[]{"1"};
////            childs= new String[][]{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}};
//            adapter=new ExpandableListviewAdapter(this,groups,childs);
//            expendList.setAdapter(adapter);
//            adapter.refresh();
//            refreshLayout.finishRefresh();
//            refreshLayout.setNoMoreData(false);
//
//        }else {
//            if (problemListResult.getData().size()  > 0) {
//                for(int i=0;i<problemListResult.getData().size()-1;i++){
//                    groups[i] = problemListResult.getData().get(i).getTitle();
//                    childs[i][i] = problemListResult.getData().get(i).getContents();
//                }
//                refreshLayout.finishLoadMore();
//            } else if (problemListResult.getData().size() < 10) {
//                Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
//                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
//            }
//        }
//    }
//
//    @Override
//    public void helpInfoResult(BaseResponse s) {
//
//    }
//}
