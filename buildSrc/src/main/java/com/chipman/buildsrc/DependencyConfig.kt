package com.chipman.buildsrc

/**
 * 项目依赖版本统一管理
 */
object DependencyConfig {

    /**
     * Android 相关依赖
     */
    object Android {
        const val AndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val AppCompat = "androidx.appcompat:appcompat:1.3.0"
        const val CoreKtx = "androidx.core:core-ktx:1.7.0"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val Junit = "junit:junit:4.13.2"
        const val Material = "com.google.android.material:material:1.6.1"
        const val TestExtJunit = "androidx.test.ext:junit:1.1.3"
        const val TestEspressoCore = "androidx.test.espresso:espresso-core:3.4.0"

        const val ActivityKtx = "androidx.activity:activity-ktx:1.4.0"
        const val FragmentKtx = "androidx.fragment:fragment-ktx:1.4.1"
        const val SwiperRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"                    // 下拉刷新
    }

    /**
     * Kotlin 相关依赖
     */
    object Kotlin {
        const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:1.7.10"
        const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2"                    // 协程
        const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2"
        private const val coil_version = "2.1.0"                                                            // kotlin 图片加载库
        const val Coil = "io.coil-kt:coil:$coil_version"
        const val CoilGif = "io.coil-kt:coil-gif:$coil_version"
        const val CoilSvg = "io.coil-kt:coil-svg:$coil_version"
        const val CoilVideo = "io.coil-kt:coil-video:$coil_version"

        const val Moshi = "com.squareup.moshi:moshi:1.13.0"                                               // Kotlin Json 序列化
        const val MoshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"
        const val MoshiCodegenAPT = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"

        const val SerialJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
    }

    /**
     * Jetpack 相关依赖
     */
    object Jetpack {
        const val DataStore = "androidx.datastore:datastore-preferences:1.0.0"

        const val Hilt = "com.google.dagger:hilt-android:2.42"
        const val HiltAPT = "com.google.dagger:hilt-android-compiler:2.42"

        private const val lifecycle_version = "2.5.1"
        const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
        const val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
        const val LifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"

        const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
        const val ViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"

        private const val nav_version = "2.4.2"                                                             // 页面导航组件
        const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val NavigationUIKtx = "androidx.navigation:navigation-ui-ktx:$nav_version"

        const val Paging3 = "androidx.paging:paging-runtime:3.1.1"                                          // 分页列表组件

        private const val room_version = "2.4.2"
        const val Room = "androidx.room:room-runtime:$room_version"
        const val RoomKtx = "androidx.room:room-ktx:$room_version"
        const val RoomAPT = "androidx.room:room-compiler:$room_version"
    }

    /**
     * 主流框架依赖
     */
    object Prevalent {
        const val ARouter = "com.alibaba:arouter-api:1.5.2"                                                 // 路由
        const val ARouterAPT = "com.alibaba:arouter-compiler:1.5.2"
        const val AutoService = "com.google.auto.service:auto-service:1.0.1"                                // 自动生成SPI暴露服务文件
        const val AutoServiceAnnotations = "com.google.auto.service:auto-service-annotations:1.0.1"

        const val EventBus = "org.greenrobot:eventbus:3.3.1"                                                // 事件分发
        const val EventBusAPT = "org.greenrobot:eventbus-annotation-processor:3.3.1"
        const val ExoPlayerCore = "com.google.android.exoplayer:exoplayer-core:2.17.0"                      // Google播放器
        const val ExoPlayerUi = "com.google.android.exoplayer:exoplayer-ui:2.17.0"
        const val ExoPlayerHls = "com.google.android.exoplayer:exoplayer-hls:2.17.0"
        const val ExoPlayerDash = "com.google.android.exoplayer:exoplayer-dash:2.17.0"

        const val Gson = "com.google.code.gson:gson:2.9.0"
        const val Glide = "com.github.bumptech.glide:glide:4.13.2"
        const val GlideAPT = "com.github.bumptech.glide:compiler:4.13.2"

        const val Lottie = "com.airbnb.android:lottie:5.2.0"                                                // 动画库
        const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"                           // 内存泄露检测

        const val MMKV = "com.tencent:mmkv:1.2.13"

        const val Okhttp = "com.github.bumptech.glide:okhttp3-integration:4.13.2"
        const val OkHttpInterceptorLogging = "com.squareup.okhttp3:logging-interceptor:4.9.2"               // 请求日志拦截器

        const val Retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val RetrofitConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"                     // Java Gson序列化
        const val RetrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:2.9.0"
        const val RetrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"                   // Kotlin Gson序列化

