package com.holat.holat.ui.home.compliants.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.R
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.databinding.CustomWhiteComplaintBinding
import com.holat.login.utils.Constants
import com.holat.login.utils.Formatter
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener
import com.yariksoffice.lingver.Lingver

/**
Created by Mohamed Mohamed Taha on 2/27/2024
 */
class AllComplaintsAdapter(private val clickListener: ClickListener<DataTicket>) :
    ListAdapter<DataTicket, AllComplaintsAdapter.TicketViewHolder>(DataTicket.DiffUtils()) {
    class TicketViewHolder(val binding: CustomWhiteComplaintBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: DataTicket, clickListener: ClickListener<DataTicket>) {
            binding.tvIdTicket.text = ticket.ticket_number.toString()
            //   binding.tvSector.text = ticket.sector_reply
            binding.tvDetails.text = ticket.details
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
            val currentLanguage = Lingver.getInstance().getLanguage()
            when (ticket.status?.use_replacement) {
                0 -> {
                    if (currentLanguage == Constants.AR_LANGUAGE) {
                        when (ticket.status.title_ar) {
                            binding.root.context.getString(R.string.close) -> {
                                changeStatus(ticket.status.title_ar, ticket.status.color)
                            }

                            binding.root.context.getString(R.string.under_process) -> {
                                changeStatus(ticket.status.title_ar, ticket.status.color)
                            }

                            binding.root.context.getString(R.string.new_) -> {
                                changeStatus(ticket.status.title_ar, ticket.status.color)
                            }
                        }
                    } else {
                        when (ticket.status.title_en) {
                            binding.root.context.getString(R.string.close) -> {
                                changeStatus(ticket.status.title_en, ticket.status.color)
                            }

                            binding.root.context.getString(R.string.under_process) -> {
                                changeStatus(ticket.status.title_en, ticket.status.color)
                            }

                            binding.root.context.getString(R.string.new_) -> {
                                changeStatus(ticket.status.title_en, ticket.status.color)
                            }
                        }
                    }
                }

                1 -> {
                    if (currentLanguage == Constants.AR_LANGUAGE) {
                        when (ticket.status.replacement_status?.title_ar) {
                            binding.root.context.getString(R.string.close) -> {
                                changeStatus(
                                    ticket.status.title_ar,
                                    ticket.status.replacement_status.color
                                )
                            }

                            binding.root.context.getString(R.string.under_process) -> {
                                changeStatus(
                                    ticket.status.title_ar,
                                    ticket.status.replacement_status.color
                                )
                            }

                            binding.root.context.getString(R.string.new_) -> {
                                changeStatus(
                                    ticket.status.title_ar,
                                    ticket.status.replacement_status.color
                                )
                            }
                        }
                    } else {
                        when (ticket.status.replacement_status?.title_en) {
                            binding.root.context.getString(R.string.close) -> {
                                changeStatus(
                                    ticket.status.title_en,
                                    ticket.status.replacement_status.color
                                )
                            }

                            binding.root.context.getString(R.string.under_process) -> {
                                changeStatus(
                                    ticket.status.title_en,
                                    ticket.status.replacement_status.color
                                )
                            }

                            binding.root.context.getString(R.string.new_) -> {
                                changeStatus(
                                    ticket.status.title_en,
                                    ticket.status.replacement_status.color
                                )
                            }
                        }
                    }
                }
            }

            binding.cardView.onDebouncedListener { clickListener.onClick(it, ticket) }
        }

        private fun changeStatus(status: String, color: String) {
            binding.tvState.text = status
            binding.tvState.setBackgroundColor(Color.parseColor(color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            CustomWhiteComplaintBinding.inflate(
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