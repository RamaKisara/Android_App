package com.example.crudapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.Database.Mainan
import com.example.crudapp.R
import kotlinx.android.synthetic.main.adapter_mainan.view.*
import kotlinx.android.synthetic.main.adapter_mainan.view.*

class MainanAdapter (private val allMainan: ArrayList<Mainan>, private val listener: OnAdapterListener) : RecyclerView.Adapter<MainanAdapter.MainanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainanViewHolder {
        return MainanViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_mainan, parent, false)
        )
    }

    override fun getItemCount() = allMainan.size

    override fun onBindViewHolder(holder: MainanViewHolder, position: Int) {
        val mainan = allMainan[position]
        holder.view.text_merk.text = mainan.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(mainan)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(mainan)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(mainan)
        }
    }

    class MainanViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Mainan>) {
        allMainan.clear()
        allMainan.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(mainan: Mainan)
        fun onDelete(mainan: Mainan)
        fun onUpdate(mainan: Mainan)
    }
}