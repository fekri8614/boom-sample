package info.fekri.boom.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.button.MaterialButton
import com.rajat.pdfviewer.PdfViewerActivity
import com.squareup.picasso.Picasso
import info.fekri.boom.R
import info.fekri.boom.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    // define variables as lateinit (will initialize)
    private lateinit var titleTV: AppCompatTextView
    private lateinit var subtitleTV: AppCompatTextView
    private lateinit var publisherTV: AppCompatTextView
    private lateinit var descTV: AppCompatTextView
    private lateinit var pageTV: AppCompatTextView
    private lateinit var publisherDate: AppCompatTextView
    private lateinit var previewBtn: MaterialButton
    private lateinit var buyBtn: MaterialButton
    private lateinit var pdfBtn: MaterialButton
    private lateinit var bookIV: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize variables
        titleTV = binding.idTVTitle
        subtitleTV = binding.idTVSubTitle
        publisherTV = binding.idTVPublisher
        descTV = binding.idTVDescription
        pageTV = binding.idTVNoOfPages
        publisherDate = binding.idTVPublishDate
        previewBtn = binding.idBtnPreview
        buyBtn = binding.idBtnBuy
        bookIV = binding.idIVBook
        pdfBtn = binding.idBtnPdf

        // get the data which passed from adapter
        val title = intent.getStringExtra("title")
        val subtitle = intent.getStringExtra("subtitle")
        val publisher = intent.getStringExtra("publisher")
        val publishedDate = intent.getStringExtra("publishedDate")
        val description = intent.getStringExtra("description")
        val pageCount = intent.getIntExtra("pageCount", 0)
        val thumbnail = intent.getStringExtra("thumbnail")
        val previewLink = intent.getStringExtra("previewLink")
        val infoLink = intent.getStringExtra("infoLink")
        val buyLink = intent.getStringExtra("buyLink")
        val pdfLink = intent.getStringExtra("pdfLink")

        // set data
        titleTV.text = title
        subtitleTV.text = subtitle
        publisherTV.text = publisher
        publisherDate.text = publishedDate
        descTV.text = description
        pageTV.text = "Page No: $pageCount"

        Picasso
            .get()
            .load(thumbnail)
            .error(R.drawable.broken_img)
            .into(bookIV)

        // add onClickListener on buttons
        previewBtn.setOnClickListener {
            // check is nullable or empty
            if (previewLink.isNullOrEmpty())
            // show toast if is empty or null
                Toast.makeText(
                    this,
                    "No preview Link present",
                    Toast.LENGTH_SHORT
                ).show()
            else
            // if is not, open url
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(previewLink)
                    )
                )
        }
        buyBtn.setOnClickListener {
            // check is null or empty
            if (buyLink.isNullOrEmpty())
            // show toast if is true
                Toast.makeText(
                    applicationContext,
                    "No buy page presenter for this book",
                    Toast.LENGTH_SHORT
                ).show()
            else
            // open url if is false (is not empty or null)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(buyLink)))
        }
        pdfBtn.setOnClickListener {
            // check is null or empty
            if (pdfLink.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "No PDF link found!", Toast.LENGTH_SHORT).show()
            } else {
                // if is not empty open here
                startActivity(
                    PdfViewerActivity
                        .launchPdfFromUrl(
                            applicationContext,
                            pdfLink,
                            title,
                            "",
                            false
                        )
                )
            }
        }

    }

}