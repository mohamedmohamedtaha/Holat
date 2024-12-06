package com.holat.holat.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.databinding.CustomSearchBinding
import com.holat.login.models.LookUpModel
import com.holat.login.utils.listener.ClickListener

class SearchAdapter(
    private val onClickListener: ClickListener<LookUpModel>
) :
    ListAdapter<LookUpModel, SearchAdapter.SearchViewHolder>(DiffUtil()) {

    class SearchViewHolder(private val binding: CustomSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            lookUpModel: LookUpModel,
            onClickListener: ClickListener<LookUpModel>
        ) {
            binding.constraintLayout.setOnClickListener {
                onClickListener.onClick(it, lookUpModel)
            }

            binding.tvName.text = lookUpModel.listName
            //binding.executePendingBindings()
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<LookUpModel>() {
        override fun areItemsTheSame(
            oldItem: LookUpModel,
            newItem: LookUpModel
        ): Boolean {
            return oldItem.lookUpListId == newItem.lookUpListId
        }

        override fun areContentsTheSame(
            oldItem: LookUpModel,
            newItem: LookUpModel
        ): Boolean {
            return oldItem.listName == newItem.listName
        }
    }

    fun retrieveDataSelected(): MutableList<LookUpModel> = currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder(
            CustomSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }
}