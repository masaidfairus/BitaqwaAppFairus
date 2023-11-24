package com.fairus.bitaqwaappfairus.menu.videokajian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.fairus.bitaqwaappfairus.databinding.ActivityMenuVideoKajianBinding
import com.fairus.bitaqwaappfairus.menu.videokajian.adapter.VideoKajianListAdapter
import com.fairus.bitaqwaappfairus.menu.videokajian.data.VideoKajianData
import com.fairus.bitaqwaappfairus.menu.videokajian.model.VideoKajianModel
import java.util.ArrayList

class MenuVideoKajianActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMenuVideoKajianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuVideoKajianBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        setSupportActionBar(binding.toolbarMenuKajian)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<VideoKajianModel> = arrayListOf()
        binding.rvVideoKajian.setHasFixedSize(true)
        list.addAll(VideoKajianData.listData)
        binding.rvVideoKajian.layoutManager = LinearLayoutManager(this)
        val listVideoAdapter = VideoKajianListAdapter(list)
        binding.rvVideoKajian.adapter = listVideoAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}