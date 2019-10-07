package com.mary.mvichat.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<V: BaseViewHolder<T>, T> : RecyclerView.Adapter<V>() {

    protected var items = mutableListOf<T>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: V, position: Int) = holder.initView(items[position])

    abstract fun setList(newList: MutableList<T>)
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal abstract fun initView(model: T)
}