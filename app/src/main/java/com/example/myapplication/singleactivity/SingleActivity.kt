package com.example.myapplication.singleactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.singleactivity.fragments.InputUserFragment
import com.example.myapplication.R
import com.example.myapplication.data.Item
import com.example.myapplication.databinding.ActivitySingleBinding

class SingleActivity: AppCompatActivity(){

    lateinit var binding: ActivitySingleBinding
    private val viewModel by viewModels<SingleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(InputUserFragment.newInstance())

        binding.ivBack.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.fragment_fade_enter,
            R.anim.fragment_fade_exit,
            R.anim.fragment_fade_enter,
            R.anim.fragment_fade_exit
        )
        transaction.replace(R.id.fcv_main, fragment, fragment.javaClass.toString())
        transaction.addToBackStack(fragment.javaClass.toString())
        transaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

}