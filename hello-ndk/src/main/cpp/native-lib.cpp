#include <jni.h>
#include <string>
#include <hello_jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_demo_MainActivity_stringFromJNI(JNIEnv *env, jobject obj, jstring className, jstring methodName) {
    std::string hello = "Hello from C++";

    callJavaMethod(env, obj, className, methodName);

    return env->NewStringUTF(hello.c_str());
};