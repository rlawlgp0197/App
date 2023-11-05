package com.example.jhstudy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jhstudy.databinding.ItemTodoBinding

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    var todoList = mutableListOf<String>()

    fun set(list:List<String>){
        todoList.clear()
        todoList.addAll(list)
        notifyDataSetChanged()
    }

    fun addTodo(data:String){
        todoList.add(data)
        notifyItemInserted(todoList.lastIndex)
        Log.d("listData",todoList.count().toString())
    }

    inner class ViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.todoText.text = data
        }
    }



    override fun getItemCount(): Int = todoList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

}