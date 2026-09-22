package com.nkmisc88.meowtv

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class MeowTVProvider : MainAPI() {

    override var mainUrl = "https://meowtv.ru"

    override var name = "MeowTV"

    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime
    )

    override var lang = "en"

    override suspend fun search(query: String): List<SearchResponse> {
        return emptyList()
    }

    override suspend fun load(url: String): LoadResponse? {
        val document = app.get(url).document

        val title = document.select("h1").firstOrNull()?.text()
            ?: document.select("title").text()
                .substringBefore(" | ")
                .substringBefore(" - MeowTV")
                .trim()

        if (title.isBlank()) {
            return null
        }

        val poster = document.select("meta[property=og:image]")
            .attr("content")
            .takeIf { it.isNotBlank() }

        val description = document.select("meta[property=og:description]")
            .attr("content")
            .takeIf { it.isNotBlank() }

        val year = Regex("""\b(19|20)\d{2}\b""")
            .find(document.text())
            ?.value
            ?.toIntOrNull()

 return newMovieLoadResponse(
    name = title,
    url = url,
    type = TvType.Movie,
    dataUrl = url
) {
    this.posterUrl = poster
    this.plot = description
    this.year = year
}
            this.posterUrl = poster
            this.plot = description
            this.year = year
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        // Playback will be implemented separately after we confirm
        // the current MeowTV playback/API structure.
        return false
    }
}
