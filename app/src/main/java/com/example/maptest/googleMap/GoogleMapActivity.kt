package com.example.maptest.googleMap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maptest.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager


class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        val SEOUL = LatLng(37.56, 126.97)
    }
    lateinit var mMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val markerOptions = MarkerOptions()
        markerOptions.position(SEOUL)
        markerOptions.title("서울")
        markerOptions.snippet("한국의 수도")
        mMap.addMarker(markerOptions)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14f))

        val mClusterManager = ClusterManager<GMarker>(this, mMap)
        mMap.setOnCameraIdleListener(mClusterManager)
        mMap.setOnMarkerClickListener(mClusterManager)
        for(i in 0 .. 10){
            val lat = 37.56 + ((0.0 + i )/ 100)
            val lng = 126.97 + ((0.0 + i) /100)
            mClusterManager.addItem(GMarker(LatLng(lat, lng), "House $i" ))
        }
    }
}