package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivitySingleBinding

class SingleActivity: AppCompatActivity(){

    lateinit var binding: ActivitySingleBinding
    private val viewModel by viewModels<SingleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if((intent.getStringExtra("id") ?: "") == "1"){
            viewModel.data = Data(
                name = "Biscuit Tanggo Coklat",
                category = "Makanan",
                qty = 0
            )
        } else if((intent.getStringExtra("id") ?: "") == "2") {
            viewModel.data = Data(
                name = "Fruity Blackcurrant 550",
                category = "Minuman",
                qty = 0
            )
        }

        loadFragment(DetailFragment.newInstance())

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