package com.example.db_con_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.db_con_project.R

class InfinityScrollAdapter(
    private val items: MutableList<String>,
    private var isLoading: Boolean = false
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_LOADING = 1
    }

    // 일반 아이템 ViewHolder
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textItem: TextView = itemView.findViewById(R.id.textItem)
        fun bind(data: String) {
            textItem.text = data
        }
    }

    // 로딩(스켈레톤) ViewHolder
    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == items.size) TYPE_LOADING else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_infinity, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
        }
        // LoadingViewHolder는 별도 바인딩 없음
    }

    override fun getItemCount(): Int = items.size + if (isLoading) 1 else 0

    fun showLoading(show: Boolean) {
        if (isLoading == show) return
        isLoading = show
        if (show) notifyItemInserted(items.size)
        else notifyItemRemoved(items.size)
    }

    fun addItems(newItems: List<String>) {
        val start = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }
}
