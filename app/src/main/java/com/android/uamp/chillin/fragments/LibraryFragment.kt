package com.android.uamp.chillin.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.uamp.chillin.MainActivity
import com.android.uamp.chillin.R
import kotlinx.android.synthetic.main.fragment_library.view.*

class LibraryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

            view.btn_coffee.setOnClickListener {
                (activity as MainActivity).navigateToMediaItem("Morning+Coffee")
            }
            view.btn_nocturnal.setOnClickListener {
                (activity as MainActivity).navigateToMediaItem("Nocturnal+Vibes")
            }
            view.btn_oriental.setOnClickListener {
                (activity as MainActivity).navigateToMediaItem("Oriental+World")
            }
            view.btn_rain.setOnClickListener {
                (activity as MainActivity).navigateToMediaItem("Rainy+Days")
            }
            view.btn_focus.setOnClickListener {
                (activity as MainActivity).navigateToMediaItem("Gentle+Focus")
            }

        return view
    }

}