
package com.example.perehodprilojeniy


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Debug
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.perehodprilojeniy.databinding.ActivityMainBinding
import androidx.core.widget.doAfterTextChanged
import kotlin.toString

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter

    companion object {
        const val USER_INFO = "user_info"
        const val EDIT = 1
        const val ADD = 2
    }

    var userList = arrayListOf<User>(
        User("Петров", "Петя", 23, "12345678", "СПб", "ya.ru"),
        User("Сидоров", "Вася", 20, "12345678", "СПб", "ya.ru"),
        User("Васильев", "Сидр", 22, "12345678", "СПб", "ya.ru"),
        User("Иванов", "Федор", 25, "12345678", "СПб", "ya.ru"),
        User("Федоров", "Иван", 31, "12345678", "СПб", "ya.ru")
    )
    var position: Int = -1

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

        adapter = UserAdapter(this, userList)
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(USER_INFO, userList[i])
                position = i
                startActivityForResult(intent, EDIT)
            }

        binding.floatBtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivityForResult(intent, ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == EDIT) {
                val newUser = data?.getSerializableExtra(DetailActivity.EDIT_USER) as User
                userList[position] = newUser
            }
            if (requestCode == ADD) {
                val newUser = data?.getSerializableExtra(DetailActivity.EDIT_USER) as User
                userList.add(newUser)
            }
            adapter.notifyDataSetChanged()
        }
    }
}