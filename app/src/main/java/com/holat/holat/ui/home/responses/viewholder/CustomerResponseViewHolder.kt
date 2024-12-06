package com.holat.holat.ui.home.responses.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.databinding.CustomRowCustomerResponseBinding
import com.holat.login.models.responses.DataResponses
import com.holat.login.utils.Formatter

/**
Created by Mohamed Mohamed Taha on 3/1/2024
 */
class CustomerResponseViewHolder(private val binding: CustomRowCustomerResponseBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun sendMessageHolder(dataResponses: DataResponses) {
        binding.textViewMessage.text = dataResponses.details
        binding.textViewSenderTime.text = Formatter.format(
            dataResponses.created_at,
            Formatter.YYYY_MM_DD_HH_MM_SS,
            Formatter.EE_DD_MMMM_YYYY
        )
    }
}