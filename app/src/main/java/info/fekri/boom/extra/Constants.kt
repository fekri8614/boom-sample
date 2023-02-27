package info.fekri.boom.extra

import android.content.Context
import android.widget.Toast

// API section -->
const val BASE_URL = "http://openlibrary.org/search.json?q="

// More -->
fun Context.longToast(msg: String) {
    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
}
fun Context.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}