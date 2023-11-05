package com.example.jhstudy.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.jhstudy.data.CoffeeOption
import com.example.jhstudy.data.MenuData
import com.example.jhstudy.data.Temperature
import com.example.jhstudy.databinding.DialogMenuOptionBinding

class MenuOptionDialog(private val isAmericano: Boolean): DialogFragment() {

    companion object {
        const val MENU = "menu"
        val TAG = MenuOptionDialog::class.java.simpleName
    }

    private var _binding: DialogMenuOptionBinding? = null
    private val binding get() = _binding!!
    var dismissResult: ((result: MenuData) -> Unit)? = null
    private var temperatureOption = Temperature.Hot
    private var coffeeOption = CoffeeOption.None
    var menu = ""

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            isCancelable = true
            setCanceledOnTouchOutside(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogMenuOptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            menu = it.getString(MENU) ?: ""
        }
        if (isAmericano) {
            binding.option2.isVisible = true
        }
        binding.apply {
            radioGroup1.setOnCheckedChangeListener(temperatureCheckedChangedListener)
            radioGroup2.setOnCheckedChangeListener(optionCheckedChangedListener)
        }
        setOnClick()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    private val temperatureCheckedChangedListener = RadioGroup.OnCheckedChangeListener { radioGroup, checkedId ->
        when (checkedId) {
            binding.radioButton1.id -> temperatureOption = Temperature.Hot
            binding.radioButton2.id -> temperatureOption = Temperature.Ice
        }
    }

    private val optionCheckedChangedListener = RadioGroup.OnCheckedChangeListener { radioGroup, checkedId ->
        when (checkedId) {
            binding.radioButton3.id -> coffeeOption = CoffeeOption.Vanilla
            binding.radioButton4.id -> coffeeOption = CoffeeOption.Almond
            binding.radioButton5.id -> coffeeOption = CoffeeOption.None
        }
    }

    private fun setOnClick() = with(binding) {
        complete.setOnClickListener {
            when (menu) {
                "아메리카노" ->{
                    val americano = MenuData.CoffeeData(
                        "americano",
                        4000,
                        temperatureOption,
                        coffeeOption
                    )

                    dismissResult?.invoke(americano)
                }
                "카페라떼" -> {
                    val latte = MenuData.CoffeeData(
                        "latte",
                        4500,
                        temperatureOption,
                        coffeeOption
                    )
                    dismissResult?.invoke(latte)
                }
                "바닐라라떼" -> {
                    val vanillaLatte = MenuData.CoffeeData(
                        "vanillaLatte",
                        5000,
                        temperatureOption,
                        coffeeOption
                    )
                    dismissResult?.invoke(vanillaLatte)
                }
                "바닐라크림프라푸치노" -> {
                    val vanillaLatte = MenuData.CoffeeData(
                        "VanillaCreamFrappuccino",
                        6000,
                        temperatureOption,
                        coffeeOption
                    )
                    dismissResult?.invoke(vanillaLatte)
                }
            }
        }

        cancel.setOnClickListener { dismiss() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : Dialog(requireContext()) {
            override fun onBackPressed() {
                dismiss()
            }
        }

        return dialog
    }
}