// IMessageCallbackListener.aidl
package com.chipman.serve.aidl;

import com.chipman.serve.aidl.Message;

interface IMessageCallbackListener {

    void onReceive(out Message msg);
}