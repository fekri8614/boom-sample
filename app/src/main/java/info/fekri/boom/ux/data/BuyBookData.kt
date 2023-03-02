package info.fekri.boom.ux.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BuyBookData(
    // design part -->
    val urlPic : String,
    val nameBook: String,
    val priceBook: String,
    val writerBook: String,
    val publishedData: String,
    // buy part -->
    val urlsBuy: ArrayList<String>,
    val urlPdf: String
): Parcelable