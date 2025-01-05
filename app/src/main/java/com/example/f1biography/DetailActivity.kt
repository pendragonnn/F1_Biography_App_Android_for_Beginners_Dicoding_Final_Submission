package com.example.f1biography

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    companion object {
        const val key_driver = "key_driver"
    }

    private lateinit var ivDetail: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvBorn: TextView
    private lateinit var tvNationality: TextView
    private lateinit var tvTeam: TextView
    private lateinit var tvCarNumber: TextView
    private lateinit var tvWinCount: TextView
    private lateinit var tvPodiumCount: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnShare: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivDetail = findViewById(R.id.iv_detail)
        tvName = findViewById(R.id.tv_detail_name)
        tvBorn = findViewById(R.id.tv_detail_born)
        tvNationality = findViewById(R.id.tv_detail_nationality)
        tvTeam = findViewById(R.id.tv_detail_team)
        tvCarNumber = findViewById(R.id.tv_detail_car_number)
        tvWinCount = findViewById(R.id.tv_detail_win_count)
        tvPodiumCount = findViewById(R.id.tv_detail_podium_count)
        tvDescription = findViewById(R.id.tv_detail_description)
        btnShare = findViewById(R.id.btn_detail_share)

        val dataDriver = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Driver>(key_driver, Driver::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Driver>(key_driver)
        }

        if (dataDriver != null) {
            ivDetail.setImageResource(dataDriver.photo)
        }
        tvName.text = dataDriver?.name
        tvBorn.text = "Born: ${dataDriver?.born.toString()}"
        tvNationality.text = "Nationality: ${dataDriver?.nationality.toString()}"
        tvTeam.text = "Team: ${dataDriver?.team.toString()}"
        tvCarNumber.text = "Car Number: ${dataDriver?.carNumber.toString()}"
        tvWinCount.text = "Wins: ${dataDriver?.winCount.toString()}"
        tvPodiumCount.text = "Podiums: ${dataDriver?.podiumCount.toString()}"
        tvDescription.text = dataDriver?.description

        btnShare.setOnClickListener {
            val shareText = "Check out this awesome Formula 1 driver!"

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }

            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}