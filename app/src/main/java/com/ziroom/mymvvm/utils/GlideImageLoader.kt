package com.ziroom.mymvvm.utils

import android.view.View
import android.widget.ImageView
import coil.load
import com.stx.xhb.androidx.XBanner
import com.ziroom.mymvvm.network.entity.BannerBean

class GlideImageLoader : XBanner.XBannerAdapter {

    override fun loadBanner(banner: XBanner?, model: Any?, view: View?, position: Int) {
        (view as ImageView).load((model as BannerBean).xBannerUrl.toString())
    }

}