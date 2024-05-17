#include <jni.h>
#include "../include/hello_jni.h"
#include <string>
#include <android/log.h>
#include <assert.h>

static const char *TAG = "NativeLib";
#define LOGD(fmt, args...) __android_log_print(ANDROID_LOG_DEBUG, TAG, fmt, ##args)
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

#define JNI_CLASS_NAME "com/example/myndkdemo/HelloJni"

#include "../include/ffmpeg/libavcodec/version.h"
#include "../include/ffmpeg/libavcodec/avcodec.h"
#include "../include/ffmpeg/libavformat/version.h"
#include "../include/ffmpeg/libavutil/version.h"
#include "../include/ffmpeg/libswscale/version.h"
#include "../include/ffmpeg/libavfilter/version.h"

extern "C" {

jstring getFfmpegVersion(JNIEnv *env, jobject obj) {
    char strBuffer[1024 * 4] = {0};
    strcat(strBuffer, "libavcodec : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVCODEC_VERSION));
    strcat(strBuffer, "\nlibavformat : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVFORMAT_VERSION));
    strcat(strBuffer, "\nlibavutil : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVUTIL_VERSION));
    strcat(strBuffer, "\nlibavfilter : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVFILTER_VERSION));
    strcat(strBuffer, "\nlibswresample : ");
    strcat(strBuffer, AV_STRINGIFY(LIBSWRESAMPLE_VERSION));
    strcat(strBuffer, "\nlibswscale : ");
    strcat(strBuffer, AV_STRINGIFY(LIBSWSCALE_VERSION));
    strcat(strBuffer, "\navcodec_configure : \n");
    strcat(strBuffer, avcodec_configuration());
    strcat(strBuffer, "\navcodec_license : ");
    strcat(strBuffer, avcodec_license());
    LOGD("Get ffmpeg version:\n%s", strBuffer);
    return env->NewStringUTF(strBuffer);
}

jstring stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

/**
 * 调用Java成员变量
 * @param env
 * @param obj
 * @param className
 * @param fieldName
 */
void callJavaField(JNIEnv *env, jobject obj, jstring className, jstring fieldName) {
    jboolean isCopy;
    const char *name = env->GetStringUTFChars(fieldName, &isCopy);
    LOGD("invoke method: %s", name);
    const char *classNameStr = env->GetStringUTFChars(className, &isCopy);
    jclass c = env->FindClass(classNameStr);
    jmethodID constructMethod = env->GetMethodID(c, "<init>", "()V");
    jobject o = env->NewObject(c, constructMethod);

    jboolean isCopy2;
    const char *_fieldName = env->GetStringUTFChars(fieldName, &isCopy2);
    //如果传入一个找不到的字段会报错
    jboolean hasException = env->ExceptionCheck();
    if (hasException) {
        env->ExceptionDescribe(); //打印异常
        env->ExceptionClear(); //清除当前异常

    }

    jfieldID stringFieldId = env->GetFieldID(c, _fieldName, "Ljava/lang/String;");
    jstring stringFiledValue = static_cast<jstring>(env->GetObjectField(o, stringFieldId));
    const char *filedChar = env->GetStringUTFChars(stringFiledValue, &isCopy);
    LOGD("Java field value: %s", filedChar);
    env->ReleaseStringUTFChars(stringFiledValue, filedChar);
}

jboolean callJavaMethod(JNIEnv *env, jobject obj, jstring className, jstring methodName) {
    jboolean isCopy;
    const char *classNameStr = env->GetStringUTFChars(className, &isCopy);
    jclass clzz = env->FindClass(classNameStr);
    jmethodID constructMethodId = env->GetMethodID(clzz, "<init>", "()V");
    jobject obj2 = env->NewObject(clzz, constructMethodId);

    const char *_methodName = env->GetStringUTFChars(methodName, &isCopy);
    jmethodID _jmethodId = env->GetMethodID(clzz, _methodName, "(Ljava/lang/String;)V");
    const char *str = "test";
    jstring result = env->NewStringUTF(str);
    env->CallVoidMethod(obj2, _jmethodId, result);

    if (env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }
    env->ReleaseStringUTFChars(methodName, _methodName);
    env->ReleaseStringUTFChars(className, classNameStr);
}

/**
 * JNI方法
 */
static JNINativeMethod methods[] = {
        {"stringFromJNI",    "()Ljava/lang/String;", (void *) stringFromJNI},
        {"getFfmpegVersion", "()Ljava/lang/String;", (void *) getFfmpegVersion}
};

/**
 * 动态注册JNI方法
 * @param env
 * @return
 */
jboolean register_dynamic_Methods(JNIEnv *env) {
    std::string s = JNI_CLASS_NAME;
    const char *className = s.c_str();
    jclass clzz = env->FindClass(className);
    if (clzz == NULL) {
        LOGE("Can't find class: %s", className);
        return JNI_FALSE;
    }
    jint result = env->RegisterNatives(clzz, methods, sizeof(methods) / sizeof(methods[0]));
    if (result < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

/**
 * 初始化
 * @param vm
 * @param reserved
 * @return
 */
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    if (vm->GetEnv(reinterpret_cast<void **> (&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    assert(env != NULL);

    if (!register_dynamic_Methods(env)) {
        return JNI_ERR;
    }
    return JNI_VERSION_1_6;
}

/*
JNIEXPORT jstring JNICALL
Java_com_example_myndkdemo_MainActivity_stringFromJNI(JNIEnv *env, jobject) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
*/

}