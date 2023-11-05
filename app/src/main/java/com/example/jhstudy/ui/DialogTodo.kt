package com.example.jhstudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.jhstudy.databinding.DialogTodoBinding

class DialogTodo: DialogFragment() {

    private var _binding: DialogTodoBinding? = null
    private val binding get() = _binding!!
    var result:((String)->Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickSend()
    }

    private fun onClickSend(){
        binding.sendBtn.setOnClickListener {
            var data = binding.editTodo.text
            result?.invoke(data.toString())
            dismiss()
        }
    }

}