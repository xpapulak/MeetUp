package com.example.meetup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class QrCodeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_map -> {
                Toast.makeText(this, "Mapa", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MapActivity::class.java)
                // To pass any data to next activity
                // start your next activity
                startActivity(intent)
            }
            R.id.nav_place -> {
                Toast.makeText(this, "Miesta", Toast.LENGTH_SHORT).show()
                val intent2 = Intent(this, PlacesActivity::class.java)
                // To pass any data to next activity
                // start your next activity
                startActivity(intent2)
            }
            R.id.nav_contact -> {
                Toast.makeText(this, "Priatelia", Toast.LENGTH_SHORT).show()
                val intent3 = Intent(this, FriendsActivity::class.java)
                // To pass any data to next activity
                // start your next activity
                startActivity(intent3)
            }
            R.id.nav_qr -> {
                Toast.makeText(this, "QR kód", Toast.LENGTH_SHORT).show()
                val intent4 = Intent(this, QrCodeActivity::class.java)
                // To pass any data to next activity
                // start your next activity
                startActivity(intent4)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
                val intent5 = Intent(this, MainActivity::class.java)
                // To pass any data to next activity
                // start your next activity
                startActivity(intent5)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
