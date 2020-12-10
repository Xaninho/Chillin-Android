package com.android.uamp.chillin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.slide_layout.view.*

class SliderAdapter(var context: Context) : PagerAdapter() {

    private val slide_images = listOf(
            R.drawable.ic_wellness,
            R.drawable.ic_playlist
    )

    private val slide_headers = listOf(
            "Wellness",
            "Playlist"
    )

    private val slide_descriptions = listOf(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    )

    override fun getCount(): Int {
        return slide_headers.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = layoutInflater.inflate(R.layout.slide_layout, container, false)

        val slideImageView : ImageView = view.imageView
        val slideHeading : TextView = view.onboardingHeader
        val slideDescription : TextView = view.onboardingDescription

        slideImageView.setImageResource(slide_images[position])
        slideHeading.text = slide_headers[position]
        slideDescription.text = slide_descriptions[position]

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}