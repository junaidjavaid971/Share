package com.app.share.fragments.homepageFragments.button1Fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.share.R
import com.app.share.interfaces.Button1FragmentCallbacks

class WazirxApplyFragment(var callback: Button1FragmentCallbacks) : Fragment() {
    var webView: WebView? = null
    var webURL: String? =
        "https://best.aliexpress.com/?af=34745&dp=afffe11678d64c40be119f0121a72101&cn=102425&aff_fcid=8e306ae0430f4ae99987a6ba7d7e9312-1650759376484-04882-_pJQpbgG&aff_fsk=_pJQpbgG&aff_platform=api-new-link-generate&sk=_pJQpbgG&aff_trace_key=8e306ae0430f4ae99987a6ba7d7e9312-1650759376484-04882-_pJQpbgG&terminal_id=de3cf87b546c49d482ba4e397db9b9ac"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    lateinit var btnApply: Button
    lateinit var btnHowToApply: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wazirx_apply, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webview)
        swipeRefreshLayout = view.findViewById(R.id.swipe)
        swipeRefreshLayout?.setOnRefreshListener {
            webURL = webView?.getUrl()
            refreshWebView()
            swipeRefreshLayout?.isRefreshing = false
        }
        progressBar = view.findViewById(R.id.seekbar)
        webView = view.findViewById(R.id.webview)

        btnHowToApply = view.findViewById(R.id.btnHowToApply)

        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            if (webView?.canGoBack()!!) {
                webView?.goBack()
            } else {
                requireActivity().onBackPressed()
            }
        }

        setWebView()
        manageClicks()
    }

    private fun manageClicks() {
        btnHowToApply.setOnClickListener {
            callback.onWazirxHtaClicked()
        }
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