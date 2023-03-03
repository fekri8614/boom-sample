package info.fekri.boom.ux.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import info.fekri.boom.ux.data.BuyBookData
import info.fekri.boom.ux.data.MoreUiData
import info.fekri.boom.ux.data.PoemsUiData
import info.fekri.boom.ux.data.ScienceData

@Dao
interface ScienceDao {

    @Insert
    fun insertAllScience(data: List<ScienceData>)

    @Query("select * from table_science")
    fun getAllScienceBooks(): List<ScienceData>

}

@Dao
interface PoemsDao {

    @Insert
    fun insertAllPoems(data: List<PoemsUiData>)

    @Query("select * from table_poems")
    fun getAllPoemsBooks(): List<PoemsUiData>

}

@Dao
interface MoreDao {

    @Insert
    fun insertAllMore(data: List<MoreUiData>)

    @Query("select * from table_more")
    fun getAllMoreBooks(): List<MoreUiData>

}

// -------------------------------
@Dao
interface BuyBookDao {

    @Insert
    fun insertAllBuy(data: List<BuyBookData>)

    @Query("select * from table_buy_books")
    fun getAllBuyBooks(): List<BuyBookData>

}