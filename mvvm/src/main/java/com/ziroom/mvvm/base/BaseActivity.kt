package com.ziroom.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.ziroom.mvvm.R
import com.ziroom.mvvm.app.MVVMManager
import com.ziroom.mvvm.event.Message
import java.lang.reflect.ParameterizedType

/**
 * activity基类
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected var mBinding: DB? = null
    private var dialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding()
        lifecycle.addObserver(viewModel)
        //注册 UI事件
        registorDefUIChange()
        initView(savedInstanceState)
        initData()
    }

    abstract fun layoutId(): Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()

    /**
     * DataBinding
     */
    private fun initViewDataBinding() {
        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
        if (ViewDataBinding::class.java != cls && ViewDataBinding::class.java.isAssignableFrom(cls)) {
            mBinding = DataBindingUtil.setContentView(this, layoutId())
            mBinding?.lifecycleOwner = this
        } else setContentView(layoutId())
        createViewModel()
    }

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        viewModel.defUI.showDialog.observe(this, {
            showLoading()
        })
        viewModel.defUI.dismissDialog.observe(this, {
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(this, {
            ToastUtils.showShort(it)
        })
        viewModel.defUI.msgEvent.observe(this, {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: Message) {}

    /**
     * 打开等待框
     */
    private fun showLoading() {
        (dialog ?: MaterialDialog(this)
            .cancelable(false)
            .cornerRadius(8f)
            .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
            .lifecycleOwner(this)
            .maxWidth(R.dimen.dialog_width))
            .show()
    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
            viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
                .get(tClass) as VM
        }
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return MVVMManager.getConfig().viewModelFactory() ?: super.getDefaultViewModelProviderFactory()
    }
}