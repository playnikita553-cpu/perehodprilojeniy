
package com.example.perehodprilojeniy


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.perehodprilojeniy.databinding.ActivityDetailBinding
import androidx.core.widget.doAfterTextChanged

class DetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EDIT_USER = "edit_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityDetailBinding.inflate(layoutInflater)


        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (intent.extras != null) {

            val selectedUser: User = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(MainActivity.USER_INFO, User::class.java) as User
            } else {
                intent.getSerializableExtra(MainActivity.USER_INFO) as User
            }


            binding.familiaTwo.setText(selectedUser.lastName)
            binding.nameTwo.setText(selectedUser.firstName)
            binding.ageTwo.setText(selectedUser.age.toString())
            binding.phoneTwo.setText(selectedUser.phone)
            binding.addressTwo.setText(selectedUser.adress)
            binding.siteTwo.setText(selectedUser.site)
        }

        binding.btnSave.setOnClickListener {
            val newUser = User(
                lastName = binding.familiaTwo.text.toString(),
                firstName = binding.nameTwo.text.toString(),
                age = binding.ageTwo.text.toString().toInt(),
                phone = binding.phoneTwo.text.toString(),
                adress = binding.addressTwo.text.toString(),
                site = binding.siteTwo.text.toString()
            )
            val intent = Intent()
            intent.putExtra(EDIT_USER, newUser)
            setResult(RESULT_OK, intent)
            finish()
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
        binding.btnSave.setOnClickListener()
        {


        }

    }
}