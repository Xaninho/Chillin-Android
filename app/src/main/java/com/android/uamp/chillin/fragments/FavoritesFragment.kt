package com.android.uamp.chillin.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListAdapter
import android.widget.PopupMenu
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.uamp.chillin.R
import com.android.uamp.chillin.common.DatabaseHandler
import kotlinx.android.synthetic.main.fragment_favorites.listViewFavorites
import kotlinx.android.synthetic.main.fragment_favorites.view.listViewFavorites

class FavoritesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val db = DatabaseHandler(view.context)
        val favoriteList: ArrayList<HashMap<String, String>> = db.getMusicBasicInfo()

        val adapter: ListAdapter = SimpleAdapter(view.context, favoriteList, R.layout.list_row, arrayOf("name", "artist"), intArrayOf(R.id.name, R.id.artist))
        view.listViewFavorites.adapter = adapter

        view.listViewFavorites.onItemClickListener =
            AdapterView.OnItemClickListener { _, adapterView, position, _ ->

                val map = listViewFavorites.getItemAtPosition(position) as HashMap<*, *>
                val idMap = map["id"]
                val nameMap = map["name"]
                val artistMap = map["artist"]

                val wrapper: Context = ContextThemeWrapper(view.context, R.style.PopupMenu)
                val menu = PopupMenu(wrapper, adapterView)
                val query = "$nameMap - $artistMap"

                menu.setOnMenuItemClickListener { item ->

                    when (item.itemId) {
                        R.id.item_delete -> {
                            val dbHelper = DatabaseHandler(view.context)
                            dbHelper.UnFavoriteMusic(idMap as String)

                            val favoriteListRefresh: ArrayList<HashMap<String, String>> = db.getMusicBasicInfo()
                            val adapter2: ListAdapter = SimpleAdapter(view.context, favoriteListRefresh, R.layout.list_row, arrayOf("name", "artist"), intArrayOf(R.id.name, R.id.artist))
                            view.listViewFavorites.adapter = adapter2

                            listViewFavorites.invalidateViews()
                            listViewFavorites.refreshDrawableState()

                            Toast.makeText(view.context, getString(R.string.fav_remove), Toast.LENGTH_SHORT).show()
                        }
                        R.id.item_search -> {
                            val uri: Uri = Uri.parse("http://www.google.com/#q=$query")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(intent)
                        }
                        R.id.item_copy -> {
                            val clipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Chillin' Music", query)
                            clipboard.primaryClip = clip
                            Toast.makeText(view.context, getString(R.string.clipboard), Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }

                menu.inflate(R.menu.favorite_menu)
                menu.show()
            }

        return view
    }
}