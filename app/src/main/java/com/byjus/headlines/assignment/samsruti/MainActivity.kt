package com.byjus.headlines.assignment.samsruti

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        setupActionBar()
    }

    private fun setupActionBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS )
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_main_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)

        toolbar.setupWithNavController(navController,appBarConfiguration)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.headlinesFragment ->{
                    toolbarTitle.visibility = View.VISIBLE
                    toolbar.title = "HEADLINES"

                }
                R.id.detailsFragment-> {
                    toolbarTitle.visibility = View.GONE
                    toolbar.title = ""
                    toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, null)
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_main_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
