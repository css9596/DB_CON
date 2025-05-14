package com.example.db_con_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.db_con_project.R
import com.example.db_con_project.data.Feature

class FeatureAdapter(
    private val features: List<Feature>,
    private val onClick: (Feature) -> Unit
) : RecyclerView.Adapter<FeatureAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDesc: TextView = itemView.findViewById(R.id.textDesc)
        init {
            itemView.setOnClickListener {
                onClick(features[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_feature, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = features[position]
        holder.textTitle.text = feature.title
        holder.textDesc.text = feature.description
    }

    override fun getItemCount() = features.size
}

