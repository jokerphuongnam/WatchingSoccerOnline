package com.pnam.watchingsocceronline.presentationphone.utils

import com.pnam.watchingsocceronline.model.model.Match
import com.pnam.watchingsocceronline.model.model.Team
import com.pnam.watchingsocceronline.model.model.Video
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.random.Random

object FakeData {

    @ExperimentalCoroutinesApi
    suspend fun getFakeData(): MutableList<Video> = suspendCancellableCoroutine {
        it.resume(data){

        }
    }

    private val data: MutableList<Video> by lazy {
        mutableListOf(
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Mancity vs Chelsea",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://soccerbats.b-cdn.net/barcelona%20vs%20atletico%20madrid.mp4",
                1000,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Mancity",
                        2
                    ),
                    Team(
                        232,
                        "Chelsea",
                        3
                    )
                )
            ),
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barsa vs Real",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://soccerbats.b-cdn.net/Chelsea%20v%20Manchester%20City.mp4",
                30,
                1638926400000,
                mutableListOf(),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Real",
                        3
                    )
                )
            )
        )
    }
}