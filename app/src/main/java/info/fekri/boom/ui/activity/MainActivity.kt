package info.fekri.boom.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import info.fekri.boom.ui.fragment.HomeFragment
import info.fekri.boom.ui.fragment.ProfileFragment
import info.fekri.boom.ui.fragment.SearchFragment

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
            val dialogWelcomeBinding =ItemDialogShowWelcomeBinding.inflate(layoutInflater)

            dialog.setView(dialogWelcomeBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            shared.edit().putBoolean("isFirst", false).apply()
        }
    }
    private fun firstRun() {
        replaceFragment(HomeFragment())
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

                R.id.menu_free_books -> {
                    binding.draweLayoutMain.closeDrawer(GravityCompat.START)

                    Snackbar.make(binding.root, "Going to free books!...", Snackbar.LENGTH_LONG)
                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                        .setBackgroundTint(ContextCompat.getColor(this, R.color.primaryColor))
                        .setAction("Cancel") {
                            Toast.makeText(applicationContext, "Canceled!", Toast.LENGTH_SHORT)
                                .show()
                        }
                        .show()
                }

                R.id.menu_nyt_web -> {
                    binding.draweLayoutMain.closeDrawer(GravityCompat.START)

                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.nytimes.com/")
                        )
                    )
                }

                R.id.menu_read_more -> {
                    binding.draweLayoutMain.closeDrawer(GravityCompat.START)

                    val dialog = AlertDialog.Builder(this).create()
                    val dialogBinding = ItemDialogReadMoreBinding.inflate(layoutInflater)

                    dialog.setView(dialogBinding.root)
                    dialog.setCancelable(true)
                    dialog.show()
                }

            }

            true
        }
    }
    private fun navigationBottom() {
        binding.bottomNavigationMain.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.menu_profile -> replaceFragment(ProfileFragment())

                R.id.menu_home -> replaceFragment(HomeFragment())

                R.id.menu_search -> replaceFragment(SearchFragment())

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