package com.nkmisc88.meowtv

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class MeowTVPlugin : Plugin() {
    override fun load(context: Context) {
        registerMainAPI(MeowTVProvider())
    }
}