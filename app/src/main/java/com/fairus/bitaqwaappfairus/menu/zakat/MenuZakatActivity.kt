package com.fairus.bitaqwaappfairus.menu.zakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.fairus.bitaqwaappfairus.R
import com.fairus.bitaqwaappfairus.databinding.ActivityMenuZakatBinding
import java.text.NumberFormat
import java.util.Locale

class MenuZakatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuZakatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuZakatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarZakat)
        initView()
    }

    private fun initView() {
        binding.btnZakat.setOnClickListener {

            if (TextUtils.isEmpty(binding.etAmountZakat.text)) {
                Toast.makeText(
                    this,
                    "Harta Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ) .show()
            } else if
                (binding.etAmountZakat.getNumericValue().toInt() >= 85000000) {
                val formatAmount = NumberFormat
                    .getNumberInstance(Locale("id", "ID"))
                    .format(binding.etAmountZakat.getNumericValue())

                binding.tvHartaAmount.text = "Rp $formatAmount"

                val zakat = binding.etAmountZakat.getNumericValue().toInt() * (2.5 / 100)
                val formatZakat = NumberFormat
                    .getNumberInstance(Locale("id", "ID"))
                    .format(zakat)

                binding.tvAmountZakat.text = "Rp $formatZakat"
            } else {
                Toast.makeText(
                    this,
                    "Total Harta Belum Mencapai Nisab (85Gr Emas)",
                    Toast.LENGTH_LONG
                ) .show()
                binding.tvAmountZakat.text = "Rp 0"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}