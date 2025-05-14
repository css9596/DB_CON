package com.example.db_con_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.db_con_project.R

class MicroInteractionAdapter(
    private val items: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<MicroInteractionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.textItem)
        fun bind(str: String) {
            text.text = str
            itemView.setOnClickListener {
                // 마이크로 인터랙션: 클릭 애니메이션
                itemView.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction {
                    itemView.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                }.start()
                onClick(str)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_infinity, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int = items.size
}
