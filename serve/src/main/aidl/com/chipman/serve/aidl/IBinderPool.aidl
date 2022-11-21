// IBinderPool.aidl
package com.chipman.serve.aidl;

interface IBinderPool {

     IBinder queryBinder(int binderCode);
}