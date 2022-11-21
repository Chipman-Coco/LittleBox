// IMessageManager.aidl
package com.chipman.serve.aidl;

import com.chipman.serve.aidl.Message;
import com.chipman.serve.aidl.IMessageCallbackListener;

interface IMessageManager {

    // Client 发送 Message 给 Server
    void sendMsg(in Message msg);

    // 客户端注册监听回调
    void registerReceiveListener(IMessageCallbackListener listener);

    // 客户端取消监听回调
    void unRegisterReceiveListener(IMessageCallbackListener listener);
}