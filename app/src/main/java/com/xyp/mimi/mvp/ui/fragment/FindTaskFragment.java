package com.xyp.mimi.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.mvp.contract.TaskContract;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.TaskList;
import com.xyp.mimi.mvp.presenter.TaskPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FindTaskFragment extends BaseSupportFragment<TaskPresenter> implements TaskContract.View {


    @BindView(R.id.spinner)
    Spinner spinner;

    private ArrayAdapter<String> adapter;

    String selectName;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerTaskComponent
//                .builder()
//                .appComponent(appComponent)
//                .taskModule(new TaskModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
//            text.setText("我的名字是："+name[arg2]);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        assert mPresenter != null;
//        mPresenter.searchTask();

    }




    @Override
    public void searchTaskSuccess(List<TaskList.ConBean> s) {
        showLoadSuccess();
        String[] name = new String[s.size()];
        for (int i = 0; i < s.size(); i++) {
            name[i] = s.get(i).getCnamme();
        }
        //获取玩关键词以后默认调用
        mPresenter.startTask( SPUtils.getInstance().getString(AppConstant.User.USER_ID), name[0]);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, name);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_multiple_choice,name);
        //将adapter 加入到spinner中
        spinner.setAdapter(adapter);
        //加入事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectName = parent.getItemAtPosition(position).toString();
                mPresenter.startTask( SPUtils.getInstance().getString(AppConstant.User.USER_ID), selectName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @OnClick({R.id.btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                mPresenter.getTask(SPUtils.getInstance().getString(AppConstant.User.USER_ID));
                break;
        }
    }

    @Override
    public void startTaskSuccess() {
        showLoadSuccess();
    }

    @Override
    public void getTaskSuccess(TaskData taskData) {
        showLoadSuccess();
//        Intent intent = new Intent(_mActivity, WebFragmentActivity.class);
//        String userName = (String) SpUtils.get(mContext, AppConstant.User.INFO, "");
//        String ip;
//        if( SpUtils.get(mContext, AppConstant.User.JUESE,"vip").equals("vip")){
//            ip =  Api.APP_DOMAIN_VIP_H5;
//        }else{
//            ip  = Api.APP_DOMAIN_H5;
//        }
//        String url = ip+"renwu/renwu.asp?appid=" + taskData.getAppid() + "&kw=" + taskData.getKw() + "&ks=" + taskData.getKs() + "&ids=" + taskData.getIds() + "&u=" + taskData.getU() + "&n=" + userName;
//        intent.putExtra("URL",url);
//        startActivity(intent);
    }



    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }


}
