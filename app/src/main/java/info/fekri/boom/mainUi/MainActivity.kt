package info.fekri.boom.mainUi

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import info.fekri.boom.R
import info.fekri.boom.databinding.ActivityMainBinding
import info.fekri.boom.databinding.ItemDialogShowWelcomeBinding
import info.fekri.boom.databinding.ItemReadMoreDialogBinding
import info.fekri.boom.extra.openWebsite
import info.fekri.boom.mainUi.fragments.BooksFragment
import info.fekri.boom.moreUi.fragments.HeroesFragment
import info.fekri.boom.mainUi.fragments.HomeFragment
import info.fekri.boom.mainUi.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /**
     * This is the onCreate method of the MainActivity class, which is the main entry point for the app.
     * It initializes the view binding, shows a welcome message if this is the first run, and sets the toolbar.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firstRunShowWelcome()
        // set our toolbar -->
        setSupportActionBar(binding.toolbarMain)
    }

    /**
     * This is the onResume method of the MainActivity class, which is called when the activity becomes visible to the user.
     * It resumes the app, sets up the action bar, the navigation drawer, and the bottom navigation.
     * */
    override fun onResume() {
        super.onResume()
        firstRun()

        actionBarTop()
        navigationDrawer()
        navigationBottom()
    }

    /**
     * This is a private method that shows a welcome message to the user if this is the first time they run the app.
     * It uses a shared preference object to store and retrieve the first run status, and an alert dialog to display the message.
     * */
    private fun firstRunShowWelcome() {
        val shared = getSharedPreferences("mainShared.xml", Context.MODE_PRIVATE)
        val isFirstRun = shared.getBoolean("isFirst", true)

        // Check if the app is running for the first time.
        if (isFirstRun) {
            // Create a dialog to welcome the user.
            val dialog = AlertDialog.Builder(this).create()
            val dialogWelcomeBinding = ItemDialogShowWelcomeBinding.inflate(layoutInflater)
            dialog.setView(dialogWelcomeBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            // Dismiss the dialog when the user clicks the button.
            dialogWelcomeBinding.btnGoWelcome.setOnClickListener { dialog.dismiss() }

            // Update the shared preferences to indicate the app is not running for the first time
            shared.edit().putBoolean("isFirst", false).apply()
        }
    }

    /**
     * This method is called when the app is launched for the first time
     * It replaces the current fragment with the HomeFragment
     * It also sets the bottom navigation menu item to the home icon
     * */
    private fun firstRun() {
        replaceFragment(HomeFragment())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_home
    }

    /**
     * This method sets up the action bar at the top of the screen
     * It creates an action bar drawer toggle that can open and close the navigation drawer
     * It synchronizes the state of the toggle with the drawer layout
     * It also adds the toggle as a drawer listener to the drawer layout
     */
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

    /**
     * This method sets up the navigation drawer that slides from the left of the screen.
     * It sets a navigation item selected listener that handles the click events of the menu items.
     * Depending on the item id, it performs different actions such as closing the drawer,
     * starting an activity, showing a dialog, or replacing a fragment.
     * */
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

                R.id.menu_heroes -> {
                    binding.draweLayoutMain.closeDrawer(GravityCompat.START)
                    binding.bottomNavigationMain.isSelected = false

                    // replace fragment -->
                    replaceFragment(HeroesFragment())
                }

            }
            true
        }
    }

    /**
     * This method shows a dialog that asks the user to read more books.
     * It creates an alert dialog with a custom view that is inflated from a binding.
     * It sets the dialog to be cancelable and shows it on the screen.
     * It also sets click listeners for the cancel and accept buttons of the dialog.
     * The cancel button dismisses the dialog.
     * The accept button dismisses the dialog and calls another method to open a selected
     * */
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

    /**
     * This method opens a website based on the selected radio button from the read more dialog.
     * It takes the read more binding as a parameter and checks the id of the checked radio button.
     * Depending on the id, it calls another method to open a website with a specific url.
     * If none of the radio buttons are checked, it shows a toast message that something went.
     * */
    private fun openSelectedLibrary(readMoreBinding: ItemReadMoreDialogBinding) {

        when (readMoreBinding.itemRadioGroupMainDialogRedMore.checkedRadioButtonId) {

            R.id.item_radio_btn_amazon_dialogReadMore -> openWebsite("https://www.amazon.com/free-kids-books/s?k=free+kids+books")

            R.id.item_radio_btn_npl_dialogReadMore -> openWebsite("https://www.nypl.org/")

            R.id.item_radio_btn_oxfordOwl_dialogReadMore -> openWebsite("https://www.oxfordowl.co.uk/for-home/find-a-book/library-page/")

            else -> Toast.makeText(applicationContext, "Something went Wrong!", Toast.LENGTH_SHORT)
                .show()
        }

    }

    /**
     * This method sets up the bottom navigation bar that has three menu items.
     * It sets an item selected listener that handles the click events of the menu items.
     * Depending on the item id, it replaces the current fragment with a different one.
     * It also sets an item reselected listener that does nothing.
     * */
    private fun navigationBottom() {
        binding.bottomNavigationMain.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.menu_profile -> replaceFragment(ProfileFragment(this))

                R.id.menu_home -> replaceFragment(HomeFragment())

                R.id.menu_books -> replaceFragment(BooksFragment())

            }

            true
        }
        binding.bottomNavigationMain.setOnItemReselectedListener { }
    }

    /**
     * This method replaces the current fragment with a new one.
     * It takes the new fragment as a parameter and creates a fragment transaction.
     * It replaces the frame layout with the new fragment.
     * It adds the transaction to the back stack.
     * It commits the transaction.
     * */
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

}