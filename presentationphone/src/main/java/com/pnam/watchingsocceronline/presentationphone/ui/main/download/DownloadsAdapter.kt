package com.pnam.watchingsocceronline.presentationphone.ui.main.download

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemVideoDownloadBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class DownloadsAdapter(
    private val containerItemCallback: ContainerItemCallback<Download>? = null,
) : BaseListAdapter<Download, DownloadsAdapter.DownloadViewHolder>(DIFF) {

    internal fun processDownload(download: Download) {
        processCallbacks.forEach {
            it.value.invoke(download)
        }
    }

    private val processCallbacks: MutableMap<String, ((Download) -> Unit)> by lazy { mutableMapOf() }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getItem(position)
        if (processCallbacks[item.vid] == null) {
            holder.progressCallback().let {
                processCallbacks[item.vid] = it
            }
        }
        holder.onBind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        return DownloadViewHolder(parent, containerItemCallback)
    }

    @InternalCoroutinesApi
    class DownloadViewHolder(
        parent: ViewGroup,
        containerItemCallback: ContainerItemCallback<Download>? = null
    ) : BaseListAdapter.BaseViewHolder<ItemVideoDownloadBinding, Download>(
        parent,
        R.layout.item_video_download,
        containerItemCallback
    ) {
        override fun onBind(data: Download) {
            binding.video = data
        }

        fun progressCallback(): (Download) -> Unit {
            return {
                if (it.vid == binding.video!!.vid) {
                    binding.downloadProgress.apply {
                        if (it.downloadProcess < 100) {
                            isVisible = false
                        } else {
                            progress = it.downloadProcess.toFloat()
                            isVisible = true
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF: DiffUtil.ItemCallback<Download> by lazy {
            object : DiffUtil.ItemCallback<Download>() {
                override fun areItemsTheSame(oldItem: Download, newItem: Download): Boolean {
                    return oldItem.vid == newItem.vid
                }

                override fun areContentsTheSame(oldItem: Download, newItem: Download): Boolean {
                    return oldItem.equals(newItem)
                }
            }
        }
    }
}