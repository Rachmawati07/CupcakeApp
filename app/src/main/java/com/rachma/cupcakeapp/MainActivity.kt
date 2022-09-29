package com.rachma.cupcakeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * MainActivity ini berguna untuk alur pesanan cupcake.
 */

// Untuk mendeklarasikan class yang bernama MainActivity
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    // Dan untuk menyiapkan pengontrol navigasi.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Untuk mengambil NavController dari NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Untuk menyiapkan bilah tindakan yang akan digunakan dengan NavController
        // Akan menampilkan judul di panel aplikasi berdasarkan label tujuan,
        // Dan menampilkan tombol Up kapan pun saat tidak berada di tujuan teratas
        setupActionBarWithNavController(navController)
    }
}