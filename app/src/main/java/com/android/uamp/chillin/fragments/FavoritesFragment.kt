package com.android.uamp.chillin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.android.uamp.chillin.R
import com.android.uamp.chillin.common.DatabaseHandler
import kotlinx.android.synthetic.main.fragment_favorites.view.listViewFavorites

class FavoritesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val db = DatabaseHandler(view.context)
        val favoriteList: ArrayList<HashMap<String, String>> = db.getMusicBasicInfo()

        val adapter: ListAdapter = SimpleAdapter(view.context, favoriteList, R.layout.list_row, arrayOf("name", "artist"), intArrayOf(R.id.name, R.id.artist))
        view.listViewFavorites.adapter = adapter

        return view
    }
}