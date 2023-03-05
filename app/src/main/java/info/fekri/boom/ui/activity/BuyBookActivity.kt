package info.fekri.boom.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import info.fekri.boom.databinding.ActivityBuyBookBinding
import info.fekri.boom.extra.KEY_SEND_DATA_BOOK_BUY

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
        // write your code here
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return true
    }
}