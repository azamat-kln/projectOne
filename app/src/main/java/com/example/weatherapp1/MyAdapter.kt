package com.example.weatherapp1

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp1.databinding.RecyclerItemBinding
import com.example.weatherapp1.domain.Model

class MyAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var dataList: List<Model> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        Log.i("MainActivityAdapter", "inside MyAdapter class")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = DataBindingUtil.inflate<RecyclerItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item,
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item, clickListener)
    }

    class ViewHolder(private val recyclerItemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(recyclerItemBinding.root) {

        fun bind(item: Model, clickListener: ClickListener) {
            recyclerItemBinding.mocky = item

            recyclerItemBinding.textView.setOnClickListener {
                clickListener.onClick(item)
            }
        }
    }
}

class ClickListener(val clickListener: (Model) -> Unit) {
    fun onClick(model: Model) = clickListener(model)
}