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

class UpstoxActivity : AppCompatActivity(), Button1FragmentCallbacks {
    var webView: WebView? = null
    var ivBack: ImageView? = null
    var webURL: String? = "https://www.google.com/"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    var currentFragment: Fragment? = null

    lateinit var upstoxFragment: UpstoxFragment
    lateinit var upstoxApplyFragment: UpstoxApplyFragment
    lateinit var upstoxHtaFragment: UpstoxHtaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upstox)

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
        ft.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()

        ft.hide(upstoxFragment)
        ft.hide(upstoxApplyFragment)
        ft.hide(upstoxHtaFragment)

        ft.show(fragment)
        ft.commit()
    }

    override fun onBackPressed() {
        if (currentFragment is UpstoxApplyFragment || currentFragment is UpstoxHtaFragment) {
            replaceFragment(upstoxFragment)
        } else {
            super.onBackPressed()
        }
    }

    override fun onUpstoxApplyClicked() {
        currentFragment = upstoxApplyFragment
        replaceFragment(upstoxApplyFragment)
    }

    override fun onUpstoxHTAClicked() {
        currentFragment = upstoxHtaFragment
        replaceFragment(upstoxHtaFragment)
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