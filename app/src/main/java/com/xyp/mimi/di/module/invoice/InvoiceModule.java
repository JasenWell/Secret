package com.xyp.mimi.di.module.invoice;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.invoice.InvoiceContract;
import com.xyp.mimi.mvp.model.invoice.InvoiceModel;

import dagger.Module;
import dagger.Provides;

@Module
public class InvoiceModule {

    InvoiceContract.View view;

    InvoiceContract.InvoiceAddAndEditorView invoiceAddAndEditorView;

    public InvoiceModule(InvoiceContract.View view) {
        this.view = view;
    }

    public InvoiceModule(InvoiceContract.InvoiceAddAndEditorView invoiceAddAndEditorView) {
        this.invoiceAddAndEditorView = invoiceAddAndEditorView;
    }

    @Provides
    @ActivityScope
    public InvoiceContract.Model provideModel(IRepositoryManager repositoryManager){
     return new InvoiceModel(repositoryManager);
    }
    @Provides
    @ActivityScope
    public InvoiceContract.View provideView(){
        return view;
    }

    @Provides
    @ActivityScope
    public InvoiceContract.InvoiceAddAndEditorView provideView1(){
        return invoiceAddAndEditorView;
    }


}
