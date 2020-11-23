package com.android.uamp.chillin.common

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_ALBUM TEXT," +
                "$COLUMN_ARTIST TEXT," +
                "$COLUMN_MUSIC_URL TEXT," +
                "$COLUMN_IMAGE_URL TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    private val SQL_VERIFY_FAVORITE = "SELECT * FROM FavoriteMusics WHERE NAME = "
    private val SQL_UNFAVORITE = "DELETE FROM FavoriteMusics WHERE NAME = "

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)

    }

    fun VerifyFavoriteMusic(musicname: String): Boolean{

        val db : SQLiteDatabase = this.writableDatabase

        val cursor: Cursor = db.rawQuery("$SQL_VERIFY_FAVORITE'$musicname'", null)
        return if (cursor.count <= 0) {
            cursor.close()
            false
        } else {
            cursor.close()
            true
        }

    }

    fun UnFavoriteMusic(musicname: String) {

        val db : SQLiteDatabase = this.writableDatabase
        db.execSQL("$SQL_UNFAVORITE'$musicname'")

    }

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Chillin.db"
        const val TABLE_NAME = "FavoriteMusics"
        const val COLUMN_NAME = "Name"
        const val COLUMN_ALBUM = "Album"
        const val COLUMN_ARTIST = "Artist"
        const val COLUMN_MUSIC_URL = "MusicUrl"
        const val COLUMN_IMAGE_URL = "ImageUrl"

    }

}