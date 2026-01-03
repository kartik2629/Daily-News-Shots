package com.example.dailynewsshots.utils

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.dailynewsshots.R
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

object NativeAdBinder {

    fun bind(nativeAd: NativeAd, adView: NativeAdView) {

        adView.headlineView = adView.findViewById(R.id.adHeadline)
        adView.imageView = adView.findViewById(R.id.adImage)
        adView.callToActionView = adView.findViewById(R.id.adCallToAction)

        (adView.headlineView as TextView).text = nativeAd.headline
        nativeAd.images.firstOrNull()?.drawable?.let {
            (adView.imageView as ImageView).setImageDrawable(it)
        }
        (adView.callToActionView as Button).text = nativeAd.callToAction

        adView.setNativeAd(nativeAd)
    }
}
