package com.holat.login.models

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.recyclerview.widget.DiffUtil
import java.io.File

/**
Created by Mohamed Mohamed Taha on 2/23/2024
 */
class UploadImageOrFile{
    var id: String? = null
    var ticketNumber: String? = null
    var fileName = ""
    var fileDataAsBase64: String? = null
    var bitMap: Bitmap? = null
    var fileSize =""
    var fileData: File? =null
    var contentType: String? =null

    class DiffUtils: DiffUtil.ItemCallback<UploadImageOrFile>(){
        override fun areItemsTheSame(oldItem: UploadImageOrFile, newItem: UploadImageOrFile): Boolean {
            return oldItem.fileName == newItem.fileName
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: UploadImageOrFile, newItem: UploadImageOrFile): Boolean {
            return oldItem == newItem
        }

    }
}