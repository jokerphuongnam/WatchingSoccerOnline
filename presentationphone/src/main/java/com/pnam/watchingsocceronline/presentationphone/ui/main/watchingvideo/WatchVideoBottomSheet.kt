package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.BottomSheetWatchingVideoBinding
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel


@Suppress("DEPRECATION")
class WatchVideoBottomSheet(
    private val activity: AppCompatActivity,
    private val viewModel: MainViewModel,
    private val binding: BottomSheetWatchingVideoBinding,
    private val paddingBottom: Int
) {

    internal fun onInit() {
        behavior.peekHeight = paddingBottom * 2
        onCreateView()
        createBehavior()
    }

    private fun createBehavior() {
        behavior.addBottomSheetCallback(bottomSheetCallBack)
    }

    private val behavior: BottomSheetBehavior<MotionLayout> by lazy {
        from(binding.container)
    }

    private var _exoPlayer: SimpleExoPlayer? = null
    private val exoPlayer: SimpleExoPlayer get() = _exoPlayer!!
    private val videoController: VideoController by lazy {
        VideoController(activity.findViewById(R.id.video_controller))
    }
    private lateinit var trackSelector: DefaultTrackSelector

    private fun onCreateView() {
        viewModel.videoLiveData.observe(activity) { video ->
            if (video == null) {
                binding.video = null
                return@observe
            }
            if (binding.video == null || binding.video!!.vid != video.vid) {
                binding.video = video
                loadVideo(video.video)
            } else {
                behavior.state = STATE_EXPANDED
            }
        }
        /**
         * make full screen
         * */
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpVideoController()
        binding.apply {
            close.setOnClickListener(closeEvent)
            avatarHandle = this@WatchVideoBottomSheet.avatarHandle
            playPause.setOnClickListener {
                Log.e("ccccccccccccccccccccccc", exoPlayer.playWhenReady.toString())
            }
        }
        recyclerView()
    }

    private fun setUpVideoController() {
        videoController.apply {
            fullScreenEvent = { isFullScreen ->
                activity.requestedOrientation = if (isFullScreen) {
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }
            }
            collapseEvent = { collapseEvent() }
        }
    }

    internal fun collapseEvent(): Boolean =
        (binding.container.progress == 1.0F).takeUnless { it }?.apply {
            collapseEvent?.invoke()
            behavior.state = STATE_COLLAPSED
        } ?: true

    internal var collapseEvent: (() -> Unit)? = null

    private fun recyclerView() {
        binding.recommends.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    private val avatarHandle: Function1<ImageRequest.Builder, Unit> by lazy {
        {
            it.transformations(CircleCropTransformation())
            it.crossfade(true)
            it.placeholder(R.drawable.ic_error)
        }
    }

    private fun loadVideo(url: String) {
        val uri: Uri = Uri.parse(url)
        val loadControl = DefaultLoadControl()
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        trackSelector = DefaultTrackSelector(
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        )
        _exoPlayer = ExoPlayerFactory.newSimpleInstance(
            activity, trackSelector, loadControl
        )
        val factory: HttpDataSource.Factory = DefaultHttpDataSourceFactory("exoplayer_video")
        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
        val mediaSource: MediaSource = ExtractorMediaSource(
            uri, factory, extractorsFactory, null, null
        )
        binding.videoView.player = _exoPlayer
        binding.videoView.keepScreenOn = true
        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
        exoPlayer.addListener(playerEvent)
    }

    internal var motionProgressChanged: ((progress: Float) -> Unit)? = null

    internal fun show() {
        behavior.state = STATE_EXPANDED
        binding.container.isVisible = true
    }

    private fun dismiss() {
        binding.container.isEnabled = false
        binding.videoView.player.release()
        _exoPlayer = null
        binding.container.isVisible = false
        viewModel.videoLiveData.postValue(null)
    }

    private val bottomSheetCallBack: BottomSheetCallback by lazy {
        object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_EXPANDED -> {
                        binding.videoView.useController = true
                    }
                    STATE_DRAGGING -> {
                        binding.videoView.useController = false
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                (1.0F - slideOffset).let {
                    binding.container.progress = it
                    motionProgressChanged?.invoke(it)
                    binding.container.setPadding(
                        0,
                        0,
                        0,
                        (paddingBottom.toFloat() * slideOffset).toInt()
                    )
                }
            }
        }
    }

    internal val isShow: Boolean get() = binding.container.isVisible

    private val closeEvent: View.OnClickListener by lazy {
        View.OnClickListener {
            dismiss()
        }
    }

    private val playerEvent: Player.EventListener by lazy {
        object : Player.EventListener {
            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

            }

            override fun onTracksChanged(
                trackGroups: TrackGroupArray?,
                trackSelections: TrackSelectionArray?
            ) {
            }

            override fun onLoadingChanged(isLoading: Boolean) {
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.videoView.useController = false
                    }
                    Player.STATE_READY -> {
                        binding.loading.visibility = View.GONE
                        binding.videoView.useController = true
                    }
                }
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
            }

            override fun onPositionDiscontinuity(reason: Int) {
            }

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
            }

            override fun onSeekProcessed() {

            }
        }
    }

    companion object {
        internal const val VIDEO = "VIDEO"
    }
}