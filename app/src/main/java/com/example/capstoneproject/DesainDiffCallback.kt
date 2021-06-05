package com.example.capstoneproject

import androidx.recyclerview.widget.DiffUtil

class DesainDiffCallback (private val mOldDesainList: List<Desain>, private val mNewDesainList: List<Desain>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldDesainList.size
    }
    override fun getNewListSize(): Int {
        return mNewDesainList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldDesainList[oldItemPosition].id == mNewDesainList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldDesainList[oldItemPosition]
        val newEmployee = mNewDesainList[newItemPosition]
        return oldEmployee.judul == newEmployee.judul && oldEmployee.gambar == newEmployee.gambar
    }
}