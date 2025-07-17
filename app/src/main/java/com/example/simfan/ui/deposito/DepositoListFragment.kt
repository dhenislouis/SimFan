package com.example.simfan.ui.deposito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentDepositoListBinding

class DepositoListFragment : Fragment() {

    private var _binding: ComponentDepositoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentDepositoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Load deposito product list from API or ViewModel
        // Setup RecyclerView to display deposito product cards
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
