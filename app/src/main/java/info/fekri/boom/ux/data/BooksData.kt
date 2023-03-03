package info.fekri.boom.ux.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("table_science")
data class ScienceData(
    @PrimaryKey
    val id: Int? = null,

    val title: String,
    val desc: String,
    val coverImageUrl: String,
    val pdfUrl: String

):Parcelable

@Parcelize
@Entity("table_more")
data class MoreUiData(
    @PrimaryKey
    val id: Int? = null,

    val title: String,
    val desc: String,
    val coverImageUrl: String,
    val pdfUrl: String

):Parcelable

@Parcelize
@Entity("table_poems")
data class PoemsUiData(
    @PrimaryKey
    val id: Int? = null,

    val title: String,
    val desc: String,
    val coverImageUrl: String,
    val pdfUrl: String

):Parcelable
