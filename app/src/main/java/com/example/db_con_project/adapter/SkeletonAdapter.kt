package com.example.db_con_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.db_con_project.R

class SkeletonAdapter(
    private val isLoading: Boolean,
    private val data: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SKELETON = 0
        private const val TYPE_DATA = 1
    }

    inner class SkeletonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.textItem)
        fun bind(str: String) { text.text = str }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading) TYPE_SKELETON else TYPE_DATA
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_SKELETON) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_skeleton, parent, false)
            SkeletonViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_skeleton, parent, false)
            DataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder && !isLoading) {
            holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = if (isLoading) 5 else data.size
}
