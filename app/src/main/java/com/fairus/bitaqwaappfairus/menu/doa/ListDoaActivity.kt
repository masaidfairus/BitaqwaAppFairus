package com.fairus.bitaqwaappfairus.menu.doa

import android.media.tv.TvContract.Channels.Logo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.fairus.bitaqwaappfairus.R
import com.fairus.bitaqwaappfairus.databinding.ActivityListDoaBinding
import com.fairus.bitaqwaappfairus.menu.doa.adapter.DoaListAdapter
import com.fairus.bitaqwaappfairus.menu.doa.data.DoaEtikaBaikData
import com.fairus.bitaqwaappfairus.menu.doa.data.DoaMakananDanMinumanData
import com.fairus.bitaqwaappfairus.menu.doa.data.DoaPagiDanMalamData
import com.fairus.bitaqwaappfairus.menu.doa.data.DoaPerjalananData
import com.fairus.bitaqwaappfairus.menu.doa.data.DoaRumahData
import com.fairus.bitaqwaappfairus.menu.doa.data.DoaSholatData
import com.fairus.bitaqwaappfairus.menu.doa.model.DoaModel

class ListDoaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDoaBinding

    lateinit var title: String
    var logo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListDoaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = intent.getStringExtra("ext_title").toString()
        logo = intent.getIntExtra("ext_icon", 0)

        setSupportActionBar(binding.toolbarListDoa)
        supportActionBar?.title = title

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<DoaModel> = arrayListOf()

        when (title) {
            getString(R.string.text_pagi_amp_malam) -> list.addAll(DoaPagiDanMalamData.listDoaPagiDanMalamData)
            getString(R.string.text_rumah) -> list.addAll(DoaRumahData.listDoaRumahData)
            getString(R.string.text_makanan_amp_minuman) -> list.addAll(DoaMakananDanMinumanData.listDoaMakananDanMinumanData)
            getString(R.string.text_perjalanan) -> list.addAll(DoaPerjalananData.listDoaPerjalananData)
            getString(R.string.text_sholat) -> list.addAll(DoaSholatData.listDoaShalatData)
            getString(R.string.text_etika_baik) -> list.addAll(DoaEtikaBaikData.listDoaEtikaBaikData)
        }

        val adapterDoa = DoaListAdapter(list, title, logo)
        binding.rvDoa.setHasFixedSize(true)
        binding.rvDoa.layoutManager = LinearLayoutManager(this)
        binding.rvDoa.adapter = adapterDoa
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}