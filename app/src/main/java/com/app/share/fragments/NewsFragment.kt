package com.app.share.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.widget.TextView
import com.app.share.fragments.newsfragments.MarketFragment
import com.app.share.fragments.newsfragments.StocksFragment
import com.app.share.fragments.newsfragments.CryptoFragment
import com.app.share.fragments.newsfragments.IPOFragment
import com.app.share.fragments.newsfragments.GlobalFragment
import android.os.Bundle
import com.app.share.R
import android.graphics.drawable.Drawable
import android.view.*
import android.webkit.*
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.share.activities.HelpActivity

class NewsFragment : Fragment() {
    var webView: WebView? = null
    var webURL: String? = "https://www.amazon.in/"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null
    var ivBack: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webview)
        swipeRefreshLayout = view.findViewById(R.id.swipe)
        swipeRefreshLayout?.setOnRefreshListener {
            webURL = webView?.url
            refreshWebView()
            swipeRefreshLayout?.isRefreshing = false
        }
        progressBar = view.findViewById(R.id.seekbar)
        webView = view.findViewById(R.id.webview)
        ivBack = view.findViewById(R.id.ivBack)
        ivBack?.setOnClickListener {
            if (webView?.canGoBack()!!) {
                webView?.goBack()
            } else {
                requireActivity().onBackPressed()
            }
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