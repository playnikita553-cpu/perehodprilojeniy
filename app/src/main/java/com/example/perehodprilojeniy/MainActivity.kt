
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
        const val REQUEST_CODE = 1
    }
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

        binding.addressTwo.doAfterTextChanged { text ->
            if (text.isNullOrEmpty()) {
                binding.btnAddress.visibility = View.GONE
            } else {
                binding.btnAddress.visibility = View.VISIBLE
            }

        }


        binding.phoneTwo.doAfterTextChanged { text ->
            if(text.isNullOrEmpty()){
                binding.btnPhone.visibility = View.GONE
            }
            else{
                binding.btnPhone.visibility = View.VISIBLE
            }
        }

        binding.siteTwo.doAfterTextChanged { text ->
            if(text.isNullOrEmpty()){
                binding.btnSite.visibility = View.GONE
            }
            else{
                binding.btnSite.visibility = View.VISIBLE
            }
        }
        binding.btnAddress.setOnClickListener(){
            val address = binding.addressTwo.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$address"))
            startActivity(intent)
        }
        binding.btnPhone.setOnClickListener() {
            val phone = binding.phoneTwo.text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            startActivity(intent)
        }
        binding.btnSite.setOnClickListener {
            val site = binding.siteTwo.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$site"))
            startActivity(intent)
        }


    }
}