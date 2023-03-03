package info.fekri.boom.ux.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.fekri.boom.ux.data.BuyBookData
import info.fekri.boom.ux.data.MoreUiData
import info.fekri.boom.ux.data.PoemsUiData
import info.fekri.boom.ux.data.ScienceData
import info.fekri.boom.ux.data.dao.BuyBookDao
import info.fekri.boom.ux.data.dao.MoreDao
import info.fekri.boom.ux.data.dao.PoemsDao
import info.fekri.boom.ux.data.dao.ScienceDao

@Database(entities = [ScienceData::class, PoemsUiData::class, MoreUiData::class, BuyBookData::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract val scienceDao: ScienceDao
    abstract val poemsDao: PoemsDao
    abstract val moreDao: MoreDao
    abstract val buyBookDao: BuyBookDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "myDatabase.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }

        }

    }

}