package com.mary.mvichat.presentation.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallback<T>(protected val oldList: List<T>,
                                       protected val newList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}