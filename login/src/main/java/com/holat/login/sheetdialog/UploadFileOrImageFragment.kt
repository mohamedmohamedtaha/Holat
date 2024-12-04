package com.holat.login.sheetdialog

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.viewbinding.ViewBinding
import com.holat.login.R
import com.holat.login.base.BaseBottomSheetDialogFragment
import com.holat.login.databinding.FragmentUploadFileOrImageBinding
import com.holat.login.utils.DialogUtil
import com.holat.login.utils.FileUtils
import com.holat.login.utils.FileUtilsNew
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.showSnackBar
import com.holat.login.models.UploadImageOrFile
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class UploadFileOrImageFragment : BaseBottomSheetDialogFragment() {
    private lateinit var binding: FragmentUploadFileOrImageBinding

    //IF cropMode = 1 this mean you want to crop the image
    //private var cropMode = 1
    private var cropMode = 0
    private var mCurrentPhotoPath: String = ""
    private var imagePickerResult: ImagePickerResult? = null
    private var pickerType: Int = VIEW_IMAGE
    private val viewType: Int = VIEW_DEFAULT
    private val imageQuality: Int = 100
    private var photoURI: Uri? = null
//    constructor() {
//        this.viewType = VIEW_DEFAULT
//    }
//
//    @SuppressLint("ValidFragment")
//    constructor(pickerType: Int, viewType: Int) : this(viewType) {
//        this.pickerType = pickerType
//    }
//
//    @SuppressLint("ValidFragment")
//    constructor(viewType: Int) {
//        this.viewType = viewType
//    }

    fun setImagePickerResult(imagePickerResult: ImagePickerResult) {
        this.imagePickerResult = imagePickerResult
    }

    public fun setCropMode(cropMode: Int) {
        this.cropMode = cropMode
    }

    override fun getBinding(): ViewBinding {
        binding = FragmentUploadFileOrImageBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        isCancelable = true
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onClick() {
        binding.cameraBtn.onDebouncedListener {
//            activityResultLauncher.launch(REQUIRED_PERMISSIONS)

            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsCameraResult.launch(PERMISSIONS_CAMERA)
            } else {
                dispatchTakePictureIntent()
            }
            //requireActivity().cameraIntentWithoutCompress()
        }
        binding.GalleryBtn.onDebouncedListener {
            requestPermissions()
        }
        binding.personalFileBtn.onDebouncedListener {
            openFile()
        }
    }

    private fun openPdf() {
        //  val file = File(Environment.getExternalStorageDirectory().absolutePath)
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("application/pdf")
        //intent.setDataAndType(Uri.fromFile(file), "application/pdf")
        // intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        requireActivity().startActivity(intent)
    }

    private fun openGallery() {
        if (pickerType == VIEW_VIDEO) {
            val type = "video/*"
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            i.type = type
            loadVideoLauncher.launch(i)
            // startActivityForResult(i, RESULT_LOAD_VIDEO)
        } else {
            var photoURI: Uri? = null
            val openGalleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //  var photoFile: File? = null
            try {
                //   photoFile = createImageFile()
                photoURI = createImageUri()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            if (photoURI != null) {
                openGalleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                //Grant uri permission for all packages
                val resInfoList = requireContext().packageManager.queryIntentActivities(
                    openGalleryIntent,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
                for (resolveInfo in resInfoList) {
                    val packageName = resolveInfo.activityInfo.packageName
                    requireContext().grantUriPermission(
                        packageName,
                        photoURI,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                }
                loadImageLauncher.launch(openGalleryIntent)
            }
        }
    }

    private fun openFile() {
//        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//            addCategory(Intent.CATEGORY_OPENABLE)
//            type = "application/pdf"
//        }
//        startActivityForResult(intent,100)
        //  var fileURI: Uri? = null
        //val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        //intent.addCategory(Intent.CATEGORY_OPENABLE)
        //  intent.setType("application/pdf")
        //intent.setDataAndType(Uri.fromFile(file), "application/pdf")
        // intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        // requireActivity().startActivity(intent)


        //  val openPdfIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        //   if (openPdfIntent.resolveActivity(requireActivity().packageManager) != null) {
        //  var photoFile: File? = null
//                try {
//                    //   photoFile = createImageFile()
//                   // fileURI = createImageUri()
//                } catch (ex: IOException) {
//                    ex.printStackTrace()
//                }
        //  if (photoURI != null) {
        //   takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        //Grant uri permission for all packages
//                    val resInfoList = requireContext().packageManager.queryIntentActivities(
//                        takePictureIntent,
//                        PackageManager.MATCH_DEFAULT_ONLY
//                    )
//                    for (resolveInfo in resInfoList) {
//                        val packageName = resolveInfo.activityInfo.packageName
//                        requireContext().grantUriPermission(
//                            packageName, photoURI,
//                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        )
//                    }
        requestPermissionsForFiles()
        //   permissionsOpenDocument.launch(arrayOf("application/pdf"))
        // }
        //}
    }

    private fun newOpenCamera(selectedImageUri: Uri) {
//        try {
//            requireContext().contentResolver?.openFileDescriptor(selectedImageUri,"w")?.use {
//
//            }
//        }
    }

    private fun newOpenVideo() {
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val selection = "${MediaStore.Video.Media.DURATION} >= ?"
        val selectionArgs = arrayOf(
            java.util.concurrent.TimeUnit.MILLISECONDS.convert(
                5,
                java.util.concurrent.TimeUnit.MINUTES
            ).toString()
        )
        val query = requireContext().contentResolver?.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection, selection, selectionArgs, "${MediaStore.Video.Media.DURATION} ASC"
        )
        query?.use { cutsor ->
            val idColumn = cutsor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cutsor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = cutsor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = cutsor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            while (cutsor.moveToNext()) {
                //Here we will use the column indexes that we found above
                val id = cutsor.getLong(idColumn);
                val name = cutsor.getString(nameColumn)
                val duration = cutsor.getInt(durationColumn)
                val size = cutsor.getInt(sizeColumn)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id
                )
                //Add to your list
                // var videoList += Video(contentUri,name,duration,size)
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        if (pickerType == VIEW_IMAGE) {
            photoURI = null
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "New Picture")
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
            photoURI = requireActivity().contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            takeImageLauncher.launch(photoURI)
//            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
//                //  var photoFile: File? = null
//                try {
//                    //   photoFile = createImageFile()
//                    photoURI = createImageUri()
//                } catch (ex: IOException) {
//                    ex.printStackTrace()
//                }
//                if (photoURI != null) {
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                    //Grant uri permission for all packages
//                    val resInfoList = requireContext().packageManager.queryIntentActivities(
//                        takePictureIntent,
//                        PackageManager.MATCH_DEFAULT_ONLY
//                    )
//                    for (resolveInfo in resInfoList) {
//                        val packageName = resolveInfo.activityInfo.packageName
//                        requireContext().grantUriPermission(
//                            packageName, photoURI,
//                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        )
//                    }
//                    takeImageLauncher.launch(photoURI)
//                }
//            }
        } else {
            var photoURI: Uri? = null
            val takePictureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
                var videoFile: File? = null
                try {
                    videoFile = createVideoFile()
                    photoURI = FileProvider.getUriForFile(
                        requireContext(), "com.example.login.provider", videoFile
                    )
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
                if (videoFile != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    //Grant uri permission for all packages
                    val resInfoList = requireContext()
                        .packageManager
                        .queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY)
                    for (resolveInfo in resInfoList) {
                        val packageName = resolveInfo.activityInfo.packageName
                        requireContext().grantUriPermission(
                            packageName,
                            photoURI,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                    }
                    takeVideoLauncher.launch(takePictureIntent)
                    //  startActivityForResult(takePictureIntent, REQUEST_IMAGE_AND_VIDEO)
                }
            }
        }

    }


    //check for app directory create when take photo
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val destPath =
            File(requireContext().getExternalFilesDir("${File.separator}WOT")!!.absolutePath)
        //  val file = File(requireContext().externalCacheDir?.absolutePath, "${File.separator}WOT")
        // val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "${File.separator}WOT")
        // storageDir.mkdirs()
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            destPath  /* directory */
        )
        mCurrentPhotoPath = image.absolutePath
        return image
    }

    //check for app directory create when take photo
    @Throws(IOException::class)
    private fun createImageUri(): Uri {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val imageFileName = "JPEG_$timeStamp"
        return getUriForPreQ(imageFileName)
    }


    private fun getUriForPreQ(fileName: String): Uri {
        val folder = File("${requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)}")
        //  val folder = File("${requireContext().getExternalFilesDir(null)}")
        folder.mkdirs()
        val file = File(folder, fileName)
        if (file.exists())
            file.delete()
        file.createNewFile()
        mCurrentPhotoPath = file.absolutePath
        return FileProvider.getUriForFile(
            requireContext(), "com.example.login.provider", file
        )
    }

    private fun getUriForPreQCrop(fileName: String): Uri {
        val destPath = File("${requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        //  val destPath = File("${requireContext().getExternalFilesDir(null)}")
        val image = File.createTempFile(
            fileName, /* prefix */
            ".jpg", /* suffix */
            destPath  /* directory */
        )
        if (image.parentFile?.exists() == false) image.parentFile?.mkdir()
        return Uri.fromFile(image)
    }

    //check for app directory create when take photo
    @Throws(IOException::class)
    private fun createCroppedImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_cropped"
        val destPath = File(context?.getExternalFilesDir("${File.separator}WOT")!!.absolutePath)
        //val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "${File.separator}WOT")
        //  storageDir.mkdirs()
        return File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            destPath  /* directory */
        )
    }


    //check for app directory create when take photo
    @Throws(IOException::class)
    private fun createCroppedImageUri(): Uri {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val imageFileName = "JPEG_${timeStamp}_cropped"
        return getUriForPreQCrop(imageFileName)
    }

    @Throws(IOException::class)
    private fun createVideoFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val videoFileName = "JPEG_" + timeStamp + "_"
        val storageDir = File("${requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        //  val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            videoFileName, /* prefix */
            ".mp4", /* suffix */
            storageDir      /* directory */
        )
        mCurrentPhotoPath = image.absolutePath
        return image
    }

    private val permissionsCameraResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                dispatchTakePictureIntent()
            }
        }
    private val permissionsOpenDocument =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { url ->
            if (url != null) {
                try {
                    //   mCurrentPhotoPath = url.path.toString()
//                    val uploadImageOrFile = UploadImageOrFile()
//                    uploadImageOrFile.fileName = mCurrentPhotoPath.toString()

                    //  mCurrentPhotoPath = FileUtils.getFile(context, url).absolutePath
                    //mCurrentPhotoPath = FileUtils.compressImage(mCurrentPhotoPath)
                    val path = FileUtilsNew.getRealPathFromURI_API19(context, url)
                    mCurrentPhotoPath = path
                    //  mCurrentPhotoPath = FileUtils.compressImage(mCurrentPhotoPath)
                    val filename: String =
                        mCurrentPhotoPath.substring(mCurrentPhotoPath.lastIndexOf("/") + 1)

//                    val bitmap:Bitmap  = FileUtils.compressImage(mCurrentPhotoPath,requireContext())
//                    val myBase64Image: String = FileUtils.encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100)
//                    val show = FileUtils.createTempFile(bitmap,requireContext())

                    val uploadImageOrFile = UploadImageOrFile()
                    uploadImageOrFile.fileName = filename
                    uploadImageOrFile.fileData = File(mCurrentPhotoPath)
                    uploadImageOrFile.contentType = "application/pdf"
//                    uploadImageOrFile.fileDataAsBase64 = myBase64Image
//                    uploadImageOrFile.bitMap = bitmap
                    //  uploadImageOrFile.fileData = FileUtils.getFile(context, url)
                    imagePickerResult?.onResult(uploadImageOrFile)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                dismiss()
            }
        }

    //        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//            if (permissions.all { it.value }) {
//            dispatchTakePictureIntent()
//            }
//        }
    private val permissionsGalleryResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }
    private val permissionsGalleryResultAbove_33 =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                openGallery()
            }
        }

    // For Crop Image whether from Camera or Gallery
    private val cropImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val result = CropImage.getActivityResult(it.data)
            if (it.resultCode == Activity.RESULT_OK) {
                val imagePath = FileUtils.getPath(context, result.uri)
                mCurrentPhotoPath = imagePath
                // imagePickerResult?.onResult(mCurrentPhotoPath)
                dismiss()
            }
        }

    // For open Camera
    private val takeImageLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            //  if (mCurrentPhotoPath != null) {
            if (cropMode == 0) {
//                    mCurrentPhotoPath = FileUtils.compressImage(mCurrentPhotoPath)
                if (imagePickerResult != null) {
                    //     mCurrentPhotoPath = FileUtils.getFile(context, photoURI).absolutePath
                    //  mCurrentPhotoPath = FileUtils.compressImage(mCurrentPhotoPath)
                    mCurrentPhotoPath = FileUtils.getRealPathFromURI(requireContext(), photoURI)
                    val filename: String =
                        mCurrentPhotoPath.substring(mCurrentPhotoPath.lastIndexOf("/") + 1)

                    val bitmap: Bitmap =
                        FileUtils.compressImage(mCurrentPhotoPath, requireContext())
                    val myBase64Image: String =
                        FileUtils.encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100)
                    val show = FileUtils.createTempFile(bitmap, requireContext())

                    val uploadImageOrFile = UploadImageOrFile()
                    uploadImageOrFile.fileName = filename
                    uploadImageOrFile.fileDataAsBase64 = myBase64Image
                    uploadImageOrFile.bitMap = bitmap
                    uploadImageOrFile.fileData = show

                    imagePickerResult?.onResult(uploadImageOrFile)
                }
                //imagePickerResult?.onResult(mCurrentPhotoPath)
                dismiss()
            } else {
                try {
                    val croppedFileUri = createCroppedImageUri()
                    //  val croppedFileUri = Uri.fromFile(createCroppedImageFile())
                    Log.d(
                        "photoFilexx",
                        "croppedFileUri $croppedFileUri  mCurrentPhotoPath $mCurrentPhotoPath"
                    )
                    val cropImage = CropImage.activity(Uri.fromFile(File(mCurrentPhotoPath!!)))
                    cropImage.setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("Crop")
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setOutputUri(Uri.fromFile(File(mCurrentPhotoPath!!)))
                        // .setCropMenuCropButtonTitle("Done")
                        .setOutputCompressQuality(imageQuality)
                        .setOutputUri(croppedFileUri)
                    cropImageLauncher.launch(cropImage.getIntent(requireContext()))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            //    }
        }

    // For open Gallery
    private val loadImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (cropMode == 0) {
                if (it.data != null) {
                    try {
                        mCurrentPhotoPath = FileUtils.getFile(context, it.data?.data).absolutePath
                        //  mCurrentPhotoPath = FileUtils.compressImage(mCurrentPhotoPath)
                        val filename: String =
                            mCurrentPhotoPath.substring(mCurrentPhotoPath.lastIndexOf("/") + 1)

                        val bitmap: Bitmap =
                            FileUtils.compressImage(mCurrentPhotoPath, requireContext())
                        val myBase64Image: String =
                            FileUtils.encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100)
                        val show = FileUtils.createTempFile(bitmap, requireContext())

                        val uploadImageOrFile = UploadImageOrFile()
                        uploadImageOrFile.fileName = filename
                        uploadImageOrFile.fileDataAsBase64 = myBase64Image
                        uploadImageOrFile.bitMap = bitmap
                        uploadImageOrFile.fileData = show
                        imagePickerResult?.onResult(uploadImageOrFile)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    dismiss()
                }
            } else {
                if (it.data != null) {
                    try {
                        mCurrentPhotoPath = FileUtils.getFile(context, it.data?.data).absolutePath
                        //    mCurrentPhotoPath = FileUtils.compressImage(mCurrentPhotoPath)
                        val croppedFileUri = createCroppedImageUri()
                        // val croppedFileUri = Uri.fromFile(createCroppedImageFile())
                        val cropImage = CropImage.activity(Uri.fromFile(File(mCurrentPhotoPath!!)))
                        cropImage.setGuidelines(CropImageView.Guidelines.ON)
                            .setActivityTitle("Crop")
                            .setCropShape(CropImageView.CropShape.RECTANGLE)
                            // .setCropMenuCropButtonTitle("Done")
                            .setOutputCompressQuality(imageQuality)

                            .setOutputUri(croppedFileUri)
                        cropImageLauncher.launch(cropImage.getIntent(requireContext()))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

    // For open video
    private val takeVideoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        try {
//                       String selectedImagePath = it;
//
//                       startActivityForResult(new Intent(getActivity(), TrimmerActivity.class)
//                                       .putExtra(TrimmerActivity.EXTRA_VIDEO_PATH, selectedImagePath)
//                                       .putExtra(TrimmerActivity.VIDEO_LENGTH, toCheckVideoLength(Uri.fromFile(new File(mCurrentPhotoPath))))
//                               , REQUEST_TRIM_VIDEO);
//                   }
//        catch (e:java.lang.Exception) {
//                       e.printStackTrace();
//                   }
        }

    // For load video
    private val loadVideoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            /*try {
                          Uri selectedImageUri = data.getData();
                          String selectedImagePath = FileUtils.getPath(getActivity(), selectedImageUri);

                          startActivityForResult(new Intent(getActivity(), TrimmerActivity.class)
                                  .putExtra(TrimmerActivity.EXTRA_VIDEO_PATH, selectedImagePath)
                                  .putExtra(TrimmerActivity.VIDEO_LENGTH, toCheckVideoLength(selectedImageUri)), REQUEST_TRIM_VIDEO);

                      } catch (Exception e) {
                          e.printStackTrace();
                      }*/

        }

    interface ImagePickerResult {
        fun onResult(data: UploadImageOrFile?)
    }

    companion object {
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
        const val PERMISSIONS_GALLERY = Manifest.permission.READ_EXTERNAL_STORAGE
        const val PERMISSIONS_CAMERA = Manifest.permission.CAMERA
        val REQUEST_EXTERNAL_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        val PERMISSIONS_GALLERY_ABOVE_33 =
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO)
        const val RESULT_LOAD_VIDEO = 6
        const val REQUEST_IMAGE_AND_VIDEO = 4
        const val VIEW_IMAGE = 1
        const val VIEW_VIDEO = 2
        const val VIEW_FILE = 3
        const val VIEW_DEFAULT = 0
        const val VIEW_CAMERA = 1
    }

    private fun requestPermissionsForFiles() {


        //filter permissions array in order to get permissions that have not been granted
        val notGrantedPermissions = REQUEST_EXTERNAL_STORAGE.filterNot { permission ->
            ContextCompat.checkSelfPermission(
                requireActivity(),
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (notGrantedPermissions.isNotEmpty()) {
            //   check if permission was previously denied and return a boolean value
            val showRationale = notGrantedPermissions.any { permission ->
                shouldShowRequestPermissionRationale(permission)
            }
            //if true, explain to user why granting this permission is important
            if (showRationale) {
                DialogUtil
                DialogUtil.showMessageWithYesNoMaterialDesign(
                    requireActivity(),
                    getString(R.string.storage_permission),
                    getString(R.string.message_storage_permission),
                    yesListener = { _, _ ->
                        permissionsOpenDocument.launch(arrayOf("application/pdf"))

                    }
                ) { _, _ -> binding.root.showSnackBar(getString(R.string.read_media_storage_denied)) }
            } else {
                //launch the videoPermission ActivityResultContract
                permissionsOpenDocument.launch(arrayOf("application/pdf"))
            }
        } else {
            permissionsOpenDocument.launch(arrayOf("application/pdf"))
        }
    }

    private fun requestPermissions() {
        //check the API level
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            //filter permissions array in order to get permissions that have not been granted
            val notGrantedPermissions = PERMISSIONS_GALLERY_ABOVE_33.filterNot { permission ->
                ContextCompat.checkSelfPermission(
                    requireActivity(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
            if (notGrantedPermissions.isNotEmpty()) {
                //   check if permission was previously denied and return a boolean value
                val showRationale = notGrantedPermissions.any { permission ->
                    shouldShowRequestPermissionRationale(permission)
                }
                //if true, explain to user why granting this permission is important
                if (showRationale) {
                    DialogUtil
                    DialogUtil.showMessageWithYesNoMaterialDesign(
                        requireActivity(),
                        getString(R.string.storage_permission),
                        getString(R.string.message_storage_permission),
                        yesListener = { _, _ ->
                            permissionsGalleryResultAbove_33.launch(
                                notGrantedPermissions.toTypedArray()
                            )
                        }
                    ) { _, _ -> binding.root.showSnackBar(getString(R.string.read_media_storage_denied)) }
                } else {
                    //launch the videoPermission ActivityResultContract
                    permissionsGalleryResultAbove_33.launch(notGrantedPermissions.toTypedArray())
                }
            } else {
                permissionsGalleryResultAbove_33.launch(notGrantedPermissions.toTypedArray())
            }
        } else {
            //check if API level below 33
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    PERMISSIONS_GALLERY
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                permissionsGalleryResult.launch(PERMISSIONS_GALLERY)
            } else {
                if (shouldShowRequestPermissionRationale(PERMISSIONS_GALLERY)) {
                    DialogUtil.showMessageWithYesNoMaterialDesign(
                        requireActivity(),
                        getString(R.string.storage_permission),
                        getString(R.string.message_storage_permission),
                        yesListener = { _, _ -> permissionsGalleryResult.launch(PERMISSIONS_GALLERY) }
                    ) { _, _ -> binding.root.showSnackBar(getString(R.string.read_media_storage_denied)) }
                } else {
                    permissionsGalleryResult.launch(PERMISSIONS_GALLERY)
                }
            }
        }
    }
}