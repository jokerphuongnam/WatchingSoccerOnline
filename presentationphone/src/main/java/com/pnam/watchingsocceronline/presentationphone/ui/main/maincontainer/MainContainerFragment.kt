package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import android.graphics.Typeface
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.background.DownloadVideoService
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentMainContainerBinding
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutLibraryBinding
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutRecyclerOnlineViewBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadResultReceiverCallback
import com.pnam.watchingsocceronline.presentationphone.ui.main.library.LibraryFragmentMain
import com.pnam.watchingsocceronline.presentationphone.ui.main.videocallback.MoreOptionClick
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*

@InternalCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
abstract class MainContainerFragment<VM : MainContainerViewModel> :
    BaseFragment<FragmentMainContainerBinding, VM, MainViewModel>(R.layout.fragment_main_container) {
    override val activityViewModel: MainViewModel by activityViewModels()

    private var _actionbar: ActionBar? = null

    protected val videosBinding: LayoutRecyclerOnlineViewBinding by lazy {
        binding.videos
    }

    protected val libraryBinding: LayoutLibraryBinding by lazy {
        binding.library
    }

    private val searchBinding: LayoutRecyclerOnlineViewBinding by lazy {
        binding.search
    }

    private val searchResultBinding: LayoutRecyclerOnlineViewBinding by lazy {
        binding.searchResults
    }

    private val notificationBinding: LayoutRecyclerOnlineViewBinding by lazy {
        binding.notification
    }

    private val toolbar: MainToolbar by lazy {
        MainToolbar(requireActivity())
    }

    private val alertUnplayedVideo: AlertDialog.Builder by lazy {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.unplayed_video)
            setPositiveButton(getString(R.string.get_notification)) { dialog, id ->
                dialog.cancel()
            }
            setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
        }
    }

    private fun clickVideo(video: Video) {
        if (
            video.date > Calendar.getInstance()
                .apply { timeZone = TimeZone.getDefault() }.timeInMillis
        ) {
            alertUnplayedVideo.setMessage(
                """${getString(R.string.video_will_show)} ${video.showTimeDate}
                        |${getString(R.string.do_you_want_to_receive_noti_when_video_be_shown)}""".trimMargin()
            ).show()

        } else {
            openVideoBottomSheet(video.vid)
        }
    }

    private val videoCallback: ContainerItemCallback<Video> by lazy {
        object : ContainerItemCallback<Video> {
            override fun onLongTouch(data: Video) {
            }

            override fun onClick(data: Video) {
                clickVideo(data)
            }
        }
    }

    private val moreOptionClick: MoreOptionClick by lazy {
        object : MoreOptionClick {
            override fun download(video: Video) {
                viewModel.getVideoDownload(video)
            }

            override fun getNotification(video: Video) {
                viewModel.getNotification(video)
            }

            override fun removeFromHistory(video: Video) {
            }
        }
    }

    internal val videoAdapter: VideosAdapter by lazy {
        VideosAdapter(videoCallback, moreOptionClick)
    }

    private val searchHistoryCallback: ContainerItemCallback<SearchHistory> by lazy {
        object : ContainerItemCallback<SearchHistory> {
            override fun onLongTouch(data: SearchHistory) {

            }

            override fun onClick(data: SearchHistory) {
                searchView.setQuery(data.searchWord, true)
            }
        }
    }

    internal val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(searchHistoryCallback)
    }

    private val searchResultsCallback: ContainerItemCallback<Video> by lazy {
        object : ContainerItemCallback<Video> {
            override fun onLongTouch(data: Video) {

            }

            override fun onClick(data: Video) {
                clickVideo(data)
            }
        }
    }

    internal val searchResultsAdapter: VideosAdapter by lazy {
        VideosAdapter(searchResultsCallback)
    }

    private val notificationVideo: ContainerItemCallback<Notification> by lazy {
        object : ContainerItemCallback<Notification> {
            override fun onLongTouch(data: Notification) {

            }

            override fun onClick(data: Notification) {
                if (data.showTime < Calendar.getInstance().timeInMillis) {
                    openVideoBottomSheet(data.vid)
                } else {
                    showToast(R.string.match_has_not_happened_yet)
                }
            }
        }
    }

    private val notificationsAdapter: NotificationsAdapter by lazy {
        NotificationsAdapter(notificationVideo)
    }

    private fun setUpRecycler() {
        binding.apply {
            if (tag.toString() == LibraryFragmentMain::class.java.simpleName) {
                recyclerContainer.removeViewAt(0)
            } else {
                recyclerContainer.removeViewAt(1)
                videosBinding.list.adapter = videoAdapter
            }
            search.list.adapter = searchAdapter
            searchResults.list.adapter = searchResultsAdapter
            notification.list.adapter = notificationsAdapter
        }
    }

    override val actionBar: ActionBar
        get() = _actionBar ?: synchronized(this) {
            _actionBar ?: (requireActivity() as AppCompatActivity).supportActionBar.also {
                _actionBar = it
            }!!
        }

    private fun hideProfile(){
        toolbar.binding.apply {
            if(isShowSignIn){
                signIn.isVisible = false
            }
            if(isShowAvatar){
                avatar.isVisible = false
            }
        }
    }

    private fun showProfile(){
        toolbar.binding.apply {
            if(isShowSignIn){
                signIn.isVisible = true
            }
            if(isShowAvatar){
                avatar.isVisible = true
            }
        }
    }

    private var isNotification: Boolean = false
    private var isSearch: Boolean = false

    private var _notificationItem: MenuItem? = null
    private val notificationItem: MenuItem get() = _notificationItem!!
    private var _notificationView: TextView? = null
    private val notificationView: TextView
        get() = _notificationView ?: synchronized(this) {
            _notificationView ?: (notificationItem.actionView as TextView).also {
                _notificationView = it
            }
        }

    private val isShowSignIn: Boolean by lazy {
        toolbar.binding.signIn.isVisible
    }

    private val isShowAvatar: Boolean by lazy {
        toolbar.binding.avatar.isVisible
    }

    private val notificationExpandItem: MenuItem.OnActionExpandListener by lazy {
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                isSearch = false
                isNotification = true
                hideProfile()
                notificationView.apply {
                    setText(R.string.notification)
                    setTextColor(context.getColorFromAttr(R.attr.blackWhite))
                    textSize = 24f
                    typeface = Typeface.DEFAULT_BOLD
                }
                binding.recyclerContainer.displayedChild = 3
                viewModel.notification()
                searchItem.isVisible = false
                _searchView = null
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                isNotification = false
                if (!isSearch) {
                    showProfile()
                }
                binding.recyclerContainer.displayedChild = 0
                searchItem.isVisible = true
                _notificationItem = null
                requireActivity().invalidateOptionsMenu()
                return true
            }
        }
    }

    private fun setUpNotification() {
        notificationItem.setOnActionExpandListener(notificationExpandItem)
    }

    private var _searchItem: MenuItem? = null
    private val searchItem: MenuItem get() = _searchItem!!
    private var _searchView: SearchView? = null
    private val searchView: SearchView
        get() = _searchView ?: synchronized(this) {
            _searchView ?: (searchItem.actionView as SearchView).also {
                _searchView = it
            }
        }

    private val searchQuery: SearchView.OnQueryTextListener by lazy {
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.recyclerContainer.displayedChild = 2
                viewModel.searchResult(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.search()
                } else {
                    viewModel.search(newText)
                }
                return true
            }
        }
    }

    private val searchExpandItem: MenuItem.OnActionExpandListener by lazy {
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                isSearch = true
                isNotification = false
                hideProfile()
                notificationItem.actionView.isVisible = false
                binding.recyclerContainer.displayedChild = 1
                searchView.setQuery("", false)
                searchView.setOnQueryTextListener(searchQuery)
                searchView.queryHint = "${getString(R.string.search_video)}…"
                searchView.requestFocus()
                viewModel.search()
                notificationItem.isVisible = false
                _notificationView = null
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                isSearch = false
                if (!isNotification) {
                    showProfile()
                }
                notificationItem.actionView.isVisible = true
                binding.recyclerContainer.displayedChild = 0
                notificationItem.isVisible = true
                _searchView = null
                requireActivity().invalidateOptionsMenu()
                return true
            }
        }
    }

    private var _profileItem: MenuItem? = null
    private val profileItem: MenuItem get() = _profileItem!!

    private fun setUpSearchItem() {
        searchItem.setOnActionExpandListener(searchExpandItem)
    }

    private fun setUpUser() {
        profileItem.actionView
        toolbar.setBinding(profileItem.actionView.findViewById(R.id.container))
        activityViewModel.userLiveData.value?.data?.let { user ->
            viewModel.userLiveData.postValue(user)
        }
    }

    private val notificationItemId: Int by lazy { R.id.notification }
    private val searchItemId: Int by lazy { R.id.search }

    internal fun onCreateOptionsMenu(menu: Menu) {
        val menuInflater: MenuInflater = requireActivity().menuInflater
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        _notificationItem = menu.findItem(notificationItemId)
        _searchItem = menu.findItem(searchItemId)
        _profileItem = menu.findItem(R.id.profile)
        setUpUser()
        setUpNotification()
        setUpSearchItem()
    }

    private val resultReceiverCallBack: DownloadResultReceiverCallback by lazy {
        DownloadResultReceiverCallback()
    }

    private fun viewModelObserve() {
        viewModel.apply {
            userLiveData.observe { user ->
                toolbar.setUser(user)
            }
            searchLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        searchBinding.loading.isVisible = true
                    }
                    is Resource.Success -> {
                        searchBinding.loading.isVisible = false
                        searchAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        searchBinding.loading.isVisible = false
                    }
                }
            }
            searchResultLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        searchResultBinding.loading.isVisible = true
                    }
                    is Resource.Success -> {
                        searchResultBinding.loading.isVisible = false
                        searchResultsAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        searchResultBinding.loading.isVisible = false
                    }
                }
            }
            notificationsLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        notificationBinding.loading.isVisible = true
                    }
                    is Resource.Success -> {
                        notificationBinding.loading.isVisible = false
                        notificationsAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        notificationBinding.loading.isVisible = false
                    }
                }
            }
            viewModel.videosLiveData.takeUnless { tag.toString() == LibraryFragmentMain::class.java.simpleName }
                ?.observe {
                    when (it) {
                        is Resource.Loading -> {
                            videosBinding.loading.isVisible = true
                        }
                        is Resource.Success -> {
                            videosBinding.loading.isVisible = false
                            videoAdapter.submitList(it.data)
                        }
                        is Resource.Error -> {
                            videosBinding.loading.isVisible = false
                        }
                    }
                }
            viewModel.videoDownloadLiveData.observe { video ->
                if (video != null) {
                    showToast(R.string.download_start)
                    DownloadVideoService.startServiceToWithdraw(
                        requireContext(),
                        video,
                        resultReceiverCallBack
                    )
                } else {
                    showToast(R.string.video_downloaded)
                }
            }
        }
    }

    private fun setUpActivityViewModel() {
        activityViewModel.userObservers.add { user ->
            toolbar.setUser(user, activityViewModel.avatarHandle)
        }
    }

    override fun onCreateView() {
        setUpRecycler()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        toolbar.onCreate()
        setUpActivityViewModel()
        viewModelObserve()
        actionBar.title = ""
        onCreateContainerView()
    }

    abstract fun onCreateContainerView()

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        activityViewModel.userLiveData.value?.data?.let { user ->
            viewModel.userLiveData.postValue(user)
        }
        if (isNotification) {
            notificationItem.collapseActionView()
        } else if (isSearch) {
            searchItem.collapseActionView()
        }
        viewModel.getVideos()
    }

    internal lateinit var openVideoBottomSheet: (Long) -> Unit
}