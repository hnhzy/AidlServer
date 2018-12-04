package com.hzy.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hzy on 2018/12/4
 **/
public class IRemoteService extends Service {

    private  IBinder iBinder=new IAidlInterface.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d("IRemoteService","收到了远程的请求，输入的参数是"+num1+"和"+num2);
            return num1+num2;
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 当客户端绑定到该服务的时候会执行
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
