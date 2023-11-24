package com.fairus.bitaqwaappfairus.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fairus.bitaqwaappfairus.R
import com.fairus.bitaqwaappfairus.dashboard.adapter.InspirationListAdapter
import com.fairus.bitaqwaappfairus.dashboard.data.InspirationData
import com.fairus.bitaqwaappfairus.dashboard.model.InspirationModel
import com.fairus.bitaqwaappfairus.databinding.ActivityMainBinding
import com.fairus.bitaqwaappfairus.menu.doa.MenuDoaActivity
import com.fairus.bitaqwaappfairus.menu.dzikir.MenuDzikirActivity
import com.fairus.bitaqwaappfairus.menu.videokajian.MenuVideoKajianActivity
import com.fairus.bitaqwaappfairus.menu.zakat.MenuZakatActivity
import com.fairus.bitaqwaappfairus.menu.jadwalsholat.MenuJadwalSholat
import java.text.SimpleDateFormat
import java.util.Calendar

class DashboardActivity : AppCompatActivity() {

    // variabel untuk mengikat layout ke class
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mendaftarkan layout activity_dashboard.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        // variabel view untuk menampung komponen layout
        val view:ScrollView = binding.root
        // menentukan tampilan dengan data yang ada di variabel view (binding)
        setContentView(view)

        // memanggil fungsi
        initHeader()
        initRecyclerViewInspiration()
        initNavMenu()

    }

    // fungsi untuk berpindah activity
    private fun initNavMenu() {

        binding.ivIconMenuDoa.setOnClickListener {
            startActivity(Intent(this, MenuDoaActivity::class.java))
        }

        binding.ivIconMenuDzikir.setOnClickListener {
            startActivity(Intent(this, MenuDzikirActivity::class.java))
        }

        binding.ivIconMenuZakat.setOnClickListener {
            startActivity(Intent(this, MenuZakatActivity::class.java))
        }

        binding.ivIconMenuJadwalSholat.setOnClickListener {
            startActivity(Intent(this, MenuJadwalSholat::class.java))
        }

        binding.ivIconMenuVideoKajian.setOnClickListener {
            startActivity(Intent(this, MenuVideoKajianActivity::class.java))
        }
    }

    private fun initRecyclerViewInspiration() {
        val list: ArrayList<InspirationModel> = arrayListOf()
        list.addAll(InspirationData.listData)

        val listAdapter = InspirationListAdapter(list)

        binding.rvInspiration.setHasFixedSize(true)
        binding.rvInspiration.layoutManager = LinearLayoutManager(this)
        binding.rvInspiration.adapter = listAdapter
    }

    private fun initHeader()
    {

        val timeNow = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("HH")
        val time = timeFormat.format(timeNow.time)

        when {
            time.toInt() in 0 .. 5->binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_night)
            time.toInt() in 6 .. 10->binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_morning)
            time.toInt() in 11 .. 18->binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_afternoon)
            time.toInt() in 19 .. 23->binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_night)
        }
    }
}