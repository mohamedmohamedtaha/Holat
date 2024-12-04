package com.holat.login.models.responses

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

data class DataResponses(
    val added_by_customer: Int, // 1
    val auto_added: Int, // 0
    val created_at: String, // 2023-11-01 16:15:18
    val deleted_at: Any, // null
    val details: String, // شكرا لكم
    val `external`: Int, // 0
    val files: List<File>,
    val from: Int, // 415270
    val from_type: String, // App\Models\Client
    val id: Int, // 1337669
    val is_draft: Int, // 1
    val section: String, // Self Service - Change status
    val sent: Int, // 0
    val subject: Any, // null
    val ticket_id: Int, // 688414
    val to: Int, // 9
    val to_type: String, // App\Models\Hospital
    val updated_at: String, // 2023-11-01 16:15:18
    val user_id: Int // 2200
){
    class DiffUtils: DiffUtil.ItemCallback<DataResponses>(){
        override fun areItemsTheSame(oldItem: DataResponses, newItem: DataResponses): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataResponses, newItem: DataResponses): Boolean {
            return oldItem == newItem
        }

    }
}