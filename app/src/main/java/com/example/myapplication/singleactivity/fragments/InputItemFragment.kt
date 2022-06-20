package com.example.myapplication.singleactivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.data.Item
import com.example.myapplication.singleactivity.SingleViewModel
import com.example.myapplication.databinding.FragmentInputItemBinding
import com.example.myapplication.singleactivity.SingleActivity

class InputItemFragment : Fragment() {

    companion object {
        fun newInstance(): InputItemFragment {
            return InputItemFragment()
        }
    }

    lateinit var binding: FragmentInputItemBinding
    private val sharedViewModel by activityViewModels<SingleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            etName.setText(sharedViewModel.item?.name ?: "")
            etCategory.setText(sharedViewModel.item?.category ?: "")
            etQuantity.setText(sharedViewModel.item?.qty.toStringNumber())

            validateBtn()

            btnNext.setOnClickListener {
                sharedViewModel.item = Item(
                    name = binding.etName.text.trim().toString(),
                    category = binding.etCategory.text.trim().toString(),
                    qty = binding.etQuantity.text.trim().toString().toIntOrNull() ?: 0
                )
                (requireActivity() as SingleActivity).loadFragment(ConfirmFragment.newInstance())
            }
            binding.etName.doOnTextChanged { _, _, _, _ ->
                validateBtn()
            }
            binding.etCategory.doOnTextChanged { _, _, _, _ ->
                validateBtn()
            }
            binding.etQuantity.doOnTextChanged { _, _, _, _ ->
                validateBtn()
            }
        }
    }

    private fun validateBtn() {
        binding.apply {
            btnNext.isVisible =
                    !(etQuantity.text.isNullOrEmpty() || etQuantity.text.toString() == "0") &&
                    !(etName.text.isNullOrEmpty()) &&
                    !(etCategory.text.isNullOrEmpty())
        }
    }

    private fun Int?.toStringNumber(): String{
        return if(this == null || this <= 0) "" else this.toString()
    }

}