package com.example.jhstudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jhstudy.adapter.TodoAdapter
import com.example.jhstudy.databinding.FragmentTodoBinding

class TodoFragment:Fragment() {
    private var _binding : FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private val todoAdapter by lazy { TodoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickPlus()
        initView()
    }

    private fun onClickPlus(){
        binding.editBtn.setOnClickListener {
            var dialogTodo = DialogTodo().apply {
                result = {
                    todoAdapter.addTodo(it)
                }
            }
            dialogTodo.show(childFragmentManager,null)
        }
    }

    private fun initView() = with(binding) {
        todoRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        todoRv.adapter = todoAdapter
        todoAdapter.set(emptyList())
    }
}