package info.fekri.boom.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ActivityBuyBookBinding
import info.fekri.boom.extra.KEY_SEND_DATA_BOOK_BUY
import info.fekri.boom.ux.data.BuyBookData

class BuyBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMainBuy)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onStart() {
        super.onStart()
        val dataSend = intent.getParcelableExtra<BuyBookData>(KEY_SEND_DATA_BOOK_BUY)

        if (dataSend != null) {
            uiShow(dataSend)

        } else {
            // visible the error text -->
            binding.txtShowError.visibility = View.VISIBLE

            // make others gone -->
            binding.linearInfoBookBuy.visibility = View.GONE
            binding.itemRadioGroupBuy.visibility = View.GONE
            binding.itemBtnDoneBuy.visibility = View.GONE
        }

    }

    private fun uiShow(dataSend: BuyBookData) {
        binding.collapsingMain.title = dataSend.nameBook

        Glide
            .with(this)
            .load(dataSend.urlPic)
            .error(R.drawable.broken_img)
            .into(binding.imgFlagBuy)

        binding.itemTxtShowBookName.text = dataSend.nameBook
        binding.itemTxtShowBookPrice.text = dataSend.priceBook
        binding.itemTxtShowBookWriter.text = dataSend.writerBook
        binding.itemTxtShowBookPublishedDate.text = dataSend.publishedData
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return true
    }
}