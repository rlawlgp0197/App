
package com.example.jhstudy.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jhstudy.R
import com.example.jhstudy.Repository
import com.example.jhstudy.adapter.PhotoAdapter
import com.example.jhstudy.databinding.FragmentGalleryBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GalleryDialog : androidx.fragment.app.DialogFragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val scope = MainScope()
    var itemClickedListener:((String)->Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogFullScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        scope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        onClick()
        fetchRandomPhotos()
        goToDialog()
    }

    private fun initViews() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = PhotoAdapter(itemClickedListener = {
            //invoke = 함수호출
            itemClickedListener?.invoke(it)
            dismiss()
        })
    }

    private fun fetchRandomPhotos(query: String? = null) = scope.launch {
        Repository.getRandomPhotos(query)?.let { photos ->
            (binding.recyclerView.adapter as? PhotoAdapter)?.apply {
                this.photos = photos
                Log.d("GF", this.photos.toString())
                notifyDataSetChanged()
            }
        }
    }


    private fun getSearchPhoto() = with(binding) {
        val searchText = searchText.text.toString()
        fetchRandomPhotos(searchText)
    }

    private fun onClick() = with(binding) {
        searchButton.setOnClickListener {
            getSearchPhoto()
            // 키보드 내려줘
            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
            searchText.apply {
                // 커서 깜빡거리는거 끔
                clearFocus()
                text.clear()
            }
        }
    }

    private fun goToDialog() {
        binding.goToDialog.setOnClickListener {
            val bottomDialog = DialogFragment2()
            bottomDialog.show(childFragmentManager, bottomDialog.tag)
        }
    }


}


