package com.example.essentialshub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.essentialshub.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val packingListViewModel: PackingListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.navHostFragment.id, StartFragment())
                .commit()
        }
    }

    fun navigateToListFragment(listType: String) {
        val listFragment = ListFragment.newInstance(listType)
        supportFragmentManager.beginTransaction()
            .replace(binding.navHostFragment.id, listFragment) // This replaces the current fragment
            .addToBackStack(null) // This adds the transaction to the back stack for navigation
            .commit()
    }


}




