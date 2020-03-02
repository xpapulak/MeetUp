package com.example.meetup

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        setSupportActionBar(toolbar)

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0

        val markerOptions = MarkerOptions()
        markerOptions.position(LatLng(15.0,45.toDouble()))
        markerOptions.title("")
        googleMap?.addMarker(markerOptions)
    }

}

