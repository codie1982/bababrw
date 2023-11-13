package com.grnt.bababrowser001

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.grnt.bababrowser001.view.HomeFragment

class MainActivity : AppCompatActivity() {
    private val navHost by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun openToBrowser(from: BrowserDirection, customTabSessionId: String? = null) {
        //@IdRes val fragmentId = if (from.fragmentId != 0) from.fragmentId else null
        Bundle().putString("customTabSessionId",customTabSessionId)
        Bundle().putInt("customTabSession",from.fragmentId)
        navHost.navController.navigate(R.id.action_homeFragment_to_browserActivity2)
    }
}