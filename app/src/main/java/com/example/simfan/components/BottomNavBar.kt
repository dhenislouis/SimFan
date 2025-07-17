package com.example.simfan.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.simfan.R

class BottomNavBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val tabViews: List<LinearLayout>

    init {
        LayoutInflater.from(context).inflate(R.layout.component_bottom_navbar, this, true)

        tabViews = listOf(
            findViewById(R.id.tabBeranda),
            findViewById(R.id.tabProduk),
            findViewById(R.id.tabDeposito),
            findViewById(R.id.tabPromo),
            findViewById(R.id.tabProfil)
        )

        setupTabLabelsAndIcons()
        setupTabListeners()
        setActiveTab(findViewById(R.id.tabBeranda))
    }

    private fun setupTabLabelsAndIcons() {
        val labels = listOf("Beranda", "Produk", "Deposito", "Promo", "Profil")
        val icons = listOf(
            R.drawable.ic_home,
            R.drawable.ic_produk,
            R.drawable.ic_deposito,
            R.drawable.ic_promo,
            R.drawable.ic_profil
        )

        tabViews.forEachIndexed { index, tab ->
            tab.findViewById<TextView>(R.id.labelTab).text = labels[index]
            tab.findViewById<ImageView>(R.id.iconTab).setImageResource(icons[index])
        }
    }

    private fun setupTabListeners() {
        tabViews.forEach { tab ->
            tab.setOnClickListener {
                val navController = findNavController()
                val destination = when (tab.id) {
                    R.id.tabBeranda -> R.id.homeFragment
                    R.id.tabProduk -> R.id.produkFragment
                    R.id.tabDeposito -> R.id.depositoFragment
                    R.id.tabPromo -> R.id.promoFragment
                    R.id.tabProfil -> R.id.profilFragment
                    else -> null
                }
                destination?.let {
                    if (navController.currentDestination?.id != it) {
                        navController.navigate(it)
                    }
                }
                setActiveTab(tab)
            }
        }
    }

    private fun setActiveTab(selectedTab: LinearLayout) {
        tabViews.forEach { tab ->
            val isSelected = tab == selectedTab
            val icon = tab.findViewById<ImageView>(R.id.iconTab)
            val label = tab.findViewById<TextView>(R.id.labelTab)

            // Ubah warna ikon
            icon.setColorFilter(
                ContextCompat.getColor(context,
                    if (isSelected) R.color.white else R.color.primary)
            )

            // Tampilkan label jika aktif
            label.visibility = if (isSelected) VISIBLE else GONE

            // Atur background tab
            tab.background = ContextCompat.getDrawable(
                context,
                if (isSelected) R.drawable.bg_tab_active else android.R.color.transparent
            )
        }
    }
}
