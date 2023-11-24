package com.fairus.bitaqwaappfairus.dashboard.data

import com.fairus.bitaqwaappfairus.R
import com.fairus.bitaqwaappfairus.dashboard.model.InspirationModel

object InspirationData {
        private val inspirationImage = intArrayOf(
            R.drawable.img_inspiration,
            R.drawable.img_inspiration2,
            R.drawable.img_inspiration3,
            R.drawable.img_inspiration4,
            R.drawable.img_inspiration5,
            R.drawable.img_inspiration6,
            R.drawable.img_inspiration7
        )

        val listData: ArrayList<InspirationModel>
            get() {
                val list = arrayListOf<InspirationModel>()
                for (position in inspirationImage.indices) {
                    val inspiration = InspirationModel()
                    inspiration. inspirationImage = inspirationImage[position]
                    list.add(inspiration)
                }
                return list
        }
}
