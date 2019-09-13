package com.nithinkumar.water


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.nithinkumar.water.fragment.Item
import kotlinx.android.synthetic.main.water_intake_list_item.view.*

class ItemAdapter(val itemClick: (position: Int, item: Item) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.water_intake_list_item, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pos = position % items.size
        holder.bind(items[pos])
        holder.itemView.setOnClickListener {
            itemClick(pos, items[pos])
        }
    }

    override fun getItemCount() = Integer.MAX_VALUE

    fun setItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Item) {
        view.list_item_text.text = item.title
        view.list_item_icon.setAnimation(item.icon)
        view.list_item_icon.playAnimation()
        view.list_item_icon.repeatCount = INFINITE
    }
}