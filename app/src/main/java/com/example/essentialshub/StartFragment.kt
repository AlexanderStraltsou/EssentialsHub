package com.example.essentialshub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.essentialshub.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up button click listeners to navigate to ListFragment
        binding.btnWeekendTrip.setOnClickListener {
            (activity as? MainActivity)?.navigateToListFragment("Weekend Trip")
        }
        binding.btnSunnyWeek.setOnClickListener {
            (activity as? MainActivity)?.navigateToListFragment("Sunny Week")
        }
        binding.btnWorkTrip.setOnClickListener {
            (activity as? MainActivity)?.navigateToListFragment("Work Trip")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}