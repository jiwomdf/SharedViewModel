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

        validateBtn(null)
        //binding.etName.setText(sharedViewModel.item?.name ?: "")
        //binding.etCategory.setText(sharedViewModel.item?.category ?: "")
        //binding.etQuantity.setText(sharedViewModel.item?.category ?: "")

        binding.btnNext.setOnClickListener {
            sharedViewModel.item = Item(
                name = binding.etName.text.trim().toString(),
                category = binding.etCategory.text.trim().toString(),
                qty = binding.etQuantity.text.trim().toString().toIntOrNull() ?: 0
            )
            (requireActivity() as SingleActivity).loadFragment(ConfirmFragment.newInstance())
        }
        binding.etQuantity.doOnTextChanged { text, _, _, _ ->
            validateBtn(text.toString())
        }
    }

    private fun validateBtn(text: String?) {
        binding.btnNext.isEnabled = !(text.isNullOrBlank() || text == "0")
        binding.btnNext.isVisible = !(text.isNullOrBlank() || text == "0")
    }

}