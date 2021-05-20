package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.BottomSheetWatchVideoBinding

class WatchVideoBottomSheet(private val transaction: FragmentTransaction, val video: Video) :
    BottomSheetDialogFragment() {
    private val binding: BottomSheetWatchVideoBinding
        get() = _binding!!
    private var _binding: BottomSheetWatchVideoBinding? = null
    private val viewModel: WatchVideoViewModel by viewModels()

    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var videoController: VideoController
    private lateinit var trackSelector: DefaultTrackSelector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bottom_sheet_watch_video,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.videoLiveData.observe(viewLifecycleOwner) {
            binding.video = it
            loadVideo(it.video)
        }
        viewModel.videoLiveData.postValue(video)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _behavior = BottomSheetBehavior.from(binding.container.parent as View)
        (_behavior as BottomSheetBehavior<View>).state = BottomSheetBehavior.STATE_EXPANDED
        behavior.addBottomSheetCallback(bottomSheetCallback)
    }

    private fun loadVideo(url: String) {
        val uri: Uri = Uri.parse(url)
        val loadControl = DefaultLoadControl()
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        trackSelector = DefaultTrackSelector(
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        )
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(
            requireContext(), trackSelector, loadControl
        )
        val factory: HttpDataSource.Factory = DefaultHttpDataSourceFactory("exoplayer_video")
        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
        val mediaSource: MediaSource = ExtractorMediaSource(
            uri, factory, extractorsFactory, null, null
        )
        binding.videoView.player = simpleExoPlayer
        binding.videoView.keepScreenOn = true
        simpleExoPlayer.prepare(mediaSource)
        simpleExoPlayer.playWhenReady = true
        simpleExoPlayer.addListener(object : Player.EventListener {
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
        })
    }

    private var _behavior: BottomSheetBehavior<*>? = null

    internal var behavior: BottomSheetBehavior<*>
        get() = _behavior!!
        set(value) {
            _behavior = value
        }

    private val bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback by lazy {
        object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        }
    }

    fun show() {
        show(transaction, javaClass.simpleName)
        isCancelable = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}