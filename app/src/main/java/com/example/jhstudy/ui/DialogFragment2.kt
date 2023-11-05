package com.example.jhstudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jhstudy.Repository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.jhstudy.adapter.ChatAdapter
import com.example.jhstudy.adapter.DialogAdapter
import com.example.jhstudy.data.ChatData
import com.example.jhstudy.databinding.DialogBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

open class DialogFragment2 : BottomSheetDialogFragment() {
    private var _binding: DialogBinding? = null
    private val binding get()=_binding!!
    private val scope = MainScope()
    private var chatAdapter= ChatAdapter()

    companion object {
        const val TAG = "Dialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DialogBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchRandomPhotos()
    }

    private fun initViews() {
        binding.dialogRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.dialogRecyclerView.adapter = DialogAdapter(itemClickedListener = {
            var photo = it.urls.full
            chatAdapter.addMessage(ChatData.ImageItem(photo, true))
        })
    }


    private fun fetchRandomPhotos(query: String? = null) = scope.launch {
        Repository.getRandomPhotos(query)?.let { photos ->
            (binding.dialogRecyclerView.adapter as? DialogAdapter)?.apply {
                this.photos = photos
                Log.d("DF3", this.photos.toString())
                notifyDataSetChanged()
            }
        }
    }
}