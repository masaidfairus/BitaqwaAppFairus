package com.fairus.bitaqwaappfairus.menu.videokajian

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fairus.bitaqwaappfairus.databinding.ActivityDetailVideoKajianBinding
import com.fairus.bitaqwaappfairus.menu.videokajian.model.VideoKajianModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener

class DetailVideoKajianActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_VIDEO_KAJIAN = "extra_video_kajian"
    }

    private lateinit var binding: ActivityDetailVideoKajianBinding

    private lateinit var fullScreenHelper: FullScreenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailVideoKajianBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val video =
            intent.getParcelableExtra<VideoKajianModel>(EXTRA_VIDEO_KAJIAN) as VideoKajianModel

        initView(video)

        fullScreenHelper = FullScreenHelper(this)
    }

    private fun initView(video: VideoKajianModel) {
        lifecycle.addObserver(binding.playerVideo)

        binding.playerVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video.urlVideo, 0f)

                addFullScreenListenerToPlayer()
            }
        })

        binding.tvChannel.text = video.channel
        binding.tvTitle.text = video.title
        binding.tvSpeaker.text = video.speaker
        binding.tvSummary.text = video.summary

        binding.ivShare.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Kajian dengan judul \"${video.title}\" dibawakan oleh \"${video.speaker}\" dan dari channel \"${video.channel}\" \n\n\n Link Video : https://www.youtube.com/watch?v=${video.urlVideo}"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.playerVideo.isFullScreen()) binding.playerVideo.exitFullScreen()
    }

    private fun addFullScreenListenerToPlayer() {
        binding.playerVideo.addFullScreenListener(object : YouTubePlayerFullScreenListener {

            override fun onYouTubePlayerEnterFullScreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                fullScreenHelper.enterFullscreen()
            }

            override fun onYouTubePlayerExitFullScreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                fullScreenHelper.exitFullscreen()
            }
        })
    }
}