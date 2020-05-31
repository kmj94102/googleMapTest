package com.example.maptest.googleMap

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class GMarker(var location : LatLng, var address : String) : ClusterItem {
    override fun getSnippet(): String? {
        return address
    }

    override fun getTitle(): String? {
        return address
    }

    override fun getPosition(): LatLng {
        return location
    }
}