package com.example.myapplication.singleactivity

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Item
import com.example.myapplication.data.User

class SingleViewModel: ViewModel() {
    var user: User? = null
    var item: Item? = null
}