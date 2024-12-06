package com.holat.holat.ui.home.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.databinding.CustomNotificationBinding
import com.holat.login.utils.Formatter
import com.holat.login.utils.Formatter.EE_DD_MMMM_YYYY
import com.holat.login.utils.Formatter.YYYY_MM_DD_HH_MM_SS
import com.holat.login.utils.Formatter.hh_mm_aa
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener

/**
Created by Mohamed Mohamed Taha on 1/31/2024
 */
class NotificationAdapter(private val clickListener: ClickListener<DataTicket>) :
    ListAdapter<DataTicket, NotificationAdapter.NotificationViewHolder>(DataTicket.DiffUtils()) {
    class NotificationViewHolder(val binding: CustomNotificationBinding) :
        ViewHolder(binding.root) {
        fun bind(ticket: DataTicket, clickListener: ClickListener<DataTicket>) {
            binding.tvDetails.text = ticket.details
            binding.tvGetDate.text =
                Formatter.format(ticket.created_at, YYYY_MM_DD_HH_MM_SS, EE_DD_MMMM_YYYY)
            binding.tvTime.text = Formatter.format(ticket.created_at, YYYY_MM_DD_HH_MM_SS, hh_mm_aa)
            binding.cardView.onDebouncedListener { clickListener.onClick(it, ticket) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            CustomNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}