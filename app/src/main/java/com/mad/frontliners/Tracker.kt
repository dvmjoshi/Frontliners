package com.mad.frontliners


import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mad.frontliners.adapter.StateAdapter
import kotlinx.android.synthetic.main.activity_tracker.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class Tracker : AppCompatActivity() {
    lateinit var stateAdapter: StateAdapter

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header, list, false))
        fetchResults()
        swipeToRefresh.setOnRefreshListener {
            fetchResults()
        }
        list.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (list.getChildAt(0) != null) {
                    swipeToRefresh.isEnabled = list.firstVisiblePosition === 0 && list.getChildAt(
                        0
                    ).getTop() === 0
                }
            }
        })


        tracker_back.setOnClickListener() {
            onBackPressed()
        }


    }

    private fun fetchResults() {
        //Concept 2. Coroutine
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.clone().execute() }
            if (response.isSuccessful) {
                swipeToRefresh.isRefreshing = false
                val data = Gson().fromJson(response.body?.string(), Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombineData(data.statewise[0])
                    bindStateWiseData(data.statewise.subList(0, data.statewise.size))
                }
            }
        }
    }

    private fun bindCombineData(data: StatewiseItem) {
        val lastUpdatedTime = data.lastupdatedtime
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        last_update.text = "Last Updated\n ${getTimeAgo(
            dateFormat.parse(lastUpdatedTime)
        )}"
        confirmed.text = data.confirmed
        active.text = data.active
        recovered.text = data.recovered
        deceased.text = data.deaths

    }

    private fun bindStateWiseData(subList: List<StatewiseItem>) {
        stateAdapter = StateAdapter(subList)
        list.adapter = stateAdapter
    }

    fun getTimeAgo(past: Date): String {
        val now = Date()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

        return when {
            seconds < 60 -> {
                "Few seconds ago"
            }
            minutes < 60 -> {
                "$minutes minutes ago"
            }
            hours < 24 -> {
                "$hours hour ${minutes % 60} min ago"
            }
            else -> {
                SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
            }
        }
    }


}
