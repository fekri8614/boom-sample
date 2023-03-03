package info.fekri.boom.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import info.fekri.boom.R
import info.fekri.boom.databinding.ActivityMainBinding
import info.fekri.boom.databinding.ItemDialogReadMoreBinding
import info.fekri.boom.databinding.ItemDialogShowWelcomeBinding
import info.fekri.boom.databinding.ItemReadMoreDialogBinding
import info.fekri.boom.extra.openWebsite
import info.fekri.boom.ui.fragment.HomeFragment
import info.fekri.boom.ui.fragment.ProfileFragment
import info.fekri.boom.ui.fragment.BuyFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firstRunShowWelcome()
        // set our toolbar -->
        setSupportActionBar(binding.toolbarMain)
    }

    override fun onResume() {
        super.onResume()
        firstRun()

        actionBarTop()
        navigationDrawer()
        navigationBottom()
    }

    private fun firstRunShowWelcome() {
        val shared = getSharedPreferences("mainShared.xml", Context.MODE_PRIVATE)
        val isFirstRun = shared.getBoolean("isFirst", true)
        if (isFirstRun) {
            val dialog = AlertDialog.Builder(this).create()
            val dialogWelcomeBinding = ItemDialogShowWelcomeBinding.inflate(layoutInflater)

            dialog.setView(dialogWelcomeBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            shared.edit().putBoolean("isFirst", false).apply()
        }
    }

    private fun firstRun() {
        replaceFragment(HomeFragment(this))
        binding.bottomNavigationMain.selectedItemId = R.id.menu_home
    }

    private fun actionBarTop() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.draweLayoutMain,
            binding.toolbarMain,
            R.string.open_drawer,
            R.string.close_drawer
        )
        actionBarDrawerToggle.syncState()
        binding.draweLayoutMain.addDrawerListener(actionBarDrawerToggle)
    }

    private fun navigationDrawer() {
        binding.navigationViewMain.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.menu_book_library -> {
                    binding.draweLayoutMain.closeDrawer(GravityCompat.START)
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.oxfordowl.co.uk/")
                        )
                    )
                }

                R.id.menu_read_more -> {
                    binding.draweLayoutMain.closeDrawer(GravityCompat.START)

                    showReadMoreDialog()
                }

            }
            true
        }
    }

    private fun showReadMoreDialog() {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this).create()
        val readMoreBinding = ItemReadMoreDialogBinding.inflate(layoutInflater)

        dialog.setView(readMoreBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        readMoreBinding.btnCancelDialogReadMore.setOnClickListener {
            dialog.dismiss()
        }
        readMoreBinding.btnAcceptDialogReadMore.setOnClickListener {
            dialog.dismiss()

            openSelectedLibrary(readMoreBinding)
        }

    }

    private fun openSelectedLibrary(readMoreBinding: ItemReadMoreDialogBinding) {

        when (readMoreBinding.itemRadioGroupMainDialogRedMore.checkedRadioButtonId) {

            R.id.item_radio_btn_amazon_dialogReadMore -> applicationContext.openWebsite("https://www.amazon.com/free-kids-books/s?k=free+kids+books")

            R.id.item_radio_btn_npl_dialogReadMore -> applicationContext.openWebsite("https://www.nypl.org/")

            R.id.item_radio_btn_oxfordOwl_dialogReadMore -> applicationContext.openWebsite("https://www.oxfordowl.co.uk/for-home/find-a-book/library-page/")

            else -> Toast.makeText(applicationContext, "Something went Wrong!", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun navigationBottom() {
        binding.bottomNavigationMain.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.menu_profile -> replaceFragment(ProfileFragment())

                R.id.menu_home -> replaceFragment(HomeFragment(this))

                R.id.menu_buy -> replaceFragment(BuyFragment(this))

            }

            true
        }
        binding.bottomNavigationMain.setOnItemReselectedListener { }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}