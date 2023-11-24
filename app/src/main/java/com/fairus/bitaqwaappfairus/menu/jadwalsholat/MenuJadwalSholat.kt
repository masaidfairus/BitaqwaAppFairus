package com.fairus.bitaqwaappfairus.menu.jadwalsholat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.fairus.bitaqwaappfairus.databinding.ActivityMenuJadwalSholatBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MenuJadwalSholat : AppCompatActivity() {
    private lateinit var binding: ActivityMenuJadwalSholatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuJadwalSholatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        initView()
        getPrayTimeData("dkijakarta")
    }

    private fun initView() {
        val waktu = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEEE, d MMM", Locale.getDefault())
        val formattedDate = dateFormat.format(waktu)

        binding.tvDatePray.text = formattedDate
    }

    private fun getPrayTimeData(location: String) {
        val client = AsyncHttpClient()
        val url = "https://muslimsalat.com/$location/daily.json?api_key=1e0c24bbed38ac57c6757662bd3cb92e"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                binding.pbJadwalSholat.visibility = View.INVISIBLE
                val responData = responseBody?.let { String(it) }

                try {
                    Log.d("Jadwal Sholat", "API Response: $responData")

                    val responObject = JSONObject(responData.toString())
                    val prayTimeArray = responObject.getJSONArray("items")

                    Log.d("Jadwal Sholat", "Array Size: ${prayTimeArray.length()}")

                        if (prayTimeArray.length() > 0) {
                            // Log details of the first item in the array
                            val firstPrayTime = prayTimeArray.getJSONObject(0)
                            Log.d("Jadwal Sholat", "First Prayer Time: $firstPrayTime")

                            // Extract and display prayer times
                            binding.tvPrayTimeSubuh.text = firstPrayTime.optString("fajr")
                            binding.tvPrayTimeSunrise.text = firstPrayTime.optString("shurooq")
                            binding.tvPrayTimeDzuhur.text = firstPrayTime.optString("dhuhr")
                            binding.tvPrayTimeAshar.text = firstPrayTime.optString("asr")
                            binding.tvPrayTimeMaghrib.text = firstPrayTime.optString("maghrib")
                            binding.tvPrayTimeIsya.text = firstPrayTime.optString("isha")

                            // Set the location text
                            binding.tvLocation.text = responObject.optString("title")
                        }
                        else {
                        Log.d("Jadwal Sholat", "No prayer times found in the array")
                    }

                } catch (error: Exception) {
                    Toast.makeText(this@MenuJadwalSholat, error.message, Toast.LENGTH_SHORT)
                        .show()
                    error.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Toast.makeText(this@MenuJadwalSholat, error?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

