package com.app.share.fragments.homepageFragments

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
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.share.R
import com.app.share.fragments.homepageFragments.button1Fragments.UpstoxApplyFragment
import com.app.share.fragments.homepageFragments.button1Fragments.UpstoxFragment
import com.app.share.fragments.homepageFragments.button1Fragments.UpstoxHtaFragment
import com.app.share.interfaces.Button1FragmentCallbacks
import com.app.share.interfaces.HomeFragmentCallback

class UpstoxActivity : AppCompatActivity(), Button1FragmentCallbacks {
    var webView: WebView? = null
    var ivBack: ImageView? = null
    var webURL: String? = "https://www.google.com/"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    lateinit var upstoxFragment: UpstoxFragment
    lateinit var upstoxApplyFragment: UpstoxApplyFragment
    lateinit var upstoxHtaFragment: UpstoxHtaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_upstox)

        addFragments()
        replaceFragment(upstoxFragment)
    }

    private fun addFragments() {
        upstoxFragment = UpstoxFragment(this)
        upstoxHtaFragment = UpstoxHtaFragment(this)
        upstoxApplyFragment = UpstoxApplyFragment(this)

        val ft = supportFragmentManager.beginTransaction()

        ft.add(R.id.flLayout, upstoxFragment)
        ft.add(R.id.flLayout, upstoxApplyFragment)
        ft.add(R.id.flLayout, upstoxHtaFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()

        ft.hide(upstoxFragment)
        ft.hide(upstoxApplyFragment)
        ft.hide(upstoxHtaFragment)

        ft.show(fragment)
        ft.commit()
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

    override fun onUpstoxApplyClicked() {

    }

    override fun onUpstoxHTAClicked() {
    }

    override fun onZerodhaApplyClicked() {

    }

    override fun onZerodhaHtaClicked() {

    }

    override fun onWazirxApplyClicked() {

    }

    override fun onWazirxHtaClicked() {

    }
}