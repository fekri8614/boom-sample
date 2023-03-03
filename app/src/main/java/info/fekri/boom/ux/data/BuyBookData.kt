package info.fekri.boom.ux.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("table_buy_books")
data class BuyBookData(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    // design part -->
    val urlPic : String,
    val nameBook: String,
    val priceBook: String,
    val writerBook: String,
    val publishedData: String,
    // buy part -->
    val urlsBuy: String,
    val urlPdf: String
): Parcelable