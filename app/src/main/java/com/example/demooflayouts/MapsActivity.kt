package com.example.demooflayouts

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager : LocationManager
    private lateinit var getLocationBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        getLocationBtn = findViewById(R.id.location_btn)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        findViewById<Button>(R.id.style_btn).setOnClickListener {
            try{
                val success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json))
                if (!success){
                    println("Style Parsing failed")
                }
            }catch (e : Exception){
                println("can't find style")
            }
        }
    }

    override fun onMapReady(gMap: GoogleMap) {
        mMap = gMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
       getCurrentUserLocation()
        getUserLocation()

    }

    private fun getCurrentUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }


    }

    private fun getUserLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationLister = LocationListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude
        val    latLng = LatLng(latitude,longitude)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            )
        }

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationLister)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions.isEmpty()){
            getCurrentUserLocation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.satellite_map -> {mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE}
            R.id.roadmap_map -> {mMap.mapType = GoogleMap.MAP_TYPE_NORMAL}
            R.id.hybrid_map -> {mMap.mapType = GoogleMap.MAP_TYPE_HYBRID}
            R.id.terrain_map -> {mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN}
            R.id.current_btn_map -> {
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()            }
            R.id.current_pointer_map -> {
                if(item.isChecked){
                    mMap.uiSettings.isMyLocationButtonEnabled = false
                    item.isChecked = false
                }else{
                    mMap.uiSettings.isZoomControlsEnabled = false
                    item.isChecked = true
                }
            }
            R.id.zoom_btn_map -> {
                if(item.isChecked) {
                    mMap.uiSettings.isZoomControlsEnabled = true
                    item.isChecked = false
                }else{
                    mMap.uiSettings.isZoomControlsEnabled = false
                    item.isChecked = true
                }
            }
        }

        return super.onOptionsItemSelected(item)

    }
}