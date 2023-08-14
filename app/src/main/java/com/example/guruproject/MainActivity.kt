package com.example.guruproject

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.guruproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val fl: FrameLayout by lazy {
        findViewById(R.id.mainFrame)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 하단바 home, to_do 버튼 누르면 green 색 변화
        val bnv_main = findViewById<BottomNavigationView>(R.id.buttonNav)

        bnv_main.setOnNavigationItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.home -> {
                        bnv_main.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_home)
                        bnv_main.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_home)
                        FirstFragment()
                    }
                    else -> {
                        bnv_main.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_todo)
                        bnv_main.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_todo)
                        SecondFragment()
                    }
                }
            )
            true
        }
        bnv_main.selectedItemId = R.id.home
    }

    // home, to_do 선택시 각 fragment 교채
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, fragment) // replace()로 프래그먼트를 컨테이너에 추가
            .commit()
    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // menu callback
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
