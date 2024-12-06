package com.holat.holat.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.databinding.CustomCompliantBinding
import com.holat.login.utils.Constants
import com.holat.login.utils.Formatter
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener
import com.yariksoffice.lingver.Lingver

/**
Created by Mohamed Mohamed Taha on 2/3/2024
 */
class CompliantAdapter(private val clickListener: ClickListener<DataTicket>) :
    ListAdapter<DataTicket, CompliantAdapter.TicketViewHolder>(DataTicket.DiffUtils()) {
    class TicketViewHolder(val binding: CustomCompliantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: DataTicket, clickListener: ClickListener<DataTicket>) {
            binding.tvIdTicket.text = ticket.ticket_number.toString()
            val currentLanguage = Lingver.getInstance().getLanguage()
            if (currentLanguage == Constants.AR_LANGUAGE) {
                binding.tvSectorValue.text = ticket.main_reason.title_ar
                binding.tvProductValue.text = ticket.sub_reason?.title_ar
            } else {
                binding.tvSectorValue.text = ticket.main_reason.title_en
                binding.tvProductValue.text = ticket.sub_reason?.title_en
            }
            binding.tvDate.text = Formatter.format(
                ticket.created_at,
                Formatter.YYYY_MM_DD_HH_MM_SS,
                Formatter.EE_DD_MMMM_YYYY
            )
            binding.tvTime.text = Formatter.format(
                ticket.created_at,
                Formatter.YYYY_MM_DD_HH_MM_SS,
                Formatter.hh_mm_aa
            )
            binding.cardView.onDebouncedListener { clickListener.onClick(it, ticket) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            CustomCompliantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}