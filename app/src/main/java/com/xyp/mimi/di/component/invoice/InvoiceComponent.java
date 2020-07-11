package com.xyp.mimi.di.component.invoice;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.invoice.InvoiceModule;
import com.xyp.mimi.mvp.ui.activity.invoice.InvoiceAddAndEditorActivity;
import com.xyp.mimi.mvp.ui.activity.invoice.InvoiceListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = InvoiceModule.class ,dependencies = AppComponent.class)
public interface InvoiceComponent {

    void injectInvoiceList(InvoiceListActivity invoiceListActivity);

    void injectAddInvoice(InvoiceAddAndEditorActivity invoiceAddAndEditorActivity);

}
