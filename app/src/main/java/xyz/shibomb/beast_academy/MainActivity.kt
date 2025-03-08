package xyz.shibomb.beast_academy

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.webkit.WebView
import android.webkit.WebViewClient
// *** Import WebChromeClient to support full screen video.
import android.webkit.WebChromeClient
import android.widget.FrameLayout

class MainActivity : Activity() {

    private lateinit var myWebView: WebView

    // *** Variables added to support full screen video.
    private var mCustomView: View? = null
    private var mCustomViewCallback: WebChromeClient.CustomViewCallback? = null
    private var mOriginalOrientation: Int = 0
    private var mOriginalSystemUiVisibility: Int = 0

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
        // *** Full screen video
        myWebView.webChromeClient = MyChrome()


        // Set the custom User-Agent for Pixel Tablet with Chrome and latest WebKit
        val userAgent = "Mozilla/5.0 (iPad; CPU OS 16_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.4 Safari/605.1.15"
        myWebView.settings.userAgentString = userAgent

        // Enable JavaScript
        myWebView.settings.javaScriptEnabled = true

        // Disable Scroll bounce
        myWebView.overScrollMode = View.OVER_SCROLL_NEVER

        // Enable immersive mode, this gets rid of the line at the bottom of the app.
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )


        // Load the URL
        myWebView.loadUrl("https://beastacademy.com/school")
    }

    // *** Full screen video.
    inner class MyChrome : WebChromeClient() {
        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            if (mCustomView != null) {
                onHideCustomView()
                return
            }
            mCustomView = view
            mOriginalSystemUiVisibility = window.decorView.systemUiVisibility
            mOriginalOrientation = requestedOrientation
            mCustomViewCallback = callback
            (window.decorView as FrameLayout).addView(mCustomView, FrameLayout.LayoutParams(-1, -1))
            window.decorView.systemUiVisibility = 3846
        }

        override fun onHideCustomView() {
            (window.decorView as FrameLayout).removeView(mCustomView)
            mCustomView = null
            window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
            requestedOrientation = mOriginalOrientation
            mCustomViewCallback?.onCustomViewHidden()
            mCustomViewCallback = null
        }
    }
}