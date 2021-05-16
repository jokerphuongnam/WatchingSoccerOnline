package com.pnam.watchingsocceronline.presentationphone.extensionview

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class PlayerVideoExtensionProperty {
    private var _simpleExoPlayer: SimpleExoPlayer? = null
    internal fun getSimpleExoPlayer(context: Context): SimpleExoPlayer =
        _simpleExoPlayer ?: synchronized(this) {
            _simpleExoPlayer ?: ExoPlayerFactory.newSimpleInstance(
                context, trackSelector, DefaultLoadControl()
            ).also { _simpleExoPlayer = it }
        }

    private var _trackSelector: DefaultTrackSelector? = null
    internal val trackSelector: DefaultTrackSelector
        get() = _trackSelector ?: synchronized(this) {
            _trackSelector ?: DefaultTrackSelector(
                AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())
            ).also { _trackSelector = it }
        }

    private var _mediaSource: MediaSource? = null
    internal val mediaSource: MediaSource
        get() = _mediaSource ?: synchronized(this) {
            ExtractorMediaSource(
                Uri.parse(_url),
                DefaultHttpDataSourceFactory("exoplayer_video"),
                DefaultExtractorsFactory(),
                null,
                null
            )
        }.also {
            _mediaSource = it
        }

    private var _url: String = ""

    internal var url: String?
        get() = _url
        set(value) {
            _url = value ?: destroyVideo()
        }

    private fun destroyVideo(): String {
        _simpleExoPlayer = null
        _trackSelector = null
        _mediaSource = null
        return ""
    }
}

private val PlayerView.playerVideoExtensionProperty: PlayerVideoExtensionProperty by lazy {
    PlayerVideoExtensionProperty()
}

val PlayerView.simpleExoPlayer: SimpleExoPlayer
    get() = playerVideoExtensionProperty.getSimpleExoPlayer(context)

val PlayerView.trackSelector: DefaultTrackSelector
    get() = playerVideoExtensionProperty.trackSelector

val PlayerView.mediaSource: MediaSource
    get() = playerVideoExtensionProperty.mediaSource

var PlayerView.url: String
    get() {
        return playerVideoExtensionProperty.url!!
    }
    set(value) {
        playerVideoExtensionProperty.url = value
    }

fun PlayerView.destroyVideo() {
    playerVideoExtensionProperty.url = null
}