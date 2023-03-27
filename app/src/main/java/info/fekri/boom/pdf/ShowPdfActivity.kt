package info.fekri.boom.pdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import com.rajat.pdfviewer.PdfEngine
import com.rajat.pdfviewer.PdfQuality
import info.fekri.boom.databinding.ActivityShowPdfBinding
import info.fekri.boom.extra.KEY_SEND_PDF_FILE

class ShowPdfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        * Show PDF
        * */

    }

}