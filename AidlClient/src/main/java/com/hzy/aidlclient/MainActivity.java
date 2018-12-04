package com.hzy.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hzy.aidlserver.IAidlInterface;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入
     */
    private EditText mEt1;
    /**
     * 请输入
     */
    private EditText mEt2;
    /**
     * AIDL远程计算
     */
    private Button mBtn;
    /**
     * 点击计算获取结果
     */
    private EditText mRes;

    private TextView mTv;

    IAidlInterface iAidlInterface;

    private ServiceConnection conn=new ServiceConnection() {
        //绑定上服务器的时候
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //拿到远程服务
            Log.d("MainActivity--",name.toString());
            Log.d("MainActivity--",service.toString());
            iAidlInterface=IAidlInterface.Stub.asInterface(service);

        }

        //服务器断开的时候
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收资源
            iAidlInterface=null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEt1 = (EditText) findViewById(R.id.et1);
        mEt2 = (EditText) findViewById(R.id.et2);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mRes = (EditText) findViewById(R.id.res);
        mTv = (TextView) findViewById(R.id.tv);

        bindService();//启动就开始绑定服务
    }

    @Override
    public void onClick(View v) {
        int num1=Integer.parseInt(mEt1.getText().toString());
        int num2=Integer.parseInt(mEt2.getText().toString());
        try {
            int res=iAidlInterface.add(num1,num2);
            mRes.setText(res+"");
        } catch (RemoteException e) {
            mRes.setText("Error");
            e.printStackTrace();
        }

    }

    private void bindService() {
        //获取到服务端
        Intent intent=new Intent();
        //新版本必须显示Intent启动绑定服务
        intent.setComponent(new ComponentName("com.hzy.aidlserver","com.hzy.aidlserver.IRemoteService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
