package com.chipman.littlebox.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.chipman.common.ktx.afterTextChanged
import com.chipman.common.ktx.filterBlankAndCarriageReturn
import com.chipman.common.util.systemui.StatusBarUtil
import com.chipman.littlebox.BaseActivity
import com.chipman.littlebox.MainActivity
import com.chipman.littlebox.R
import com.chipman.littlebox.databinding.ActivityLoginBinding
import com.chipman.littlebox.util.AppUtil
import com.chipman.littlebox.widget.LoadingDialog
import com.jay.phone_text_watcher.PhoneTextWatcher
import com.jay.phone_text_watcher.TextChangeCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val mViewModel: LoginViewModel by viewModels()

    private var countDownJob: Job? = null // 倒计时任务
    private var loadingDialog: LoadingDialog? = null

    companion object {
        fun start(fromActivity: Activity) {
            val intent = Intent(fromActivity, LoginActivity::class.java)
            fromActivity.startActivity(intent)
        }
    }

    override fun ActivityLoginBinding.initView(savedInstanceState: Bundle?) {
        StatusBarUtil.setTranslucentStatus(this@LoginActivity)
        with(etPhone) {
            filterBlankAndCarriageReturn()
            val phoneTextWatcherSpace = PhoneTextWatcher()
            addTextChangedListener(phoneTextWatcherSpace)
            phoneTextWatcherSpace.setTextChangedCallback(object : TextChangeCallback() {
                override fun afterTextChanged(unformatted: String?, isPhoneNumberValid: Boolean) {
//                    Timber.d("unformatted: $unformatted, isPhoneNumberValid: $isPhoneNumberValid")
                    ivClear.visibility = if (unformatted?.isNotEmpty() == true) View.VISIBLE else View.INVISIBLE
//                    mViewModel.onInputChange(unformatted!!, etCode.text.toString())
                }
            })
            setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    ivClear.visibility = if ((v as EditText).text?.isNotEmpty() == true) View.VISIBLE else View.INVISIBLE
                } else {
                    ivClear.visibility = View.INVISIBLE
                }
            }
        }
        with(etCode) {
            filterBlankAndCarriageReturn()
            afterTextChanged {
                ivClear2.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
//                mViewModel.onInputChange(etPhone.replaceBlank(), it)
            }
            setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    ivClear2.visibility = if ((v as EditText).text?.isNotEmpty() == true) View.VISIBLE else View.INVISIBLE
                } else {
                    ivClear2.visibility = View.INVISIBLE
                }
            }
        }
        ivClear.setOnClickListener {
            etPhone.setText("")
        }
        ivClear2.setOnClickListener {
            etCode.setText("")
        }
//        checkBox.setOnCheckedChangeListener { _, isChecked ->
//            Timber.d("isChecked: $isChecked")
//            mViewModel.isAgree = isChecked
//        }
//        btLogin.setOnClickListener {
//            mViewModel.loginByCode()
//        }
//        tvCode.setOnClickListener {
//            mViewModel.sendPhoneCode()
//        }
//        wxLogin.setOnClickListener {
//            mViewModel.sendAuthRequest()
//        }
    }

//    private fun initData() {
//        mViewModel.showDialogLiveData.observe(this, Observer {
//            if (it) showBottomDialog()
//        })
//        mViewModel.isEnableLiveData.observe(this, Observer {
//            mBinding.btLogin.isEnabled = it
//        })
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    mViewModel.sendCodeStatuesFlow.collect {
//                        when (it) {
//                            is State.Error -> ToastUtils.show(it.message)
//                            is State.Success -> ToastUtils.show(it.data)
//                            is State.Loading -> {
//                                mBinding.etCode.setText("")
//                                countDown()
//                            }
//                        }
//                    }
//                }
//                launch {
//                    mViewModel.loginStateFlow.collect {
//                        if (loadingDialog == null) {
//                            loadingDialog = LoadingDialog()
//                        }
//                        when (it) {
//                            is State.Loading -> {
//                                loadingDialog!!.show(supportFragmentManager, LoginActivity::class.simpleName)
//                            }
//                            is State.Error -> {
//                                loadingDialog?.dismiss()
//                                ToastUtils.show(it.message)
//                            }
//                            is State.Success -> {
//                                mViewModel.loggedIn(it.data)
//                                navigateTo()
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    private fun navigateTo() {
        MainActivity.start(this@LoginActivity)
        finish()
    }

    private fun EditText.replaceBlank(): String {
        return text.toString().replace(" ", "")
    }

    private fun countDown() {
        countDownJob?.cancel()
        countDownJob = null
        mBinding.tvCode.apply {
            isEnabled = false
            setTextColor(R.color.grey)
            // 倒计时
            countDownJob = AppUtil.countDow(60, {
                text = String.format("${it}s后重新获取")
            }, {
                text = "重新获取"
                setTextColor(com.chipman.common.R.color.material_indigo_A100)
                isEnabled = true
            }, lifecycleScope)
        }
    }

    private fun showBottomDialog() {
        val dialog = MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            cornerRadius(20f)
            customView(R.layout.dialog_agree)
            cancelOnTouchOutside(false)
            lifecycleOwner(this@LoginActivity)
        }
        val customView = dialog.getCustomView()
        customView.findViewById<Button>(R.id.bt_agree).setOnClickListener {
            mBinding.checkBox.isChecked = true
//            mViewModel.dialogAgreeClick(true)
            dialog.dismiss()
        }
        customView.findViewById<Button>(R.id.bt_disagree).setOnClickListener {
//            mViewModel.dialogAgreeClick(false)
            dialog.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownJob?.cancel()
        countDownJob = null
    }
}