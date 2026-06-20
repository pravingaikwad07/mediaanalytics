package com.pravin.mediastreamanalytics.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pravin.mediastreamanalytics.R
import com.pravin.mediastreamanalytics.analytics.AnalyticsManager
import com.pravin.mediastreamanalytics.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnalyticsManager.logScreenView("Profile Selection", "ProfileFragment")

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top, bottom = systemBars.bottom)
            insets
        }

        binding.cardUserA.setOnClickListener {
            selectProfile("user_sushil", binding.tvUserA.text.toString())
        }

        binding.cardUserB.setOnClickListener {
            selectProfile("user_pravin", binding.tvUserB.text.toString())
        }

        binding.cardGuest.setOnClickListener {
            selectProfile("user_guest", binding.tvUserC.text.toString())
        }
    }

    private fun selectProfile(userId: String, userName: String) {
        AnalyticsManager.setUserId(userId)
        val bundle = Bundle().apply {
            putString("userName", userName)
        }
        findNavController().navigate(R.id.action_profileFragment_to_mediaFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
