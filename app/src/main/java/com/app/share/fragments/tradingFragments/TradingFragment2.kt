package com.app.share.fragments.tradingFragments

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.widget.ProgressBar
import android.os.Bundle
import com.app.share.R
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.*
import android.webkit.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.app.share.interfaces.HomeFragmentCallback
import com.app.share.interfaces.TradingFragmentBackPress

class TradingFragment2(var homeCallback: TradingFragmentBackPress) : Fragment() {
    var webView: WebView? = null
    var webURL: String? = "https://www.amazon.in/"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    var ivBack: ImageView? = null
    var ivRefresh: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trading2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webview)
        swipeRefreshLayout = view.findViewById(R.id.swipe)
        progressBar = view.findViewById(R.id.seekbar)
        ivBack = view.findViewById(R.id.ivBack)
        ivRefresh = view.findViewById(R.id.ivRefresh)

        ivBack?.setOnClickListener {
            if (webView?.canGoBack()!!) {
                webView?.goBack()
            } else {
                homeCallback.onTradingFragmentBackPressed()
            }
        }

        ivRefresh?.setOnClickListener {
            webURL = "https://www.amazon.in/"
            refreshWebView()
        }

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