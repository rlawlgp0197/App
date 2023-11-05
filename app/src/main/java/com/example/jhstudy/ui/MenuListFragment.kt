package com.example.jhstudy.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jhstudy.R
import com.example.jhstudy.data.MenuData
import com.example.jhstudy.databinding.FragmentMenuListBinding

class MenuListFragment: Fragment() {

    companion object {
        const val MENU_LIST = "menuList"
    }

    private var _binding: FragmentMenuListBinding? = null
    private val binding get() = _binding!!
    var tempMenuList = ArrayList<MenuData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //tempMenuList = arguments?.safeParcelableList(MENU_LIST) ?: ArrayList()
        arguments?.let { bundle ->

            tempMenuList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelableArrayList(MENU_LIST, MenuData::class.java) as ArrayList<MenuData>
            } else {
                bundle.getParcelableArrayList<MenuData>(MENU_LIST) as ArrayList<MenuData>
            }

            tempMenuList.forEach { menuData ->
                Log.d("woong", menuData.toString())
            }
        }
        bindRecyclerView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun bindRecyclerView() = with(binding) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = createMenuListAdapter()
        }
    }

    private fun createMenuListAdapter(): RecyclerView.Adapter<MenuListViewHolder> {
        return object : RecyclerView.Adapter<MenuListViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuListViewHolder(parent)

            override fun getItemCount() = tempMenuList.size

            override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
                val menuList = arrayListOf<MenuData>()
                menuList.addAll(tempMenuList)
                val item = menuList[holder.adapterPosition]
                holder.bind(item)
            }
        }

    }

    inner class MenuListViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
    ) {
        private val menuName = itemView.findViewById<TextView>(R.id.menuName)
        private val menuPrice = itemView.findViewById<TextView>(R.id.menuPrice)
        private val option = itemView.findViewById<TextView>(R.id.option)

        fun bind(item: MenuData) {
            val price = String.format(requireContext().getString(R.string.price), item.price)
            menuName.text = item.name
            menuPrice.text = price
            when (item) {
                is MenuData.CoffeeData -> {
                    val finalOption = String.format(requireContext().getString(R.string.final_option), item.temperature, item.option)
                    option.text = finalOption
                }
                is MenuData.Beverage -> {
                    option.text = ""
                }
            }
        }
    }

}