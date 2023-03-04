package info.fekri.boom.ux.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.fekri.boom.ux.data.MoreUiData
import info.fekri.boom.ux.data.PoemsUiData
import info.fekri.boom.ux.data.ScienceData
import info.fekri.boom.ux.room.dao.MoreDao
import info.fekri.boom.ux.room.dao.PoemsDao
import info.fekri.boom.ux.room.dao.ScienceDao

@Database(entities = [ScienceData::class, PoemsUiData::class, MoreUiData::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract val scienceDao: ScienceDao
    abstract val poemsDao: PoemsDao
    abstract val moreDao: MoreDao

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