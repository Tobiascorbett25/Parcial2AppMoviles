package com.example.parcial2
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class F1Response(
    val MRData: MRData
)

data class MRData(
    val DriverTable: DriverTable
)

data class DriverTable(
    val Drivers: List<Driver>
)


@Parcelize
data class Driver(
    val driverId: String,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String,
    val url: String,
    val image: String
) : Parcelable
