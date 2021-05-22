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
            ),
            Video(
                546,
                "Football 1",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://www.youtube.com/watch?v=enziwY3stxU",
                700,
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
            ),
            Video(
                978,
                "Football 2",
                "https://scontent-sin6-2.xx.fbcdn.net/v/t1.15752-9/188702422_484240689350066_5184219742324406957_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=ae9488&_nc_ohc=NERddgpxFrwAX_G4v9u&_nc_ht=scontent-sin6-2.xx&oh=a8a4c14920363d2658734e06523cb40a&oe=60CFFA68",
                "https://r3---sn-npoe7ns7.c.drive.google.com/videoplayback?expire=1621675330&ei=ApWoYJ72MZX_rvIPy-2TEA&ip=2001:ee0:5007:c9e0:b05f:60d0:7d80:19d7&cp=QVRHU0dfVFNRQVhPOmpCUzRRZmZhRVdvSW45YzRhY2RxSXhHSVF1TFA0TXM1bHNLYTNQYVI1WjU&id=36d6bce9042b6faf&itag=18&source=webdrive&requiressl=yes&mh=3v&mm=32&mn=sn-npoe7ns7&ms=su&mv=m&mvi=3&pl=42&ttl=transient&susc=dr&driveid=1zWFcQ2yJH16j6leye7kCjI8UzAfyg-K0&app=explorer&mime=video/mp4&vprv=1&prv=1&dur=1163.296&lmt=1621613574547312&mt=1621660581&sparams=expire,ei,ip,cp,id,itag,source,requiressl,ttl,susc,driveid,app,mime,vprv,prv,dur,lmt&sig=AOq0QJ8wRgIhAKLJoGzDln14xj2bdI8AT3ppioCiva3mFJITTJIVPDN1AiEA3-ksFrYSBetabonO2RxmjTs_iutQSNGe09wN4SRtzjo=&lsparams=mh,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgZtA_oUOPg4-6hmcbv5GNwVcXBF5RvpeOVvlitMeHzYcCIDgx1uVa0v2lSloIGrO2GI7H7wNmipOko3Ij_5Ppa_4P&cpn=vjX1cmLgdJyNdZLR&c=WEB_EMBEDDED_PLAYER&cver=1.20210519.1.1",
                1000,
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
            ), Video(
                123,
                "Football 3",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/187226908_496319061807534_2189928169550971913_n.jpg?_nc_cat=110&ccb=1-3&_nc_sid=ae9488&_nc_ohc=yLVkptGCBSQAX_FVflV&_nc_ht=scontent-sin6-3.xx&oh=f7c0b9dd0953f6d1be49b0d6b52be8b7&oe=60CC4A4B",
                "https://r2---sn-npoe7n7y.c.drive.google.com/videoplayback?expire=1621675401&ei=SZWoYNnlEa-Hir4PypGY6A4&ip=2001:ee0:5007:c9e0:b05f:60d0:7d80:19d7&cp=QVRHU0dfVFROQlhPOmpCUzRSY2dhRVdvSW45ZDFiY2RxSXhHSVJyTVA0TXM1bHNMeDRQYVI1WjU&id=46539a485e1c1fc6&itag=18&source=webdrive&requiressl=yes&mh=TW&mm=32&mn=sn-npoe7n7y&ms=su&mv=m&mvi=2&pl=42&ttl=transient&susc=dr&driveid=1NksJ3M-AvE2Biu7RTnrwPNNRn7OHDCFZ&app=explorer&mime=video/mp4&vprv=1&prv=1&dur=1199.775&lmt=1621613396114220&mt=1621660581&sparams=expire,ei,ip,cp,id,itag,source,requiressl,ttl,susc,driveid,app,mime,vprv,prv,dur,lmt&sig=AOq0QJ8wRAIgYmzR1aWy6gjxpFzZhfrfbHgJAD1k6bR5d6B1vZDBy7oCIFQYlFFTPov4DwihZ-eQIcNqUrsv-pTmtfQI1rn58kPZ&lsparams=mh,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgB1XTMeomobPn_eMgvp7XWnrnREYyPRA2nAQO0DXgiWECIC0sj7_yh5dtOkW7BTkQ81HAq0CqnfyLjNse92N-uFVf&cpn=cbMHdlbRABqGQsn8&c=WEB_EMBEDDED_PLAYER&cver=1.20210519.1.1",
                30,
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