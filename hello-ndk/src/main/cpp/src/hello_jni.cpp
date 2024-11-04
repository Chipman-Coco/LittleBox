#include <jni.h>
#include "../include/hello_jni.h"
#include <string>
#include <android/log.h>
#include <cassert>

static const char *TAG = "HelloJni";
#define LOGD(fmt, args...) __android_log_print(ANDROID_LOG_DEBUG, TAG, fmt, ##args)
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

#define JNI_CLASS_NAME "com/example/demo/jni/HelloJni"

#include "../include/ffmpeg/libavcodec/version.h"
#include "../include/ffmpeg/libavcodec/avcodec.h"
#include "../include/ffmpeg/libavformat/version.h"
#include "../include/ffmpeg/libavutil/version.h"
#include "../include/ffmpeg/libswscale/version.h"
#include "../include/ffmpeg/libavfilter/version.h"

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

/**
 * 调用Java成员变量
 * @param env
 * @param obj
 * @param className
 * @param fieldName
 */
void callJavaField(JNIEnv *env, jobject obj, jstring className, jstring fieldName) {
    jboolean iscopy;
    const char *name = env->GetStringUTFChars(fieldName, &iscopy);
    LOGD("invoke method:%s", name);
    const char *classNameStr = env->GetStringUTFChars(className, &iscopy);
    jclass c = env->FindClass(classNameStr);
    //读取类的构造函数
    jmethodID constructMethod = env->GetMethodID(c, "<init>", "()V");
    //根据构造函数创建一个Object对象
    jobject objCallBack = env->NewObject(c, constructMethod);

    jboolean isCopy;
    const char *_fieldName = env->GetStringUTFChars(fieldName, &isCopy);

    jfieldID field_Name = env->GetFieldID(c, _fieldName, "Ljava/lang/String;");

    /*native层处理*/
    /*检测是否有异常*/
    jboolean hasException = env->ExceptionCheck();
    if (hasException == JNI_TRUE) {
        //打印异常，同Java中的printExceptionStack;
        env->ExceptionDescribe();
        env->ExceptionClear();

        //抛出异常给上面，让Java层去捕获
        jclass noFieldClass = env->FindClass("java/lang/Exception");
        std::string msg(_fieldName);
        std::string header = "无效字段";
        env->ThrowNew(noFieldClass, header.append(msg).c_str());
        env->ReleaseStringUTFChars(fieldName, _fieldName);
        return;
    }
    jstring fieldObj = static_cast<jstring>(env->GetObjectField(objCallBack, field_Name));
    const char *fieldC = env->GetStringUTFChars(fieldObj, &isCopy);
    LOGD("字段%s值:%s", _fieldName, fieldC);
    env->ReleaseStringUTFChars(fieldObj, fieldC);
}

jboolean callJavaMethod(JNIEnv *env, jobject obj1, jstring className, jstring methodName) {
    jboolean isCopy;
    const char *classNameStr = env->GetStringUTFChars(className, &isCopy);
    jclass callbackClass = env->FindClass(classNameStr);
    //获取构造函数
    jmethodID constructMethod = env->GetMethodID(callbackClass, "<init>", "()V");
    jobject objCallBack = env->NewObject(callbackClass, constructMethod);

    const char *_methodName = env->GetStringUTFChars(methodName, &isCopy);
    jmethodID _jmethodName = env->GetMethodID(callbackClass, _methodName, "(Ljava/lang/String;)V");
    const char *str = "123";
    jstring result = env->NewStringUTF(str);
    env->CallVoidMethod(objCallBack, _jmethodName, result);

    if (env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }
    //释放字符串内存
    env->ReleaseStringUTFChars(methodName, _methodName);
    env->ReleaseStringUTFChars(className, classNameStr);
    return JNI_TRUE;
}

/**
 * JNI方法
 */
static JNINativeMethod methods[] = {
        {"callJavaField",  "(Ljava/lang/String;Ljava/lang/String;)V", (void *) callJavaField},
        {"callJavaMethod", "(Ljava/lang/String;Ljava/lang/String;)Z", (void *) callJavaMethod},
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