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
import com.app.share.fragments.homepageFragments.button1Fragments.*
import com.app.share.interfaces.Button1FragmentCallbacks
import com.app.share.interfaces.HomeFragmentCallback

class ZerodhaActivity : AppCompatActivity(), Button1FragmentCallbacks {
    var webView: WebView? = null
    var ivBack: ImageView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    var currentFragment: Fragment? = null

    lateinit var zerodhaFragment: ZerodhaFragment
    lateinit var zerodhaHtaFragment: ZerodhaHtaFragment
    lateinit var zerodhaApplyFragment: ZerodhaApplyFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upstox)

        addFragments()
        replaceFragment(zerodhaFragment)
    }

    private fun addFragments() {
        zerodhaFragment = ZerodhaFragment(this)
        zerodhaHtaFragment = ZerodhaHtaFragment(this)
        zerodhaApplyFragment = ZerodhaApplyFragment(this)

        val ft = supportFragmentManager.beginTransaction()

        ft.add(R.id.flLayout, zerodhaFragment)
        ft.add(R.id.flLayout, zerodhaHtaFragment)
        ft.add(R.id.flLayout, zerodhaApplyFragment)
        ft.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()

        ft.hide(zerodhaFragment)
        ft.hide(zerodhaHtaFragment)
        ft.hide(zerodhaApplyFragment)

        ft.show(fragment)
        ft.commit()
    }

    override fun onBackPressed() {
        if (currentFragment is ZerodhaApplyFragment || currentFragment is ZerodhaHtaFragment) {
            replaceFragment(zerodhaFragment)
        } else {
            super.onBackPressed()
        }
    }

    override fun onUpstoxApplyClicked() {
    }

    override fun onUpstoxHTAClicked() {
    }

    override fun onZerodhaApplyClicked() {
        currentFragment = zerodhaApplyFragment
        replaceFragment(zerodhaApplyFragment)
    }

    override fun onZerodhaHtaClicked() {
        currentFragment = zerodhaHtaFragment
        replaceFragment(zerodhaHtaFragment)

    }

    override fun onWazirxApplyClicked() {

    }

    override fun onWazirxHtaClicked() {

    }
}