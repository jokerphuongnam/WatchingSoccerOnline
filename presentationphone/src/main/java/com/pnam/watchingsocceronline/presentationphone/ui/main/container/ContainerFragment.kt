package com.pnam.watchingsocceronline.presentationphone.ui.main.container

import android.graphics.Typeface
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pnam.watchingsocceronline.model.model.Notification
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentContainerBinding
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutLibraryBinding
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutRecyclerViewBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.library.LibraryFragment
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import java.util.*

abstract class ContainerFragment<VM : ContainerViewModel> :
    BaseFragment<FragmentContainerBinding, VM>(R.layout.fragment_container) {
    private var _actionbar: ActionBar? = null

    private val toolbar: MainToolbar by lazy {
        MainToolbar(requireActivity() as AppCompatActivity)
    }

    protected abstract val videoCallback: ContainerItemCallback<Video>?

    internal val videoAdapter: VideosAdapter by lazy {
        VideosAdapter(videoCallback)
    }

    private val searchHistoryCallback: ContainerItemCallback<SearchHistory> by lazy {
        object : ContainerItemCallback<SearchHistory> {
            override fun onLongTouch(data: SearchHistory) {

            }

            override fun onClick(data: SearchHistory) {
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
                openVideoBottomSheet(data.vid)
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
                }
            }
        }
    }

    internal val notificationsAdapter: NotificationsAdapter by lazy {
        NotificationsAdapter(notificationVideo)
    }

    private fun setUpRecycler() {
        binding.apply {
            if (tag.toString() == LibraryFragment::class.java.simpleName) {
                recyclerContainer.removeViewAt(0)
            } else {
                recyclerContainer.removeViewAt(1)
                mainBinding.list.apply {
                    adapter = videoAdapter
                    layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                }
            }
            search.list.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
            searchResults.list.apply {
                adapter = searchResultsAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
            notification.list.apply {
                adapter = notificationsAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
        }
    }

    protected val mainBinding: LayoutRecyclerViewBinding by lazy {
        binding.notLibrary
    }

    protected val libraryBinding: LayoutLibraryBinding by lazy {
        binding.library
    }

    override val actionBar: ActionBar
        get() = _actionBar ?: synchronized(this) {
            _actionBar ?: (requireActivity() as AppCompatActivity).supportActionBar.also {
                _actionBar = it
            }!!
        }

    override fun onCreateView() {
        setUpRecycler()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        toolbar.onCreate()
        onCreateContainerView()
    }

    private var isNotification: Boolean = false
    private var isSearch: Boolean = false

    private var _notificationItem: MenuItem? = null
    private val notificationItem: MenuItem get() = _notificationItem!!
    private val notificationTv: TextView by lazy { notificationItem.actionView as TextView }

    private val notificationExpandItem: MenuItem.OnActionExpandListener by lazy {
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                isNotification = true
                toolbar.binding.avatar.isVisible = false
                searchItem.collapseActionView()
                notificationTv.apply {
                    setText(R.string.notification)
                    setTextColor(context.getColorFromAttr(R.attr.blackWhite))
                    textSize = 24f
                    typeface = Typeface.DEFAULT_BOLD
                }
                binding.recyclerContainer.displayedChild = 3
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                isNotification = false
                if (!isSearch) {
                    toolbar.binding.avatar.isVisible = true
                }
                binding.recyclerContainer.displayedChild = 0
                return true
            }
        }
    }

    private fun setUpNotification() {
        notificationItem.setOnActionExpandListener(notificationExpandItem)
    }

    private var _searchItem: MenuItem? = null
    private val searchItem: MenuItem get() = _searchItem!!
    private val searchView: SearchView by lazy { searchItem.actionView as SearchView }

    private val searchExpandItem: MenuItem.OnActionExpandListener by lazy {
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                isSearch = true
                toolbar.binding.avatar.isVisible = false
                notificationItem.actionView.isVisible = false
                searchView.requestFocus()
                searchView.setQuery("", false)
                notificationItem.collapseActionView()
                binding.recyclerContainer.displayedChild = 1
                searchView.queryHint = "${getString(R.string.search_video)}…"
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                isSearch = false
                if (!isNotification) {
                    toolbar.binding.avatar.isVisible = true
                }
                notificationItem.actionView.isVisible = true
                binding.recyclerContainer.displayedChild = 0
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
        toolbar.setBinding(profileItem.actionView.findViewById(R.id.profile))
        toolbar.setUser(viewModel.user)
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

    abstract fun onCreateContainerView()

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        if (isNotification) {

        } else if (isSearch) {

        }
    }

    internal lateinit var openVideoBottomSheet: (Long) -> Unit
}