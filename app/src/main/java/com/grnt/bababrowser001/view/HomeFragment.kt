package com.grnt.bababrowser001.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.grnt.bababrowser001.R

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    var navigation: NavController? = null
    lateinit var btnOpenBrowsers:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_home, container, false)
        btnOpenBrowsers =  view.findViewById(R.id.btnOpenBrowsers)
        btnOpenBrowsers.setOnClickListener {
            val intent = Intent(activity, BrowserActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }


}