package com.xyp.mimi.di.component.address;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.address.AddressModule;
import com.xyp.mimi.mvp.ui.activity.address.AddAddressActivity;
import com.xyp.mimi.mvp.ui.activity.address.AddressListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = AddressModule.class ,dependencies = AppComponent.class)
public interface AddressComponent {

    void inject(AddressListActivity activity);

    void inject(AddAddressActivity addAddressActivity);

}
