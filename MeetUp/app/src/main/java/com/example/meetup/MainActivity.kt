package com.example.meetup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val MY_REQUEST_CODE: Int = 717
    private lateinit var firebaseServer: FirebaseServer
    lateinit var providers: List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Ste prihlásený/á ako: "+ user.email!!, Toast.LENGTH_SHORT).show()
        }
        else{
            setContentView(R.layout.activity_main)
            setSupportActionBar(toolbar)

            firebaseServer = FirebaseServer(this) // inicializacia firebase databaze

            //Init signin
            providers = Arrays.asList<AuthUI.IdpConfig>(
                AuthUI.IdpConfig.EmailBuilder().build(), //Email
                AuthUI.IdpConfig.GoogleBuilder().build() //Google
            )

            showSignInOptions()
        }
    }

    private fun showSignInOptions(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.SignTheme)
            .build(),MY_REQUEST_CODE)
     }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK){
                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,""+response!!.error!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}

