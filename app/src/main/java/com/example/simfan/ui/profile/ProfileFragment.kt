package com.example.simfan.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.simfan.databinding.FragmentProfileBinding
import com.example.simfan.R

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(ProfileRepository(simFanApiServiceInstance))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHeader()
        setupStatus()
        setupFitur()
        setupBantuan()

        val menuVerifikasi = view.findViewById<LinearLayout>(R.id.menuVerifikasi)
        menuVerifikasi.findViewById<TextView>(R.id.menuLabel).text = "Verifikasi Nomor Ponsel"
        menuVerifikasi.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_phone)

        val menuAkunSaya = view.findViewById<LinearLayout>(R.id.menuAkunSaya)
        menuAkunSaya.findViewById<TextView>(R.id.menuLabel).text = "Akun Saya"
        menuAkunSaya.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_profil)

        val menuUbahKataSandi = view.findViewById<LinearLayout>(R.id.menuUbahKataSandi)
        menuUbahKataSandi.findViewById<TextView>(R.id.menuLabel).text = "Ubah Kata Sandi"
        menuUbahKataSandi.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_wallet)

        val menuAkunBank = view.findViewById<LinearLayout>(R.id.menuAkunBank)
        menuAkunBank.findViewById<TextView>(R.id.menuLabel).text = "Akun Bank"
        menuAkunBank.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_package)

        val menuAkunTTD = view.findViewById<LinearLayout>(R.id.menuAkunTTD)
        menuAkunTTD.findViewById<TextView>(R.id.menuLabel).text = "Akun Tanda Tangan Elektronik"
        menuAkunTTD.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_gift)

        val menuUTDB = view.findViewById<LinearLayout>(R.id.menuUTDB)
        menuUTDB.findViewById<TextView>(R.id.menuLabel).text = "Undang Teman & Dapatkan Bonus"
        menuUTDB.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_phone)

        val menuRPC = view.findViewById<LinearLayout>(R.id.menuRPC)
        menuRPC.findViewById<TextView>(R.id.menuLabel).text = "Riwayat Promo & Cashback"
        menuRPC.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_profil)

        val menuKEK = view.findViewById<LinearLayout>(R.id.menuKEK)
        menuKEK.findViewById<TextView>(R.id.menuLabel).text = "Keuntungan Eksklusif Komunal"
        menuKEK.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_wallet)

        val menuHPPRPH = view.findViewById<LinearLayout>(R.id.menuHPPRPH)
        menuHPPRPH.findViewById<TextView>(R.id.menuLabel).text = "History Poin Privileges / Riwayat Poin Hadiah"
        menuHPPRPH.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_package)

        val menuKL = view.findViewById<LinearLayout>(R.id.menuKL)
        menuKL.findViewById<TextView>(R.id.menuLabel).text = "Ketentuan Layanan"
        menuKL.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_gift)

        val menuAP = view.findViewById<LinearLayout>(R.id.menuAP)
        menuAP.findViewById<TextView>(R.id.menuLabel).text = "Aturan Privasi"
        menuAP.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_phone)

        val menuPU = view.findViewById<LinearLayout>(R.id.menuPU)
        menuPU.findViewById<TextView>(R.id.menuLabel).text = "Pertanyaan Umum"
        menuPU.findViewById<ImageView>(R.id.menuIcon).setImageResource(R.drawable.ic_profil)

        val titleFitur = view.findViewById<LinearLayout>(R.id.titleFitur)
        titleFitur.findViewById<TextView>(R.id.menuLabel).text = "Fitur"

        val menuUbahKataSandi = view.findViewById<LinearLayout>(R.id.menuPKIK)
        menuUbahKataSandi.findViewById<TextView>(R.id.menuLabel).text = "Profil Komunal / Info Komunal"

        val token = "Bearer your_jwt_token_here"
        viewModel.fetchProfile(token)

        lifecycleScope.launchWhenStarted {
            viewModel.profileState.collect { profile ->
                profile?.let {
                    binding.includeProfileHeader.textProfileName.text = it.name
                    binding.includeProfileHeader.textProfileEmail.text = it.email
                    binding.includeProfileHeader.textProfileKodeReferal.text = "Kode Referal: ${it.kodeReferal}"
                    binding.includeProfileStatus.textProfileStatusPoints.text = "${it.points} Poin"
                    binding.includeProfileStatus.textProfileStatusExpiry.text = "Berlaku hingga ${it.expiry}"
                    // TODO: Load avatar with Glide/Coil if needed
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.loadingState.collect { isLoading ->
                binding.progressBar.isVisible = isLoading
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorState.collect { error ->
                error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }


        // TODO: Fetch and observe profile data from ViewModel / API Go
    }

    private fun setupHeader() {
        binding.includeProfileHeader.apply {
            // TODO: Fetch user profile from API Go
            textProfileName.text = "Ayu Cantika"
            textProfileEmail.text = "ayu.cantika@email.com"
            textProfileKodeReferal.text = "Kode Referal: RV07162"
            // TODO: Set avatar if available
        }
    }

    private fun setupStatus() {
        binding.includeProfileStatus.apply {
            // TODO: Fetch and set current points and expiry date
            textProfileStatusPoints.text = "1250 Poin"
            textProfileStatusExpiry.text = "Berlaku hingga 31 Desember 2025"
        }
    }

    private fun setupFitur() {
        binding.includeProfileFitur.apply {
            cardEditProfile.setOnClickListener {
                // TODO: Navigate to edit profile
            }
            cardPengaturan.setOnClickListener {
                // TODO: Navigate to pengaturan
            }
            cardKeluar.setOnClickListener {
                // TODO: Handle logout logic
            }
        }
    }

    private fun setupBantuan() {
        binding.includeProfileBantuan.apply {
            cardFaq.setOnClickListener {
                // TODO: Navigate to FAQ
            }
            cardHubungiKami.setOnClickListener {
                // TODO: Navigate to Hubungi Kami
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
