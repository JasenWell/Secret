package com.xyp.mimi.im.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.utils.SingleSourceLiveData;
import io.rong.imlib.RongIMClient;

public class SplashViewModel extends AndroidViewModel {

    private SingleSourceLiveData<Boolean> autoResult = new SingleSourceLiveData<>();
    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 自动连结果
     * @return
     */
    public LiveData<Boolean> getAutoLoginResult() {
        if (IMManager.getInstance().getConnectStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            autoResult.postValue(true);
        } else {
            autoResult.setSource(IMManager.getInstance().getAutoLoginResult());
        }
        return autoResult;
    }

}