        private const val ijk_version = "0.8.8"
        const val IjkPlayer_java = "tv.danmaku.ijk.media:ijkplayer-java:$ijk_version"                       // ijk player
        const val IjkPlayer_exo = "tv.danmaku.ijk.media:ijkplayer-exo:$ijk_version"
        const val IjkPlayer_armv7a = "tv.danmaku.ijk.media:ijkplayer-armv7a:$ijk_version"                   // so文件
        const val IjkPlayer_x86_64 = "tv.danmaku.ijk.media:ijkplayer-x86_64:$ijk_version"

        const val FlexBox = "com.google.android.flexbox:flexbox:3.0.0"
        const val Fresco = "com.facebook.fresco:fresco:2.6.0"                                               // 图片加载
    }

    /**
     * 其它相关依赖
     */
    object Github {
        const val AutoSize = "com.github.JessYanCoding:AndroidAutoSize:v1.2.1"                              // 屏幕自动适配

        const val Banner = "com.github.zhpanvip:bannerviewpager:3.5.5"

        const val DialogLifecycle = "com.afollestad.material-dialogs:lifecycle:3.3.0"                       // Dialog
        const val DialogCore = "com.afollestad.material-dialogs:core:3.3.0"
        const val DialogBottomSheets = "com.afollestad.material-dialogs:bottomsheets:3.3.0"

        const val GSYPlayer = "com.github.CarGuo.GSYVideoPlayer:gsyVideoPlayer-java:v8.3.3-release-jitpack"
        const val GSYExoPlayer = "com.github.CarGuo.GSYVideoPlayer:GSYVideoPlayer-exo2:v8.3.3-release-jitpack"
        const val GSYIjkArmv7a = "com.github.CarGuo.GSYVideoPlayer:gsyVideoPlayer-armv7a:v8.3.3-release-jitpack"
        const val GSYIjkArm64 = "com.github.CarGuo.GSYVideoPlayer:gsyVideoPlayer-arm64:v8.3.3-release-jitpack"

        const val ImmersionBar = "com.geyifeng.immersionbar:immersionbar:3.2.2"                             // 沉浸式状态栏和导航栏管理
        const val ImmersionBarKtx = "com.geyifeng.immersionbar:immersionbar-ktx:3.2.2"

        const val Matisse = "com.zhihu.android:matisse:0.5.3-beta3"                                         // 图片选择
        const val MPAndroidChart = "com.github.PhilJay:MPAndroidChart:v3.1.0"                               // 图表

        const val PermissionX = "com.guolindev.permissionx:permissionx:1.6.4"                               // 权限申请
        const val PhoneTextWatcher = "com.github.jaydroid1024:PhoneTextWatcher:0.0.2"                       // 手机号输入框格式化

        const val RecyclerViewAdapter = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.6"            // RecyclerViewAdapter
        const val RefreshLayout = "io.github.scwang90:refresh-layout-kernel:2.0.5"                          // 下拉刷新
        const val RefreshHeader = "io.github.scwang90:refresh-header-classics:2.0.5"                        // 经典刷新头
        const val RefreshGoogleHeader = "io.github.scwang90:refresh-header-material:2.0.5"                  // 谷歌刷新头
        const val RefreshFooter = "io.github.scwang90:refresh-footer-classics:2.0.5"                        // 经典加载底部加载

        const val Timber = "com.jakewharton.timber:timber:5.0.1"                                            // 日志输出
        const val ToastUtil = "com.github.getActivity:ToastUtils:10.5"                                      // Tost 工具

        const val Util = "com.blankj:utilcodex:1.31.1"                                                      // Util

        const val XUI = "com.github.xuexiangjys:XUI:1.1.9"                                                  // UI 框架
        const val XPopup = "com.github.li-xiaojun:XPopup:2.8.11"                                            // Popup Window

        const val ZXingLite = "com.github.jenly1314:zxing-lite:2.2.0"                                       // 二维码识别
    }


    /**
     * SDK
     */
    object SDK {
        const val Wechat = "com.tencent.mm.opensdk:wechat-sdk-android:6.8.0"                                // 微信
        const val AliPay = "com.alipay.sdk:alipaysdk-android:15.8.11"                                       // 支付宝
        const val AMap3D = "com.amap.api:3dmap:7.6.0"                                                       // 3D 地图
        const val AMapLocation = "com.amap.api:location:5.2.0"                                              // 定位

        const val Bugly = "com.tencent.bugly:crashreport:4.0.4"                                             // 异常上报
        const val BuglyNative = "com.tencent.bugly:nativecrashreport:3.8.0"                                 // native异常上报
        const val TBSX5 = "com.tencent.tbs:tbssdk:44199"                                                    // X5WebView

        const val AliYunOss = "com.aliyun.dpa:oss-android-sdk:2.9.4"                                        // 阿里云 oss
    }
}