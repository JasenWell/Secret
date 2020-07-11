package com.xyp.mimi.di.module;

import android.app.Application;
import android.view.LayoutInflater;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.model.YueAndUsdtModel;
import com.xyp.mimi.mvp.ui.adapter.ChongzhiQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import static com.xyp.mimi.mvp.contract.YueAndUsdtContract.*;

@Module
public class YueAndUsdtModule {

    ViewYue viewYue;

    ViewUsdt viewUsdt;

    public YueAndUsdtModule(ViewYue viewYue) {
        this.viewYue = viewYue;
    }

    public YueAndUsdtModule(ViewUsdt viewUsdt) {
        this.viewUsdt = viewUsdt;
    }

    @Provides
    @FragmentScope
    public Model provideModel(IRepositoryManager repositoryManager){
     return new YueAndUsdtModel(repositoryManager);
    }


    @FragmentScope
    @Provides
    public List<Chongzhi.ConBean> provideChongzhiBean() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    public ChongzhiQuickAdapter prodvideBaseQuickAdapter(List<Chongzhi.ConBean> recommendProductsBeans, Application application) {
        ChongzhiQuickAdapter recommendQuickAdapter = new ChongzhiQuickAdapter(recommendProductsBeans);
        recommendQuickAdapter.addHeaderView(LayoutInflater.from(application).inflate(R.layout.adapter_item_chongzhi_header, null));
        return recommendQuickAdapter;
    }

    @Provides
    @FragmentScope
    public ViewYue provideView(){
        return viewYue;
    }

    @Provides
    @FragmentScope
    public ViewUsdt provideView1(){
        return viewUsdt;
    }
}
