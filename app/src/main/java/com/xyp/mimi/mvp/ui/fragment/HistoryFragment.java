//package com.yiwuzhijia.ddyp.mvp.ui.fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.blankj.utilcode.util.SPUtils;
//import com.jess.arms.di.component.AppComponent;
//import com.yiwuzhijia.ddyp.R;
//import com.yiwuzhijia.ddyp.app.base.BaseSupportFragment;
//import com.yiwuzhijia.ddyp.mvp.contract.history.HistoryContract;
//import com.yiwuzhijia.ddyp.mvp.http.entity.history.HistoryPost;
//import com.yiwuzhijia.ddyp.mvp.http.entity.history.Keyword;
//import com.yiwuzhijia.ddyp.mvp.presenter.history.HistoryPresenter;
//import com.yiwuzhijia.ddyp.mvp.utils.AppConstant;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//
//public class HistoryFragment extends BaseSupportFragment<HistoryPresenter> implements HistoryContract.View {
//
//    @BindView(R.id.spinner)
//    Spinner spinner;
//
//    @BindView(R.id.spinner1)
//    Spinner spinner1;
//
//    @BindView(R.id.tv_1)
//    TextView tv1;
//    @BindView(R.id.tv_2)
//    TextView tv2;
//    @BindView(R.id.tv_3)
//    TextView tv3;
//
//    String[] numbers = {"周"};
//
//    private ArrayAdapter<String> adapter;
//
//    List<Keyword.ConBean> con = new ArrayList<>();
//
//    @Override
//    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//
//    }
//
//    @Override
//    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_history, container, false);
//    }
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
//        mPresenter.getKeywordList();
//
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, numbers);
////        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_multiple_choice,name);
//        //将adapter 加入到spinner中
//        spinner1.setAdapter(adapter);
//
//    }
//
//    @Override
//    public void setData(@Nullable Object data) {
//
//    }
//
//    @Override
//    public void HistoryResult(HistoryPost s) {
//        showLoadSuccess();
//        tv1.setText(s.getPingjun());
//        tv2.setText(s.getYugu());
//        tv3.setText(s.getQingxie());
//    }
//
//    @Override
//    public void keyWordListResult(Keyword k) {
//        showLoadSuccess();
//        con = k.getCon();
//        String[] name = new String[k.getCon().size()];
//        for (int i = 0; i < k.getCon().size(); i++) {
//            name[i] = k.getCon().get(i).getCnamme();
//        }
//        //获取玩关键词以后默认调用
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, name);
////        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_multiple_choice,name);
//        //将adapter 加入到spinner中
//        spinner.setAdapter(adapter);
//        //加入事件Spinner事件监听
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mPresenter.getHistory(SPUtils.getInstance().getString(AppConstant.User.USER_ID), con.get(position).getCid() + "");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public void showMessage(@NonNull String message) {
//        showLoadSuccess();
//    }
//}
