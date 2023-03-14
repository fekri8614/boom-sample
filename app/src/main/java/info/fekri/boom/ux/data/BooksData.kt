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

// variables for our book details
// BooksFragment Recycler variables
@Parcelize
data class BookRvModel(
    val title: String,
    var subtitle: String,
    var authors: ArrayList<String>,
    var publisher: String,
    var publishedDate: String,
    var description: String,
    var pageCount: Int,
    var thumbnail: String,
    var previewLink: String,
    var infoLink: String,
    var buyLink: String,
    var pdfLink: String?
): Parcelable

