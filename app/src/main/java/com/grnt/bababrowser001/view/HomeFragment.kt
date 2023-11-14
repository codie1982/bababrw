package com.grnt.bababrowser001.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.MainActivity
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.control.ControlController
import com.grnt.bababrowser001.control.ControlInteractor
import com.grnt.bababrowser001.control.ControlView
import com.grnt.bababrowser001.control.DefaultSessionControlController
import com.grnt.bababrowser001.ext.components
import com.grnt.bababrowser001.ext.requireComponents

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var btnBrowser: ImageButton
    private lateinit var btnTabTray: ImageButton
    lateinit var controlRV : RecyclerView
    var navigation: NavController? = null


    private var _controlInteractor: ControlInteractor? = null
    private val controlInteractor: ControlInteractor
        get() = _controlInteractor!!

    private var controlView: ControlView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val activity = activity as MainActivity
        var view =  inflater.inflate(R.layout.fragment_home, container, false)

        controlRV = view.findViewById(R.id.controllerRecyclerView)
        _controlInteractor = ControlInteractor(
            controller = DefaultSessionControlController(
                activity = activity,
                engine = requireComponents.core.engine,
                addTabUseCase = requireComponents.useCases.tabsUseCases.addTab
            )
        )
        controlView = ControlView(
            containerView = controlRV,
            viewLifecycleOwner = viewLifecycleOwner,
            interactor = controlInteractor,
        )

        updateSessionControlView()

        btnBrowser = view.findViewById(R.id.btnBrowser)
        btnBrowser.setOnClickListener(View.OnClickListener {
           activity.openToBrowser()
        })
        btnTabTray = view.findViewById(R.id.btnTabTray)
        btnTabTray.setOnClickListener(View.OnClickListener {
            activity.openToTabTray()
        })
        return view
    }

    private fun updateSessionControlView() {
        controlView?.update(requireContext().components.appStore.state)
    }
}