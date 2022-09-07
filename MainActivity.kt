package com.example.fcs

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

   // private var reviewlist = arrayListOf<Review>()   //리뷰에 있는 정보들을 들고 와야 함.

    var reviewlist = arrayListOf<Review>(
        Review("리또리또",5,2,3),
        Review("리또리또2",4,2,3),
        Review("리또리또3",5,2,3),
        Review("리또리또4",3,2,3),
        Review("리또리또5",5,2,3),
        Review("리또리또6",2,2,3),
        Review("리또리또7",5,2,3),
        Review("리또리또8",1,2,3),
        Review("리또리또9",5,2,3),
        Review("리또리또10",3,2,3)

    )

    override fun onCreate(savedInstanceState: Bundle?) { //액티비티 실행 시작지점
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*val item = arrayOf("a","b","c","d","e","f","g","h","i","j")
        //context란 한 액티비티의 모든 정보 의미
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)*/

        val adapter = ReviewAdapter(this,reviewlist)
        listView.adapter = adapter


    }
}