package com.example.perehodprilojeniy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.perehodprilojeniy.databinding.ListItemBinding

class UserAdapter(context: Context, dataArrayList: ArrayList<User>) :
    ArrayAdapter<User?>(context, R.layout.list_item, dataArrayList) {

    lateinit var binding: ListItemBinding

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        binding = ListItemBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        val item = getItem(position)
        binding.tvFio.text = item?.smallFio()
        return binding.root
    }
}