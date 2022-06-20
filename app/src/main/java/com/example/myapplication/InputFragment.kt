package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    companion object {
        fun newInstance(): InputFragment {
            return InputFragment()
        }
    }

    lateinit var binding: FragmentInputBinding
    private val sharedViewModel by activityViewModels<SingleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateBtn(null)
        binding.tvName.text = sharedViewModel.data?.name ?: ""
        binding.tvCategory.text = sharedViewModel.data?.category ?: ""

        binding.btnNext.setOnClickListener {
            sharedViewModel.data?.qty = binding.etQuantity.text.trim().toString().toIntOrNull() ?: 0
            (requireActivity() as SingleActivity).loadFragment(ConfirmFragment.newInstance())
        }
        binding.etQuantity.doOnTextChanged { text, start, before, count ->
            validateBtn(text.toString())
        }
    }

    private fun validateBtn(text: String?) {
        binding.btnNext.isEnabled = !(text.isNullOrBlank() || text == "0")
        binding.btnNext.isVisible = !(text.isNullOrBlank() || text == "0")
    }

}