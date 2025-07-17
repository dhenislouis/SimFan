package com.example.simfan.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentBerandaHeaderBinding

class BerandaHeaderFragment : Fragment() {

    private var _binding: ComponentBerandaHeaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentBerandaHeaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Fetch user data for greeting and saldo from ViewModel or API
        binding.greetingText.text = "Selamat Pagi, Ayu Cantika"
        binding.saldoText.text = "Rp 12.500.000"

        // Example button actions
        binding.scanQrButton.setOnClickListener {
            // Navigate to Scan QR
        }

        binding.topUpButton.setOnClickListener {
            // Navigate to Top Up
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
