package xyz.shibomb.beast_academy

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {

    private lateinit var myWebView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // hide the navigation bar and the status bar
        window.decorView.windowInsetsController?.hide(
            WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
        )

        setContentView(R.layout.activity_main)

        // Open the web page in the current WebView
        myWebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()

        // Set the custom User-Agent for Pixel Tablet with Chrome and latest WebKit
        val userAgent = "Mozilla/5.0 (iPad; CPU OS 16_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.4 Safari/605.1.15"
        myWebView.settings.userAgentString = userAgent

        // Enable JavaScript
        myWebView.settings.javaScriptEnabled = true

        // Disable Scroll bounce
        myWebView.overScrollMode = View.OVER_SCROLL_NEVER

        // Load the URL
        myWebView.loadUrl("https://beastacademy.com/school")
    }
}