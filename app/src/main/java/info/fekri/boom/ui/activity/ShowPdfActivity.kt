package info.fekri.boom.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.fekri.boom.databinding.ActivityShowPdfBinding
import info.fekri.boom.extra.KEY_SEND_PDF_FILE

class ShowPdfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra(KEY_SEND_PDF_FILE)
        if (data != null) showPdf(data)
    }

    private fun showPdf(pdfName: String) {
        val pdfContainer = binding.pdfContainer.fromAsset(pdfName)
        pdfContainer.isVerticalScrollBarEnabled = false
        pdfContainer.isHorizontalScrollBarEnabled = true
        pdfContainer.show()
    }
}