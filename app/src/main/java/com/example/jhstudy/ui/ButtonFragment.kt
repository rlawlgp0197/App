package com.example.jhstudy.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jhstudy.CameraSharedViewModel
import com.example.jhstudy.ImageSharedViewModel
import com.example.jhstudy.databinding.FragmentButtonBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date

class ButtonFragment:Fragment() {
    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!


    var itemClickedListener:((String)->Unit)? = null

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 123

        val PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openDialog()
        permissionCheck()
        onClickCamera()
    }

    private fun permissionCheck() {
        val permissionsToRequest = mutableListOf<String>()

        for (permission in PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(permission)
            }
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                REQUEST_CODE_PERMISSIONS
            )
        } else {
            Log.d("1313", "All permissions granted")
        }
    }

    private fun openDialog() {
        binding.galleryButton.setOnClickListener {
            val galleryDialog = GalleryDialog().apply {
                itemClickedListener = {
                    this@ButtonFragment.itemClickedListener?.invoke(it)
                    val viewModel = ViewModelProvider(requireActivity())[ImageSharedViewModel::class.java]
                    viewModel.sharedImageData.value = it
                }
            }
            galleryDialog.show(childFragmentManager, galleryDialog.tag)
        }
    }


    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            activityResult.launch(intent)
        } else {
            Log.d("1313", "Cannot open camera")
        }
    }

    private fun onClickCamera() {
        binding.cameraButton.setOnClickListener {
            openCamera()
        }
    }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
            val extras = result.data!!.extras
            val bitmap = extras?.get("data") as Bitmap

            imageUri = saveBitmapAndReturnUri(bitmap)
            if (imageUri != null) {
                val filePath = imageUri.toString()
                val uri = Uri.parse(filePath)
                val viewModel = ViewModelProvider(requireActivity())[CameraSharedViewModel::class.java]
                viewModel.sharedCameraData.value = uri
            }
        }
    }

    private fun saveBitmapAndReturnUri(bitmap: Bitmap): Uri? {
        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "IMG_$timeStamp.png"
        val file = File(storageDir, fileName)

        try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return Uri.fromFile(file)
    }

}