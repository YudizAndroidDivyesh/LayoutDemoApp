package com.example.demooflayouts

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var getLocationBtn : Button
    private lateinit var marker : Marker
    private lateinit var geocoder : Geocoder
    private lateinit var location : Location
    private val permissionId = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        getLocationBtn = findViewById(R.id.location_btn)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


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
        //On Map Click
        mMap.setOnMapClickListener(this)
    }

    private fun getCurrentUserLocation() {
        if(checkPermissions()){
            if(isLocationEnabled()){
                mFusedLocationClient.lastLocation.addOnCompleteListener(this){
                    task-> location = task.result
                    if(location != null){
                       geocoder = Geocoder(this, Locale.getDefault())

                       // Get current Address
                        val list : List<Address>? = geocoder.getFromLocation(location.latitude,
                                location.longitude,1)
                      // Using Address Class get Address
                        val address : Address? = list?.get(0)

                        //Create Marker
                       val markerOption =  MarkerOptions().position(
                            LatLng(location.latitude,
                                location.longitude)
                        ).title(address?.getAddressLine(0))
                        marker = mMap.addMarker(markerOption)!!

                        // Move Camera on Current location
                        mMap.animateCamera(CameraUpdateFactory.
                        newLatLngZoom(LatLng(location.latitude,
                            location.longitude),11f))
                    }else{
                        Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please turn on Location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }else{
            requestPermissions()
        }
    }
    override fun onMapClick(latlng: LatLng) {
        marker.remove()
        val geocoder = Geocoder(this)
        val address : List<Address> = geocoder.getFromLocation(latlng.latitude,latlng.longitude,1) as List<Address>
        // Using Address Class get Address
       marker = mMap.addMarker(MarkerOptions().position(latlng).title(address[0].getAddressLine(0)))!!
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10f))
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
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
                if(item.isChecked) {
                    mMap.uiSettings.isMyLocationButtonEnabled  = true
                    item.isChecked = false
                }else{
                    mMap.uiSettings.isMyLocationButtonEnabled  = false
                    item.isChecked = true
                }


                Toast.makeText(this, item.isChecked.toString(), Toast.LENGTH_SHORT).show()
            }
            R.id.current_pointer_map -> {
                if (item.isChecked){
                    getCurrentUserLocation()
                    item.isChecked = false
                }else{
                    marker.remove()
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