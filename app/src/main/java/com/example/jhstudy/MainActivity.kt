package com.example.jhstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.jhstudy.databinding.ActivityMainBinding
import com.example.jhstudy.ui.GalleryFragment
import com.example.jhstudy.ui.CafeFragment
import com.example.jhstudy.ui.ChatFragment
import com.example.jhstudy.ui.TodoFragment

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var mCafeFragment: CafeFragment? = null

    private var mGalleryFragment: GalleryFragment? = null

    private var chatFragment: ChatFragment? = null

    private var todoFragment: TodoFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
        onClick2()
        onClick3()

    }


    private fun onClick() {
        binding.cafeButton.setOnClickListener {
            addFragment2()
        }
        binding.todoButton.setOnClickListener {
            addTodo()
        }
    }

    private fun onClick2() {
        binding.galleryButton.setOnClickListener {
            addFragment3()
        }
    }

    private fun onClick3() {
        binding.chatButton.setOnClickListener {
            addFragment4()
        }
    }

    private fun addFragment2() {
        mCafeFragment = CafeFragment().apply {
            arguments = bundleOf("woong" to "hi")
        }

        binding.container.isVisible = true
        addFragment(R.id.container, mCafeFragment, addBackStack = true)
    }

    private fun addFragment3() {
        mGalleryFragment = GalleryFragment()
        binding.container2.isVisible = true
        addFragment(R.id.container2, mGalleryFragment, addBackStack = true)
    }

    private fun addFragment4() {
        chatFragment = ChatFragment()
        binding.container3.isVisible = true
        addFragment(R.id.container3, chatFragment, addBackStack = true)
    }

    private fun addTodo(){
        todoFragment = TodoFragment()
        binding.todoContainer.isVisible = true
        addFragment(R.id.todo_container,todoFragment,true)
    }



    private fun setOnClick() = with(binding) {
        cafeButton.setOnClickListener {
            addCafeFragment()
        }

        galleryButton.setOnClickListener {
            showToast("click!!")
        }
    }

    private fun addCafeFragment() {
        mCafeFragment = CafeFragment().apply {
            arguments = bundleOf("woong" to "hi")
        }
        binding.container.isVisible = true

        addFragment(R.id.container, mCafeFragment, addBackStack = true)
    }

    override fun onBackPressed() {
        if (mCafeFragment?.isVisible == true) {
            Log.d("woong", "${mCafeFragment?.childFragmentManager?.backStackEntryCount}")
            if (mCafeFragment?.childFragmentManager?.backStackEntryCount!! >= 1) {
                mCafeFragment?.childFragmentManager?.popBackStackImmediate()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        mCafeFragment = null
        super.onDestroy()
    }

    private fun check() {
//        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
//                isGranted->
//            if(isGranted){
//                Log.d("camera12","callback,granted..")
//            }else{
//                Log.d("camera12","callback, denied..")
//            }
//        }
//        requestPermissionLauncher.launch("android.permission.CAMERA")}

    }
    }




