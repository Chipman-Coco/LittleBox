package com.chipman.common.ktx

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText

/**
 * EditText相关扩展方法
 */

/**
 * 过滤掉空格和回车
 */
fun EditText.filterBlankAndCarriageReturn() {
    val filterList = mutableListOf<InputFilter>()
    filterList.addAll(filters)
    filterList.add(InputFilter { source, _, _, _, _, _ -> if (source == " " || source == "\n") "" else null })
    filters = filterList.toTypedArray()
}

/**
 * 用于简化为EditText组件操作设置afterTextChanged
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}