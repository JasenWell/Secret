package com.xyp.mimi.di.module;

import android.app.Application;
import android.view.LayoutInflater;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.contract.BindContract;
import com.xyp.mimi.mvp.http.entity.product.Product;
import com.xyp.mimi.mvp.model.BindModel;
import com.xyp.mimi.mvp.ui.adapter.BindRecommendQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class BindModule {

    BindContract.View view;
    public BindModule(BindContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public BindContract.Model provideModel(IRepositoryManager repositoryManager){
     return new BindModel(repositoryManager);
    }

    @ActivityScope
    @Provides
    public List<Product.ConBean> provideBindRecommendProductsBean() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public BindRecommendQuickAdapter prodvideBaseQuickAdapter(List<Product.ConBean> recommendProductsBeans, Application application) {
        BindRecommendQuickAdapter recommendQuickAdapter = new BindRecommendQuickAdapter(recommendProductsBeans);
        recommendQuickAdapter.addHeaderView(LayoutInflater.from(application).inflate(R.layout.adapter_item_bind_recommend_header, null));
        return recommendQuickAdapter;

    }

    @Provides
    @ActivityScope
    public BindContract.View provideView(){
        return view;
    }

}
