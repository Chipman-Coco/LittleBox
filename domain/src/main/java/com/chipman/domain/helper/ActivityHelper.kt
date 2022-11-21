package com.chipman.domain.helper

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf

private const val PACKAGE_NAME = "com.chipman.wanandroid"

interface ActivityAddressable {

    val className: String

    val bundle: Bundle
}

fun intentTo(addressable: ActivityAddressable): Intent {
    return Intent().setComponent(ComponentName(PACKAGE_NAME, addressable.className))
        .putExtras(addressable.bundle)
}

object Activities {

    object Login : ActivityAddressable {
        override val className: String
            get() = "$PACKAGE_NAME.ui.login.LoginActivity"
        override val bundle: Bundle
            get() = bundleOf()
    }
}