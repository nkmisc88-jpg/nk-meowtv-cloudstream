package com.nkmisc88.meowtv

import com.lagradost.cloudstream3.plugins.BasePlugin
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin

@CloudstreamPlugin
class MeowTVPlugin : BasePlugin() {

    override fun load() {
        registerMainAPI(MeowTVProvider())
    }
}
