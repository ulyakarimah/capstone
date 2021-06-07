package com.example.capstoneproject

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.Activity.DesainAddUpdateActivity
import com.example.capstoneproject.databinding.ItemDesainBinding

class DesainAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<DesainAdapter.DesainViewHolder>() {
    private val listDesain = ArrayList<Desain>()
    fun setListDesain(listDesain: List<Desain>) {
        val diffCallback = DesainDiffCallback(this.listDesain, listDesain)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listDesain.clear()
        this.listDesain.addAll(listDesain)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesainViewHolder {
        val binding = ItemDesainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DesainViewHolder, position: Int) {
        holder.bind(listDesain[position])
    }
    override fun getItemCount(): Int {
        return listDesain.size
    }
    inner class DesainViewHolder(private val binding: ItemDesainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(desain: Desain) {
            with(binding) {
                tvDesainkuNumb.text = desain.judul
                with(itemView) {
                    Glide.with(this)
                        .load(desain.gambar)
                        .into(binding.fotoDesain)
                }

                cvItemdesain.setOnClickListener {
                    val intent = Intent(activity, DesainAddUpdateActivity::class.java)
                    intent.putExtra(DesainAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(DesainAddUpdateActivity.EXTRA_DESAIN, desain)
                    activity.startActivityForResult(intent, DesainAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }
}


