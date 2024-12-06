package com.holat.holat.data.models.tickets

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class File(
    val added_by_customer: Int, // 1
    val created_at: String, // 2023-10-29 11:26:08
    val creator_id: String?= null, // null
    val deleted_at: String?= null, // null
    val file_name: String, // /storage/tickets_files/1698567968-WhatsApp Image 2023-10-29 at 11.22.10 AM.pdf
    val id: Int, // 312371
    val ticket_id: Int, // 688414
    val updated_at: String // 2023-10-29 11:26:08
) : Parcelable{
    class DiffUtils: DiffUtil.ItemCallback<File>(){
        override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
            return oldItem.file_name == newItem.file_name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
            return oldItem == newItem
        }

    }
}