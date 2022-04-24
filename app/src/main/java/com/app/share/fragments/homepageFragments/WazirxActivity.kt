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

class WazirxActivity : AppCompatActivity(), Button1FragmentCallbacks {
    var webView: WebView? = null
    var ivBack: ImageView? = null
    var webURL: String? = "https://www.google.com/"
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null

    var currentFragment: Fragment? = null

    lateinit var wazirxFragment: WazirxFragment
    lateinit var wazirxApplyFragment: WazirxApplyFragment
    lateinit var wazirxHtaFragment: WazirxHtaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upstox)

        addFragments()
        replaceFragment(wazirxFragment)
    }

    private fun addFragments() {
        wazirxFragment = WazirxFragment(this)
        wazirxApplyFragment = WazirxApplyFragment(this)
        wazirxHtaFragment = WazirxHtaFragment(this)

        val ft = supportFragmentManager.beginTransaction()

        ft.add(R.id.flLayout, wazirxFragment)
        ft.add(R.id.flLayout, wazirxApplyFragment)
        ft.add(R.id.flLayout, wazirxHtaFragment)
        ft.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()

        ft.hide(wazirxFragment)
        ft.hide(wazirxApplyFragment)
        ft.hide(wazirxHtaFragment)

        ft.show(fragment)
        ft.commit()
    }

    override fun onBackPressed() {
        if (currentFragment is WazirxApplyFragment || currentFragment is WazirxHtaFragment) {
            replaceFragment(wazirxFragment)
        } else {
            super.onBackPressed()
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
        currentFragment = wazirxApplyFragment
        replaceFragment(wazirxApplyFragment)
    }

    override fun onWazirxHtaClicked() {
        currentFragment = wazirxHtaFragment
        replaceFragment(wazirxHtaFragment)
    }
}