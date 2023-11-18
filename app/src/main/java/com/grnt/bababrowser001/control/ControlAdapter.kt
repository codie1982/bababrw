package com.grnt.bababrowser001.control

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AnyRes
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.Components
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.control.banner.BannerPagerViewHolder
import com.grnt.bababrowser001.control.topsites.TopSitePagerViewHolder
import com.grnt.bababrowser001.feature.model.Banner
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
           BannerPagerViewHolder.LAYOUT_ID ->BannerPagerViewHolder(
               view = view,
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
        getItem(position)
        when (holder){
            is TopSitePagerViewHolder->{

                var topSites: List<TopSite> =
                    listOf(
                        TopSite.Default(
                            1, "Google", "https://www.google.com",
                            R.drawable.google_logo, 1,
                        ),
                    TopSite.Default(2,"YouTube","https://www.youtube.com",R.drawable.youtube_logo,1),
                    TopSite.Default(3,"Mozilla","https://www.mozilla.org",R.drawable.mozilla_logo,1),
                    TopSite.Default(4,"Trendyol","https://www.trendyol.com",R.drawable.trendyol_logo,1),
                    TopSite.Default(5,"Hürriyet","https://www.hurriyet.com",R.drawable.hurriyet_logo,1),
                    TopSite.Default(6,"TürkTelekom","https://www.turktelekom.com.tr",R.drawable.tt_logo,1),
                    TopSite.Default(7,"Facebook","https://www.facebook.com",R.drawable.facebook_logo,1),
                    TopSite.Default(8,"Sahibinden","https://www.sahibinden.com",R.drawable.sahibinden_logo,1),
                    TopSite.Default(9,"Hepsiburada","https://www.hepsiburada.com",R.drawable.hepsiburada_logo,1),
                    TopSite.Default(10,"Türkiye.gov.tr","https://www.mozilla.turkiye.gov.tr",R.drawable.turkiye_logo,1),
                    TopSite.Default(11,"N11","https://www.n11.com",R.drawable.n11_logo,1),
                    TopSite.Default(12,"Netflix","https://www.netflix.com",R.drawable.netflix_logo,1),
                )
                holder.bind(topSites)
            }
            is BannerPagerViewHolder->{
                /*var banners:List<Banner> = listOf(
                    Banner(1,
                        title = "Hepsi Burada",
                        url = "https://www.hepsiburada.com",
                        image = R.drawable.hb_banner
                    )
                )*/
                holder.bind()
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
    data class TopSitePager(var topSites: List<TopSite>): AdapterItem(TopSitePagerViewHolder.LAYOUT_ID)
    data class BannerPager(var banners: List<Banner>): AdapterItem(BannerPagerViewHolder.LAYOUT_ID)
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