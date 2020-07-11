//package com.yiwu.gau.di.module;
//
//import android.app.Application;
//import android.view.LayoutInflater;
//
//import com.jess.arms.di.scope.FragmentScope;
//import com.jess.arms.integration.IRepositoryManager;
//import com.yiwu.gau.R;
//import com.yiwu.gau.mvp.contract.YueAndUsdtContract;
//import com.yiwu.gau.mvp.http.entity.chongzhi.Chongzhi;
//import com.yiwu.gau.mvp.model.YueAndUsdtModel;
//import com.yiwu.gau.mvp.ui.adapter.ChongzhiQuickAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class UsdtModule {
//
//    YueAndUsdtContract.ViewUsdt view;
//
//    public UsdtModule(YueAndUsdtContract. view) {
//        this.view = view;
//    }
//
//    @Provides
//    @FragmentScope
//    public YueAndUsdtContract.Model provideModel(IRepositoryManager repositoryManager){
//     return new YueAndUsdtModel(repositoryManager);
//    }
//
//
//    @FragmentScope
//    @Provides
//    public List<Chongzhi.ConBean> provideChongzhiBean() {
//        return new ArrayList<>();
//    }
//
//    @FragmentScope
//    @Provides
//    public ChongzhiQuickAdapter prodvideBaseQuickAdapter(List<Chongzhi.ConBean> recommendProductsBeans, Application application) {
//        ChongzhiQuickAdapter recommendQuickAdapter = new ChongzhiQuickAdapter(recommendProductsBeans);
//        recommendQuickAdapter.addHeaderView(LayoutInflater.from(application).inflate(R.layout.adapter_item_chongzhi_header, null));
//        return recommendQuickAdapter;
//    }
//
//    @Provides
//    @FragmentScope
//    public YueAndUsdtContract.View1 provideView(){
//        return view;
//    }
//
//}
