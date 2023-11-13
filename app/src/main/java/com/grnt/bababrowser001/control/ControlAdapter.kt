package com.grnt.bababrowser001.control

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.AppStore
import com.grnt.bababrowser001.Components
import com.grnt.bababrowser001.control.topsites.TopSitePagerViewHolder
import com.grnt.bababrowser001.feature.model.TopSite

class ControlAdapter(
    private val interactor: ControlInteractor,
    private val viewLifecycleOwner: LifecycleOwner,
    private val components: Components,
): ListAdapter<AdapterItem, RecyclerView.ViewHolder>(AdapterItemDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
       return when (viewType){
            TopSitePagerViewHolder.LAYOUT_ID ->TopSitePagerViewHolder(
                view = view,
                appStore = components.appStore,
                viewLifecycleOwner = viewLifecycleOwner,
                interactor = interactor,
            )
           else -> throw IllegalStateException()
       }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int,payloads: MutableList<Any>) {
        if (payloads.isNullOrEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            when (holder) {
                is TopSitePagerViewHolder -> {
                    if (payloads[0] is AdapterItem.TopSitePagerPayload) {
                        val payload = payloads[0] as AdapterItem.TopSitePagerPayload
                        holder.update(payload)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder){
            is TopSitePagerViewHolder->{

                var topSites:List<TopSite> =
                    listOf(
                    TopSite.Default(1,"Google","https://www.google.com",1),
                    TopSite.Default(2,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(3,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(4,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(5,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(6,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(7,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(8,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(9,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(10,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(11,"Mozilla","https://www.mozilla.org",1),
                    TopSite.Default(12,"Mozilla","https://www.mozilla.org",1),
                )

                (item as AdapterItem.TopSitePager).topSites
                holder.bind(topSites)
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun getItemViewType(position: Int) = getItem(position).viewType

}

class AdapterItemDiffCallback : DiffUtil.ItemCallback<AdapterItem>(){
    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem) =
        oldItem.sameAs(newItem)

    @Suppress("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem) =
        oldItem.contentsSameAs(newItem)

    override fun getChangePayload(oldItem: AdapterItem, newItem: AdapterItem): Any? {
        return oldItem.getChangePayload(newItem) ?: return super.getChangePayload(oldItem, newItem)
    }

}
sealed class AdapterItem(@LayoutRes val viewType: Int) {
    data class TopSitePager(val topSites: List<TopSite>): AdapterItem(TopSitePagerViewHolder.LAYOUT_ID)
    data class TopSitePagerPayload(
        val changed: Set<Pair<Int, TopSite>>,
    )
    /**
     * True if this item represents the same value as other. Used by [AdapterItemDiffCallback].
     */
    open fun sameAs(other: AdapterItem) = this::class == other::class

    /**
     * Returns a payload if there's been a change, or null if not
     */
    open fun getChangePayload(newItem: AdapterItem): Any? = null

    open fun contentsSameAs(other: AdapterItem) = this::class == other::class
}