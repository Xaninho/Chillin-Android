package com.android.uamp.chillin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_onboarding.*


open class OnboardingActivity : AppCompatActivity() {

    private var mDots = arrayOfNulls<TextView>(2)
    private var currentPage : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setStatusBarTranslucent(true)

        val slideAdapter = SliderAdapter(this)
        slideViewPager.adapter = slideAdapter

        addDotsIndicator(0)

        slideViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {

                addDotsIndicator(position)
                currentPage = position

                if (currentPage == 0) {
                    nextBtn.isEnabled = true
                    prevBtn.isEnabled = false
                    prevBtn.visibility = View.INVISIBLE
                    nextBtn.text = "Next"
                    prevBtn.text = ""
                } else if (currentPage == mDots.size - 1) {
                    nextBtn.isEnabled = true
                    prevBtn.isEnabled = true
                    prevBtn.visibility = View.VISIBLE
                    nextBtn.text = "Start"
                    nextBtn.setOnClickListener {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    prevBtn.text = "Back"
                } else {
                    nextBtn.isEnabled = true
                    prevBtn.isEnabled = true
                    prevBtn.visibility = View.VISIBLE
                    nextBtn.text = "Next"
                    prevBtn.text = "Back"
                }

            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        })

        nextBtn.setOnClickListener {
            slideViewPager.currentItem = currentPage++
        }
        prevBtn.setOnClickListener {
            slideViewPager.currentItem = currentPage--
        }
    }

    fun addDotsIndicator(position: Int) {

        dotsLayout.removeAllViews()

        for (i in mDots.indices) {
            mDots[i] = TextView(applicationContext)
            mDots[i]?.text = Html.fromHtml("&#8226;")
            mDots[i]?.textSize = 35F
            mDots[i]?.setTextColor(resources.getColor(R.color.darkerWhite))

            dotsLayout.addView(mDots[i])
        }

        if (mDots.isNotEmpty()) {
            mDots[position]?.setTextColor(resources.getColor(R.color.colorWhite))
        }

    }

    protected fun setStatusBarTranslucent(makeTranslucent: Boolean) {
        if (makeTranslucent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

}

