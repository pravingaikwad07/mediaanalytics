package com.pravin.mediastreamanalytics.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.pravin.mediastreamanalytics.R
import com.pravin.mediastreamanalytics.analytics.AnalyticsManager
import com.google.android.material.card.MaterialCardView

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top, bottom = systemBars.bottom)
            insets
        }

        view.findViewById<MaterialCardView>(R.id.card_user_a).setOnClickListener {
            selectProfile("user_a")
        }

        view.findViewById<MaterialCardView>(R.id.card_user_b).setOnClickListener {
            selectProfile("user_b")
        }

        view.findViewById<MaterialCardView>(R.id.card_guest).setOnClickListener {
            selectProfile("guest")
        }
    }

    private fun selectProfile(userId: String) {
        AnalyticsManager.setUserId(userId)
        findNavController().navigate(R.id.action_profileFragment_to_playerFragment)
    }
}
