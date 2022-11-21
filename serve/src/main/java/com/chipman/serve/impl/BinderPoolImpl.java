package com.chipman.serve.impl;

import android.os.IBinder;
import android.os.RemoteException;

import com.chipman.serve.BinderType;
import com.chipman.serve.aidl.IBinderPool;

import java.util.Objects;

import javax.inject.Inject;

public class BinderPoolImpl extends IBinderPool.Stub {

    @Inject
    BinderPoolImpl() {

    }

    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        BinderType binderType = BinderType.Companion.getValue(binderCode);
        switch (Objects.requireNonNull(binderType)) {
            case MESSAGE:
                MessageManagerImpl.Companion.getInstance();
                break;
            case MUSIC:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + binderCode);
        }
        return null;
    }
}