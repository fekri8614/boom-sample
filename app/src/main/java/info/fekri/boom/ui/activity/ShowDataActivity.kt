package info.fekri.boom.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ActivityShowDataBinding
import info.fekri.boom.ui.fragment.KEY_SEND_DATA_SHOW
import info.fekri.boom.ux.data.Book

class ShowDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowDataBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)

        binding.collapsingMain.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
        // set back item to app
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val dataBooks = intent.getParcelableExtra<Book>(KEY_SEND_DATA_SHOW)
        if (dataBooks != null) {

            Glide
                .with(this)
                .load(dataBooks.coverImageUrl)
                .into(binding.imgCover)

            binding.txtStoryTitle.text = dataBooks.title
            binding.txtStoryDesc.text = dataBooks.desc

            binding.fabOpenWebsite.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(dataBooks.website)))
            }

            supportActionBar!!.title = dataBooks.title

        } else {

            Glide
                .with(this)
                .load(R.drawable.broken_img)
                .into(binding.imgCover)

            binding.txtStoryDesc.text = "null"
            binding.txtStoryTitle.text = "null"

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }
}