package info.fekri.boom.ux.retrofit.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class BookNewsData(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int
) : Parcelable {
    @Parcelize
    data class Item(
        @SerializedName("accessInfo")
        val accessInfo: AccessInfo,
        @SerializedName("etag")
        val etag: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("kind")
        val kind: String,
        @SerializedName("saleInfo")
        val saleInfo: SaleInfo,
        @SerializedName("searchInfo")
        val searchInfo: SearchInfo,
        @SerializedName("selfLink")
        val selfLink: String,
        @SerializedName("volumeInfo")
        val volumeInfo: VolumeInfo
    ) : Parcelable {
        @Parcelize
        data class AccessInfo(
            @SerializedName("accessViewStatus")
            val accessViewStatus: String,
            @SerializedName("country")
            val country: String,
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("epub")
            val epub: Epub,
            @SerializedName("pdf")
            val pdf: Pdf,
            @SerializedName("publicDomain")
            val publicDomain: Boolean,
            @SerializedName("quoteSharingAllowed")
            val quoteSharingAllowed: Boolean,
            @SerializedName("textToSpeechPermission")
            val textToSpeechPermission: String,
            @SerializedName("viewability")
            val viewability: String,
            @SerializedName("webReaderLink")
            val webReaderLink: String
        ) : Parcelable {
            @Parcelize
            data class Epub(
                @SerializedName("isAvailable")
                val isAvailable: Boolean
            ) : Parcelable

            @Parcelize
            data class Pdf(
                @SerializedName("isAvailable")
                val isAvailable: Boolean
            ) : Parcelable
        }

        @Parcelize
        data class SaleInfo(
            @SerializedName("country")
            val country: String,
            @SerializedName("isEbook")
            val isEbook: Boolean,
            @SerializedName("saleability")
            val saleability: String
        ) : Parcelable

        @Parcelize
        data class SearchInfo(
            @SerializedName("textSnippet")
            val textSnippet: String
        ) : Parcelable

        @Parcelize
        data class VolumeInfo(
            @SerializedName("allowAnonLogging")
            val allowAnonLogging: Boolean,
            @SerializedName("authors")
            val authors: List<String>,
            @SerializedName("averageRating")
            val averageRating: Double,
            @SerializedName("canonicalVolumeLink")
            val canonicalVolumeLink: String,
            @SerializedName("categories")
            val categories: List<String>,
            @SerializedName("contentVersion")
            val contentVersion: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("imageLinks")
            val imageLinks: ImageLinks,
            @SerializedName("industryIdentifiers")
            val industryIdentifiers: List<IndustryIdentifier>,
            @SerializedName("infoLink")
            val infoLink: String,
            @SerializedName("language")
            val language: String,
            @SerializedName("maturityRating")
            val maturityRating: String,
            @SerializedName("pageCount")
            val pageCount: Int,
            @SerializedName("panelizationSummary")
            val panelizationSummary: PanelizationSummary,
            @SerializedName("previewLink")
            val previewLink: String,
            @SerializedName("printType")
            val printType: String,
            @SerializedName("publishedDate")
            val publishedDate: String,
            @SerializedName("publisher")
            val publisher: String,
            @SerializedName("ratingsCount")
            val ratingsCount: Int,
            @SerializedName("readingModes")
            val readingModes: ReadingModes,
            @SerializedName("subtitle")
            val subtitle: String,
            @SerializedName("title")
            val title: String
        ) : Parcelable {
            @Parcelize
            data class ImageLinks(
                @SerializedName("smallThumbnail")
                val smallThumbnail: String,
                @SerializedName("thumbnail")
                val thumbnail: String
            ) : Parcelable

            @Parcelize
            data class IndustryIdentifier(
                @SerializedName("identifier")
                val identifier: String,
                @SerializedName("type")
                val type: String
            ) : Parcelable

            @Parcelize
            data class PanelizationSummary(
                @SerializedName("containsEpubBubbles")
                val containsEpubBubbles: Boolean,
                @SerializedName("containsImageBubbles")
                val containsImageBubbles: Boolean
            ) : Parcelable

            @Parcelize
            data class ReadingModes(
                @SerializedName("image")
                val image: Boolean,
                @SerializedName("text")
                val text: Boolean
            ) : Parcelable
        }
    }
}