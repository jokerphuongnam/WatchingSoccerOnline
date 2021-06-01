package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.BottomSheetWatchingVideoBinding
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import com.pnam.watchingsocceronline.presentationphone.ui.main.comment.CommentFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.custom.CustomBottomSheet
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
@Suppress("DEPRECATION")
class WatchVideoBottomSheet(
    private val activity: AppCompatActivity,
    private val viewModel: MainViewModel,
    private val binding: BottomSheetWatchingVideoBinding,
    private val paddingBottom: Int
) {

    internal fun onInit() {
        viewModel.userObservers.add(::userObserver)
        onCreateView()
        onCreateBehavior()
        onCreateViewModel()
        onCreateVideoController()
        onCreateRecyclerView()
    }

    private val behavior: CustomBottomSheet<MotionLayout> by lazy {
        CustomBottomSheet.from(binding.container)
    }

    private fun onCreateBehavior() {
        behavior.addBottomSheetCallback(bottomSheetCallBack)
        behavior.peekHeight = paddingBottom * 2
    }

    private var _exoPlayer: SimpleExoPlayer? = null
    private val exoPlayer: SimpleExoPlayer get() = _exoPlayer!!
    private val videoController: VideoController by lazy {
        VideoController(activity.findViewById(R.id.video_controller))
    }
    private lateinit var trackSelector: DefaultTrackSelector

    private fun fullScreen(){
        /**
         * make full screen
         * */
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun onCreateView() {
        binding.apply {
            close.setOnClickListener(closeEvent)
            avatarHandle = viewModel.avatarHandle
            playPause.setOnClickListener {
                if (exoPlayer.playWhenReady) {
                    binding.playPause.setImageResource(R.drawable.ic_pause)
                } else {
                    binding.playPause.setImageResource(R.drawable.ic_play)
                }
                exoPlayer.playWhenReady = !exoPlayer.playWhenReady
            }
            comment.setOnClickListener {
                CommentFragment().apply {
                    show(
                        this@WatchVideoBottomSheet.activity.supportFragmentManager,
                        CommentFragment::class.java.simpleName
                    )
                }
            }
        }
    }

    private var isRunVideo = false

    private fun stopVideo() {
        isRunVideo = false
        binding.videoView.player.stop()
        _exoPlayer = null
        binding.videoView.player = null
        binding.container.isVisible = false
    }

    private fun <D> MutableLiveData<D>.observe(observer: Observer<D>) {
        observe(activity, observer)
    }

    private fun onCreateViewModel() {
        viewModel.apply {
            videoLiveData.observe { video ->
                when (video) {
                    is Resource.Loading -> {
                        binding.loading.isVisible = true
                    }
                    is Resource.Success -> {
                        binding.loading.isVisible = false
                        if (video.data == null) {
                            binding.video = null
                            stopVideo()
                            return@observe
                        }
                        if (binding.video == null || binding.video!!.vid != video.data.vid) {
                            binding.video = video.data
                            if (isRunVideo) {
                                stopVideo()
                            }
                            loadVideoFromUrl(video.data.url)
                        } else {
                            behavior.state = STATE_EXPANDED
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
            recommendLiveData.observe { videos ->
                when (videos) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        recommendAdapter.submitList(videos.data?.toMutableList())
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    private fun onCreateVideoController() {
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

    private val recommendItemClick: ContainerItemCallback<Video> by lazy {
        object : ContainerItemCallback<Video> {
            override fun onLongTouch(data: Video) {

            }

            override fun onClick(data: Video) {
                viewModel.openVideo(data.vid)
                show()
            }
        }
    }

    private val recommendAdapter: RecommendAdapter by lazy {
        RecommendAdapter(recommendItemClick)
    }

    private fun onCreateRecyclerView() {
        binding.recommends.adapter = recommendAdapter
    }

    internal fun collapseEvent(): Boolean =
        (binding.container.progress == 1.0F).takeUnless { it }?.apply {
            behavior.state = STATE_COLLAPSED
        } ?: true

    private fun loadVideoFromUrl(url: String) {
        loadVideo(Uri.parse(url))
    }

    private fun loadVideo(uri: Uri) {
        isRunVideo = true
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
                    STATE_COLLAPSED -> {
                        binding.videoView.useController = false
                    }
                    else -> {

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
                        (paddingBottom.toFloat() * (1.0 - slideOffset)).toInt()
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
                        binding.playPause.setImageResource(R.drawable.ic_play)
                    }
                    Player.STATE_IDLE -> {
                        binding.playPause.setImageResource(R.drawable.ic_pause)
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

    private fun userObserver(user: User) {
        binding.user = user
    }

    internal fun show() {
        behavior.state = STATE_EXPANDED
        binding.container.isVisible = true
        binding.scroll.fullScroll(ScrollView.FOCUS_UP)
        viewModel.getRecommendVideo()
        binding.container.setPadding(0, 0, 0, 0)
    }

    private fun dismiss() {
        viewModel.closeVideo()
    }
}