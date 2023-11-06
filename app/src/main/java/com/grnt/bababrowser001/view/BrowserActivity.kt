package com.grnt.bababrowser001.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.grnt.bababrowser001.R
import mozilla.components.browser.engine.gecko.GeckoEngine
import mozilla.components.browser.engine.gecko.GeckoEngineView
import mozilla.components.concept.engine.DefaultSettings
import mozilla.components.concept.engine.Engine

class BrowserActivity : AppCompatActivity() {
    private val engine:Engine by lazy {
        val defaultSettings = DefaultSettings()
        GeckoEngine(this,defaultSettings)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        if(name == GeckoEngineView::class.java.name){
            val engineView = engine.createView(this,attrs)
            val engineSession = engine.createSession()
            engineSession.loadUrl("https://www.mozilla.org")
            engineView.render(engineSession)
            return engineView.asView()
        }
        return super.onCreateView(parent, name, context, attrs)
    }
}