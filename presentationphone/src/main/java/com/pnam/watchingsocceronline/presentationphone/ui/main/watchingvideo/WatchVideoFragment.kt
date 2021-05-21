package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
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
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentWatchingVideoBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment


@Suppress("DEPRECATION")
class WatchVideoFragment :
    BaseFragment<FragmentWatchingVideoBinding, WatchVideoViewModel>(R.layout.fragment_watching_video) {
    override val viewModel: WatchVideoViewModel by viewModels()

    private var _exoPlayer: SimpleExoPlayer? = null
    private val exoPlayer: SimpleExoPlayer get() = _exoPlayer!!
    private val videoController: VideoController by lazy {
        VideoController(requireView().findViewById(R.id.video_controller))
    }
    private lateinit var trackSelector: DefaultTrackSelector

    override fun createView() {
        viewModel.videoLiveData.observe(viewLifecycleOwner) {
            binding.video = it
            loadVideo(it.video)
        }
        /**
         * make full screen
         * */
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        viewModel.videoLiveData.postValue(
            requireActivity().intent.getParcelableExtra(
                VIDEO
            )
        )
        setUpVideoController()
        binding.apply {
            close.setOnClickListener(closeEvent)
            avatarHandle = this@WatchVideoFragment.avatarHandle
            container.setTransitionListener(transitionListener)
            playPause.setOnClickListener {
                Log.e("ccccccccccccccccccccccc", exoPlayer.playWhenReady.toString())
            }
        }
        videoViewEvent()
        recyclerView()
    }

    private fun videoViewEvent() {
        binding.videoView.apply {
            controllerHideOnTouch = false
            hideController()
            setOnClickListener {
                Log.e("ccccccccccccc", "click")
            }
        }
    }

    private fun setUpVideoController() {
        videoController.apply {
            fullScreenEvent = { isFullScreen ->
                requireActivity().requestedOrientation = if (isFullScreen) {
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }
            }
            collapseEvent = { collapseEvent() }
        }
    }

    internal fun collapseEvent() {
        binding.container.progress = 0.0f
        collapseEvent?.invoke()
    }

    internal var collapseEvent: (() -> Unit)? = null

    private fun recyclerView() {
        binding.recommends.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
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
            requireContext(), trackSelector, loadControl
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

    private val transitionListener: MotionLayout.TransitionListener by lazy {
        object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {

            }

            override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {
                Log.e("ccccccccccccccccccc", progress.toString())
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {

            }

            override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) {

            }
        }
    }

    private fun collapsed() {
        binding.apply {
            loading.visibility = View.VISIBLE
        }
    }

    private fun dragging() {
        binding.apply {
            loading.visibility = View.GONE
            videoView.hideController()
            videoView.controllerHideOnTouch = false
            videoView.isEnabled = false
        }
    }

    private fun expanded() {
        binding.apply {
            loading.visibility = View.VISIBLE
            videoView.controllerHideOnTouch = true
            videoView.isEnabled = true
        }
    }

    private val closeEvent: View.OnClickListener by lazy {
        View.OnClickListener {
            binding.videoView.player.release()
            _exoPlayer = null
            requireActivity().supportFragmentManager.popBackStack(
                javaClass.simpleName,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
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
                    }
                    Player.STATE_READY -> {
                        binding.loading.visibility = View.GONE
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