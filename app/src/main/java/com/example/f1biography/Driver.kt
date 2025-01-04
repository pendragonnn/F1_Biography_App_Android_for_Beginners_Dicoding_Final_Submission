package com.example.f1biography

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Driver(
    val name: String,
    val born: String,
    val photo: Int,
    val nationality: String,
    val team: String,
    val carNumber: Int,
    val winCount: Int,
    val podiumCount: Int,
    val description: String
) : Parcelable
