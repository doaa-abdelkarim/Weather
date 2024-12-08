package com.example.core.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.widget.Toast
import com.example.core.utils.Constants.DELAY_TP_GET_LAST_KNOWN_LOCATION
import com.example.core.utils.Constants.LOCATION_FASTEST_INTERVAL
import com.example.core.utils.Constants.LOCATION_INTERVAL
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import java.io.IOException
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class LocationUtil(
    context: Context,
    private val onLocationDetected: (Location) -> Unit
) {
    private val TAG: String = this.javaClass.simpleName
    private val locationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, LOCATION_INTERVAL)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(LOCATION_FASTEST_INTERVAL)
            //    .setMaxUpdateDelayMillis(locationMaxWaitTime)
            .build()
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                if (location != null) {
                    timer.cancel()
                    removeLocationUpdate()
                }
            }
        }
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var timer: Timer

    init {
        initFusedLocationClient(context)
    }

    private fun initFusedLocationClient(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(activity: Activity) {
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(onTokenCanceledListener: OnTokenCanceledListener): CancellationToken {
                    return CancellationTokenSource().token
                }

                override fun isCancellationRequested(): Boolean {
                    return false
                }
            }).addOnSuccessListener(activity) { location: Location? ->
            if (location == null) startLocationUpdates(activity)
            else onLocationDetected(location)
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(activity: Activity) {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        timer = Timer()
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    removeLocationUpdate()
                    getLastLocation(activity)
                }
            },
            DELAY_TP_GET_LAST_KNOWN_LOCATION
        )
    }

    private fun removeLocationUpdate() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(activity: Activity) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener(activity) { location ->
                onLocationDetected(
                    location
                )
            }
    }

    fun getAddress(context: Context, lat: Double, lng: Double): Address? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>?
        var address: Address? = null
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1)
            if (addresses != null) address = addresses[0]
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return address
    }

}