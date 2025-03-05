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


        window.decorView.windowInsetsController?.hide(
            WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
        )

        setContentView(R.layout.activity_main)

        myWebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()  // Open the web page in the current WebView

        // Set the custom User-Agent for Pixel Tablet with Chrome and latest WebKit
        val userAgent = "Mozilla/5.0 (iPad; CPU OS 16_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.4 Safari/605.1.15"
        myWebView.settings.userAgentString = userAgent

        // Enable JavaScript
        myWebView.settings.javaScriptEnabled = true
        // Enable media playback
        myWebView.settings.mediaPlaybackRequiresUserGesture = false // Allow autoplay of media

        // more options to get the speaker to play
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.setSupportMultipleWindows(false)

        // Disable Scroll bounce
        myWebView.overScrollMode = View.OVER_SCROLL_NEVER



        // Load the URL
        myWebView.loadUrl("https://beastacademy.com/school")
    }

    override fun onResume() {
        super.onResume()

        // Use WindowInsetsController to hide status and navigation bars (instead of systemUiVisibility)
        val windowInsetsController = window.insetsController
        windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    }
}