package com.example.app_movil_alerta_terremotos.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LocationViewModel (application: Application) : AndroidViewModel(application){

    private val locationLiveData = LocationLiveData(application)
    fun getLocationLiveData() = locationLiveData
    fun startLocationUpdates() {
        locationLiveData.startLocationUpdates()
    }

}