package com.app.share.fragments.homepageFragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.share.R
import com.app.share.interfaces.HomeFragmentCallback

class Link6Activity : AppCompatActivity() {
    var webView: WebView? = null
    var ivBack: ImageView? = null
    var webURL: String? = "https://www.amazon.in/"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_link6)

        webView = findViewById(R.id.webview)
        swipeRefreshLayout = findViewById(R.id.swipe)
        ivBack = findViewById(R.id.ivBack)

        swipeRefreshLayout?.setOnRefreshListener {
            webURL = webView?.getUrl()
            refreshWebView()
            swipeRefreshLayout?.isRefreshing = false
        }

        ivBack?.setOnClickListener {
            if (webView?.canGoBack()!!) {
                webView?.goBack()
            } else {
                onBackPressed()
            }
        }

        progressBar = findViewById(R.id.seekbar)
        webView = findViewById(R.id.webview)
        setWebView()
    }

    private fun refreshWebView() {
        webView!!.webViewClient = webViewClient
        webView!!.loadUrl(webURL!!)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView?.webViewClient = webViewClient
        webView!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView!!.settings.javaScriptEnabled = true
        webView!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        webView!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView!!.settings.setAppCacheEnabled(true)
        webView!!.settings.domStorageEnabled = true
        webView!!.overScrollMode = WebView.OVER_SCROLL_NEVER
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)

        webView!!.webChromeClient = webChromeClient

        webView!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP && webView!!.canGoBack()) {
                webView!!.goBack()
                return@setOnKeyListener true
            }
            false
        }

        webView!!.loadUrl(webURL.toString())
    }

    val webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, progress: Int) {
            progressBar!!.progress = progress
            if (progress == 100) progressBar!!.visibility = View.GONE
        }
    }
    val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url.toString())
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar!!.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar!!.visibility = View.GONE
        }
    }
}