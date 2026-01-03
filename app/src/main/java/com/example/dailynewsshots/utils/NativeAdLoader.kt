package com.example.dailynewsshots.utils

import android.content.Context
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd

class NativeAdLoader(private val context: Context) {

    private var nativeAd: NativeAd? = null

    fun load(onLoaded: (NativeAd?) -> Unit) {
        val adLoader = AdLoader.Builder(
            context,
            "ca-app-pub-3106037298707105/5040099250" // TEST Native ID
        )
            .forNativeAd {
                nativeAd = it
                onLoaded(it)
            }
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun destroy() {
        nativeAd?.destroy()
    }
}
