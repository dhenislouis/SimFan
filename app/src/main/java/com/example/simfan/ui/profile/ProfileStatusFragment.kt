package com.example.simfan.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentProfileStatusCardBinding

class ProfileStatusFragment : Fragment() {

    private var _binding: ComponentProfileStatusCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentProfileStatusCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Fetch membership data from ViewModel or arguments
        binding.membershipTitle.text = "Gold Member"
        binding.membershipPoints.text = "1,250 Points"
        binding.membershipExpiry.text = "Berlaku hingga 31 Des 2025"
        binding.membershipTotalTransaksi.text = "Total Transaksi Rp 25.000.000"

        // Info button action
        binding.infoButton.setOnClickListener {
            // Show info policy or navigate to policy fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
