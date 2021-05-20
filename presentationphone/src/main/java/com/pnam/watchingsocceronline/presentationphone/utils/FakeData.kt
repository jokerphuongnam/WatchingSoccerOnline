package com.pnam.watchingsocceronline.presentationphone.utils

import com.pnam.watchingsocceronline.model.model.Match
import com.pnam.watchingsocceronline.model.model.Team
import com.pnam.watchingsocceronline.model.model.Video

object FakeData {
    val data: MutableList<Video> by lazy {
        mutableListOf(
            Video(
                321,
                "Gym test video",
                "https://tranhtreotuongamia.com/wp-content/uploads/2020/05/buc-tranh-trang-tri-phong-tap-gym-mau-nam-body-sieu-chuan-877136912.jpg",
                "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
                300,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Gym 1",
                        2
                    ),
                    Team(
                        232,
                        "Gym 2",
                        3
                    )
                )
            )
        )
    }
}