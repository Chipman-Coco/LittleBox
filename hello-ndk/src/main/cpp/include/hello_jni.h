#ifndef HELLO_JNI_H
#define HELLO_JNI_H

#ifdef __cplusplus
extern "C" {
#endif

    void callJavaField(JNIEnv *env, jobject obj, jstring className, jstring fieldName);

    jboolean callJavaMethod(JNIEnv *env, jobject obj, jstring className, jstring methodName);

#ifdef __cplusplus
};
#endif

#endif
