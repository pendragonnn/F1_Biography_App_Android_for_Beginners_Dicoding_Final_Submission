package com.example.f1biography

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvDrivers: RecyclerView
    private val list = ArrayList<Driver>()
    private lateinit var customToolbar: Toolbar
    private lateinit var iconToolbar: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvDrivers = findViewById(R.id.rv_drivers)
        rvDrivers.setHasFixedSize(true)

        list.addAll(getListDrivers())
        showRecyclerList()

        customToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(customToolbar)

        iconToolbar = findViewById(R.id.toolbar_icon)
        iconToolbar.setOnClickListener{
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
            Log.d("MainActivity", "Toolbar: $customToolbar, Icon: $iconToolbar")
        }
    }

    private fun showRecyclerList() {
        rvDrivers.layoutManager = LinearLayoutManager(this)
        val listDriverAdapter = ListDriverAdapter(list)
        rvDrivers.adapter = listDriverAdapter
    }

    private fun getListDrivers(): Collection<Driver> {
        val dataName = resources.getStringArray(R.array.drivers_name_list)
        val dataBorn = resources.getStringArray(R.array.drivers_born_list)
        val dataNationality = resources.getStringArray(R.array.drivers_nationality_list)
        val dataTeam = resources.getStringArray(R.array.drivers_team_list)
        val dataCarNumber = resources.getIntArray(R.array.drivers_car_number_list)
        val dataWinCount = resources.getIntArray(R.array.drivers_wins_count_list)
        val dataPodiumCount = resources.getIntArray(R.array.drivers_podiums_count_list)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescription = resources.getStringArray(R.array.drivers_descriptions_list)

        val listDriver = ArrayList<Driver> ()
        for(i in dataName.indices) {
            val driver = Driver(dataName[i], dataBorn[i], dataPhoto.getResourceId(i, -1), dataNationality[i], dataTeam[i], dataCarNumber[i], dataWinCount[i], dataPodiumCount[i], dataDescription[i])
            listDriver.add(driver)
        }
        return listDriver
    }
}