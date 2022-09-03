package com.example.fcs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ReviewAdapter (private val context : Context, private val ReviewList: ArrayList<Review>) : BaseAdapter() {


    override fun getCount(): Int {

        return ReviewList.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    fun getItem(position : Int , convertView: Int): Any {
        return ReviewList[position]

    }

    override fun getItemId(position: Int): Long {
        return 0
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_review_user,null)

        //val profile = view.findViewById<ImageView>(R.id.review_photo)
        val name = view.findViewById<TextView>(R.id.review_name)
        val clean = view.findViewById<TextView>(R.id.review_clean)
        val nice = view.findViewById<TextView>(R.id.review_nice)
        val taste = view.findViewById<TextView>(R.id.review_taste)

        val review = ReviewList[position]

        //profile.setImageResource(review.review_image)
        name.text = review.review_name
        clean.text = review.review_clean.toString()
        nice.text = review.review_nice.toString()
        taste.text = review.review_taste.toString()

        return view
    }
}