package info.fekri.boom.ux.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val desc: String,
    val coverImageUrl: String,
    val pdfUrl: String
): Parcelable