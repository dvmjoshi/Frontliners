package com.mad.frontliners

import com.mad.frontliners.adapter.CenterZoomLayoutManager
import com.mad.frontliners.model.Appreciate
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mad.frontliners.adapter.FrontlinerAdapter

class Optimistic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optimistic)
        val toursRV = findViewById<RecyclerView>(R.id.discover_RV)

        val linearLayoutManager = CenterZoomLayoutManager(this)


        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        toursRV.layoutManager = linearLayoutManager// here u have to add


        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(toursRV)
        toursRV.isNestedScrollingEnabled = false


        val discover = ArrayList<Appreciate>()

        discover.add(
            Appreciate(
                "Productive",
                "Talent and luck might happen to you by chance, but learning is a skill and practice that anyone can accomplish with diligence.\n",
                "" +
                        "https://cdn.dribbble.com/users/1391772/screenshots/9455785/media/89cb77afe0836144afdb9c75afec8c3a.jpg"
            )
        )
        discover.add(
            Appreciate(
                "Precautions",
                "To prevent the spread of COVID-19:\n" +
                        "Clean your hands often. .\n" +
                        "Maintain a safe distance.\n" +
                        "Don’t touch your eyes, nose or mouth.\n" +
                        "Cover your nose and mouth\n" +
                        "SICK?Call the helpline.\n",

                "https://cdn.dribbble.com/users/1171505/screenshots/10798357/media/ea8478781c36e2dd93c49d8d695b260e.png"
            )
        )
        discover.add(
            Appreciate(
                "Help ",
                "There is no exercise better for the heart than reaching down and lifting people up",
                "https://cdn.dribbble.com/users/1731254/screenshots/10821731/media/3edfdb07351cda0bd4e31be88c230448.png"
            )
        )
        discover.add(
            Appreciate(
                "Appreciate",
                "Dear Frontliners, our modern day heroes, thank you for showing kindness and compassion in these difficult times. Thank you for the courage, patience and effort to",
                "https://cdn.dribbble.com/users/1703271/screenshots/10834974/media/bc73cd2e899a671b44257ab35991cfd4.jpg"
            )
        )


        toursRV.adapter = FrontlinerAdapter(this, discover)


    }
}

