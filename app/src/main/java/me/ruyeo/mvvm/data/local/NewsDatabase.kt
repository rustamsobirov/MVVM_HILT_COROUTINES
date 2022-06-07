package me.ruyeo.mvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.ruyeo.mvvm.data.local.entity.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

}