package com.holat.holat.ui.home.compliants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.holat.holat.data.models.tickets.File
import com.holat.holat.databinding.CustomFilesBinding
import com.holat.login.hilt.IMAGE_URL
import com.holat.login.utils.Formatter
import com.holat.login.utils.Formatter.EE_DD_MMMM_YYYY
import com.holat.login.utils.Formatter.YYYY_MM_DD_HH_MM_SS
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.showImage

/**
Created by Mohamed Mohamed Taha on 1/31/2024
 */
class CompliantFilesAdapter(private val clickListener: ClickListener<File>) :
    ListAdapter<File, CompliantFilesAdapter.TicketFilesViewHolder>(File.DiffUtils()) {
    class TicketFilesViewHolder(val binding: CustomFilesBinding) : ViewHolder(binding.root) {
        fun bind(file: File, clickListener: ClickListener<File>) {
            val fullUrl = IMAGE_URL + file.file_name
            binding.root.showImage(fullUrl, binding.imageFile)
            binding.tvFileName.text = file.file_name
            binding.tvFileSize.text =
                Formatter.format(file.created_at, YYYY_MM_DD_HH_MM_SS, EE_DD_MMMM_YYYY)
            binding.constraint.onDebouncedListener { clickListener.onClick(it, file) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketFilesViewHolder {
        return TicketFilesViewHolder(
            CustomFilesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TicketFilesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}