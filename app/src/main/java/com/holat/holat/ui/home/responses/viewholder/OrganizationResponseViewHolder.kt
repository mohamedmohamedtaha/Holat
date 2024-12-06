package com.holat.holat.ui.home.responses.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.databinding.CustomRowOrganizationResponseBinding
import com.holat.login.models.responses.DataResponses
import com.holat.login.utils.Formatter

/**
Created by Mohamed Mohamed Taha on 3/1/2024
 */
class OrganizationResponseViewHolder(private val binding: CustomRowOrganizationResponseBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun receiveMessageHolder(dataResponses: DataResponses) {
        binding.textViewMessageReceiver.text = dataResponses.details
        binding.textViewTimeReceiver.text = Formatter.format(
            dataResponses.created_at,
            Formatter.YYYY_MM_DD_HH_MM_SS,
            Formatter.EE_DD_MMMM_YYYY
        )

    }
}