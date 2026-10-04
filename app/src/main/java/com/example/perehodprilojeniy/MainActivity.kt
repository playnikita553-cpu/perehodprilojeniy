
package com.example.perehodprilojeniy


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Debug
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.perehodprilojeniy.databinding.ActivityMainBinding
import androidx.core.widget.doAfterTextChanged
import kotlin.toString

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    companion object {
        const val User_info= "user_info"
        const val edit = 1
        const val add = 2
    }
    var userlist = arrayListOf<User>()
    var position:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




    }
}