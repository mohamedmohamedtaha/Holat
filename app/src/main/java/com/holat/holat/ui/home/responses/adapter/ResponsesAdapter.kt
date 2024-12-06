package com.holat.holat.ui.home.responses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.databinding.CustomRowCustomerResponseBinding
import com.holat.holat.databinding.CustomRowOrganizationResponseBinding
import com.holat.holat.ui.home.responses.viewholder.CustomerResponseViewHolder
import com.holat.holat.ui.home.responses.viewholder.OrganizationResponseViewHolder
import com.holat.login.models.responses.DataResponses
import com.holat.login.utils.Constants.RECEIVER
import com.holat.login.utils.Constants.SENDER

/**
Created by Mohamed Mohamed Taha on 3/1/2024
 */
class ResponsesAdapter(
    private val response: List<DataResponses>
) : PagingDataAdapter<DataResponses, RecyclerView.ViewHolder>(DataResponses.DiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == SENDER) {
            OrganizationResponseViewHolder(
                CustomRowOrganizationResponseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            CustomerResponseViewHolder(
                CustomRowCustomerResponseBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int = response.count()
    override fun getItemViewType(position: Int): Int {
        return if (SENDER == response[position].added_by_customer) {
            SENDER
        } else {
            RECEIVER
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = response[position]
        when (holder.itemViewType) {
            SENDER -> {
                holder as OrganizationResponseViewHolder
                holder.receiveMessageHolder(message)
            }

            else -> {
                holder as CustomerResponseViewHolder
                holder.sendMessageHolder(message)
            }
        }
    }

    fun setItems(items: ArrayList<DataResponses>, page: Int) {
        if (page == 1) {
            items.clear()
            items.addAll(items)
        } else {
            items.addAll(items)
        }
        notifyDataSetChanged()
    }
}

























