package com.holat.holat.ui.home.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.R
import com.holat.holat.databinding.CustomUploadFileBinding
import com.holat.login.models.UploadImageOrFile
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener

/**
Created by Mohamed Mohamed Taha on 2/3/2024
 */
class UploadImageOrFileAdapter(private val clickListener: ClickListener<Int>) :
    ListAdapter<UploadImageOrFile, UploadImageOrFileAdapter.UploadImageOrFileHolder>(
        UploadImageOrFile.DiffUtils()
    ) {
    class UploadImageOrFileHolder(val binding: CustomUploadFileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: UploadImageOrFile, clickListener: ClickListener<Int>) {
            val indexImages = ticket.fileName.indexOf("Images/")
            val indexPdf = ticket.fileName.indexOf("document:")
            if (indexImages != -1) {
                val justImageName = ticket.fileName.subSequence(
                    startIndex = indexImages,
                    endIndex = ticket.fileName.length
                )
                binding.tvFileName.text = justImageName
            } else if (indexPdf != -1) {
                val justPdfName = ticket.fileName.subSequence(
                    startIndex = indexPdf,
                    endIndex = ticket.fileName.length
                )
                binding.tvFileName.text = justPdfName
            } else {
                binding.tvFileName.text = ticket.fileName
            }
            try {
                val size = ticket.fileData?.length() ?: 0L
                val sizeByKb = size / 1024
                ticket.fileSize = sizeByKb.toString()
                binding.tvFileSize.text = String.format(
                    binding.root.context.getString(
                        R.string.kb
                    ), ticket.fileSize
                )
                //    val image = stringToBitMap(ticket.fileDataAsBase64)
//                if (image != null){
//                    binding.imFile.setImageBitmap(image)
//                }

            } catch (e: Exception) {

            }

            binding.imDelete.onDebouncedListener { clickListener.onClick(it, adapterPosition) }
        }

        private fun stringToBitMap(encodedString: String?): Bitmap? {
            return try {
                val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
                //            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //            bitmap.compress(Bitmap.CompressFormat.JPEG,90,baos);
                BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            } catch (e: java.lang.Exception) {
                e.message
                null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadImageOrFileHolder {
        return UploadImageOrFileHolder(
            CustomUploadFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UploadImageOrFileHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}