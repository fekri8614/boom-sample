package info.fekri.boom.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ActivityShowHeroesBinding
import info.fekri.boom.extra.KEY_SEND_DATA_TO_SHOW
import info.fekri.boom.ux.retrofit.models.HeroesData

class ShowHeroesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowHeroesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMainBuy)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onStart() {
        super.onStart()

        try {
            val dataHeroes = intent.getParcelableExtra<HeroesData.HeroesDataItem>(KEY_SEND_DATA_TO_SHOW)

            if (dataHeroes != null) {

                binding.collapsingMain.title            = dataHeroes.name

                // ------------------------------

                Glide
                    .with(binding.root)
                    .load(dataHeroes.images.md)
                    .error(R.drawable.broken_img)
                    .into(binding.imgFlagHero)

                // ------------------------------------------

                binding.itemTxtShowHeroFullName.text    = dataHeroes.biography.fullName
                binding.itemTxtShowHeroAlias.text       = dataHeroes.biography.aliases.toString()
                binding.itemTxtShowPlaceBirth.text      = dataHeroes.biography.placeOfBirth

                // ----------------------------

                binding.itemTxtShowFirstAppearance.text = dataHeroes.biography.firstAppearance
                binding.itemTxtShowPublisher.text       = dataHeroes.biography.publisher
                binding.itemTxtShowOccupation.text      = dataHeroes.work.occupation
                binding.itemTxtShowAffiliation.text     = dataHeroes.connections.groupAffiliation
                binding.itemTxtShowRelation.text        = dataHeroes.connections.relatives

                // -------------------------------

                binding.itemTxtShowPowers.text          = dataHeroes.powerstats.toString()
                binding.itemTxtShowAppearance.text      = dataHeroes.appearance.toString()

            } else {
                binding.txtShowError.visibility         = View.VISIBLE
            }
        } catch (e: Exception) {
            Log.v("boomLog", e.message.toString())
        }




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return true
    }
}