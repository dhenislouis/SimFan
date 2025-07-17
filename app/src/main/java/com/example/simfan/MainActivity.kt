package com.example.simfan

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var tabViews: List<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabBeranda = findViewById<LinearLayout>(R.id.tabBeranda)
        val tabProduk = findViewById<LinearLayout>(R.id.tabProduk)
        val tabDeposito = findViewById<LinearLayout>(R.id.tabDeposito)
        val tabPromo = findViewById<LinearLayout>(R.id.tabPromo)
        val tabProfil = findViewById<LinearLayout>(R.id.tabProfil)

        // Icon dan label setup
        setupTab(tabBeranda, R.drawable.ic_home, "Beranda")
        setupTab(tabProduk, R.drawable.ic_produk, "Produk")
        setupTab(tabDeposito, R.drawable.ic_deposito, "Deposito")
        setupTab(tabPromo, R.drawable.ic_promo, "Promo")
        setupTab(tabProfil, R.drawable.ic_profil, "Profil")

        // Navigasi logic
        tabViews = listOf(tabBeranda, tabProduk, tabDeposito, tabPromo, tabProfil)
        setupTabNavigation()
        updateActiveTab(tabBeranda) // Default aktif
    }

    private fun setupTab(tab: LinearLayout, iconRes: Int, labelText: String) {
        val icon = tab.findViewById<ImageView>(R.id.iconTab)
        val label = tab.findViewById<TextView>(R.id.labelTab)
        icon.setImageResource(iconRes)
        label.text = labelText
    }

    private fun setupTabNavigation() {
        tabViews.forEach { tab ->
            tab.setOnClickListener {
                updateActiveTab(tab)
            }
        }
    }

    private fun updateActiveTab(selectedTab: View) {
        tabViews.forEach { tab ->
            val isSelected = (tab == selectedTab)
            tab.isSelected = isSelected

            val icon = tab.findViewById<ImageView>(R.id.iconTab)
            val label = tab.findViewById<TextView>(R.id.labelTab)

            icon.setColorFilter(
                ContextCompat.getColor(
                    tab.context,
                    if (isSelected) R.color.primary else R.color.nav_icon_default
                )
            )

            label.visibility = if (isSelected) View.VISIBLE else View.GONE
            label.animate().apply {
                duration = 200
                alpha(if (isSelected) 1f else 0f)
            }.start()

            tab.setBackgroundResource(
                if (isSelected) R.drawable.bg_tab_active else android.R.color.transparent
            )
        }
    }
}


