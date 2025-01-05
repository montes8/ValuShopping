package com.tayler.valushopping.ui.home.other.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.vale.uitaylibrary.utils.setOnClickUiTayDelay
import com.tayler.valushopping.BR
import com.tayler.valushopping.databinding.RowOtherBinding
import com.tayler.valushopping.entity.ItemModel
class OtherAdapter(var onClickAdmin: ((ItemModel) -> Unit)? = null) :
    RecyclerView.Adapter<OtherAdapter.AdminViewHolder>() {

    var adminList: List<ItemModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        return AdminViewHolder(
            RowOtherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.bind(adminList[position])
    }

    override fun getItemCount(): Int = adminList.size



    inner class AdminViewHolder(private val binding: RowOtherBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(other: ItemModel) {
            binding.setVariable(BR.other, other)
            binding.executePendingBindings()
            binding.ctlAdmin.setOnClickUiTayDelay{onClickAdmin?.invoke(other) }
        }
    }
}