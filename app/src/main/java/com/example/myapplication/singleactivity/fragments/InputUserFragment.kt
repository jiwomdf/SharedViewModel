package com.example.myapplication.singleactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.data.User
import com.example.myapplication.databinding.FragmentInputUserBinding
import com.example.myapplication.singleactivity.SingleActivity
import com.example.myapplication.singleactivity.SingleViewModel

class InputUserFragment : Fragment() {

    companion object {
        fun newInstance(): InputUserFragment {
            return InputUserFragment()
        }
    }

    private lateinit var binding: FragmentInputUserBinding
    private val sharedViewModel by activityViewModels<SingleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.etAge.setText(sharedViewModel.user?.age ?: 0)
        //binding.etName.setText(sharedViewModel.item?.name ?: "")

        binding.btnNext.setOnClickListener {
            sharedViewModel.user = User(
                name = binding.etName.text.toString().trim(),
                age = binding.etAge.text.toString().trim().toIntOrNull() ?: 0
            )
            (requireActivity() as SingleActivity).loadFragment(InputItemFragment.newInstance())
        }
    }
}