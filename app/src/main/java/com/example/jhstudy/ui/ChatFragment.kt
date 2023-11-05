package com.example.jhstudy.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jhstudy.CameraSharedViewModel
import com.example.jhstudy.ImageSharedViewModel
import com.example.jhstudy.R
import com.example.jhstudy.adapter.ChatAdapter
import com.example.jhstudy.data.ChatData
import com.example.jhstudy.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private var buttonFragment = ButtonFragment().apply {
        itemClickedListener = {

        }
    }
    private val chatAdapter = ChatAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onClick()
        onClickPlus()
        imageLiveData()
        cameraLiveData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    private fun onClick() = with(binding) {
        send.setOnClickListener {
            val message = editText.text.toString()
            if (message.isNotEmpty()) {
                val data = ChatData.TextItem(message,chatAdapter.lastMassageIsMe())
                chatAdapter.addMessage(data)
                editText.text.clear()
            }
        }
    }


    private fun initView() = with(binding) {
        recyclerViewChat.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewChat.adapter = chatAdapter
        chatAdapter.set(listOf())
    }




    private fun onClickPlus(){
        binding.plus.setOnClickListener {
            binding.buttonContainer.isVisible = !binding.buttonContainer.isVisible
            addButtonFragment()
        }
    }


    private fun addFragment(
        @IdRes containerId: Int,
        fragment: Fragment?,
        fragmentManager: FragmentManager = childFragmentManager,
        addBackStack: Boolean = false
    ) {
        requireNotNull(fragment)

        val transaction = fragmentManager.beginTransaction()
        transaction.add(containerId, fragment).apply {
            if(addBackStack) addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }
    private fun addButtonFragment() {
        buttonFragment = ButtonFragment()
        addFragment(R.id.buttonContainer, buttonFragment, addBackStack = true)
    }

    private fun imageLiveData(){
        val viewModel = ViewModelProvider(requireActivity())[ImageSharedViewModel::class.java]
        viewModel.sharedImageData.observe(viewLifecycleOwner) { data ->
            Log.d("image111",data.toString())
            val data = ChatData.ImageItem(data,chatAdapter.lastMassageIsMe())
            chatAdapter.addMessage(data)
        }
    }

    private fun cameraLiveData(){
        val viewModel = ViewModelProvider(requireActivity())[CameraSharedViewModel::class.java]
        viewModel.sharedCameraData.observe(viewLifecycleOwner) { data ->
            val data = ChatData.CameraItem(data,chatAdapter.lastMassageIsMe())
            chatAdapter.addMessage(data)
        }

    }

    }


