package com.ziroom.mymvvm.ui.me

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.ziroom.mymvvm.R
import com.ziroom.mymvvm.databinding.ItemUsedwebBinding
import com.ziroom.mymvvm.network.entity.UsedWeb

/**
 *   @auther : Aleyn
 *   time   : 2019/11/14
 */

class MeWebAdapter :
    BaseQuickAdapter<UsedWeb, BaseDataBindingHolder<ItemUsedwebBinding>>(R.layout.item_usedweb) {

    override fun convert(holder: BaseDataBindingHolder<ItemUsedwebBinding>, item: UsedWeb) {
        holder.dataBinding?.itemData = item
        holder.dataBinding?.executePendingBindings()
    }

}