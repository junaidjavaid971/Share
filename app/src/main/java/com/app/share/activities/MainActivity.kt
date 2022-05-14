package com.app.share.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.app.share.R
import com.app.share.fragments.*
import com.app.share.fragments.HomeFragment.HomePageButtonsClickListener
import com.app.share.fragments.TradingFragment.TradingFragmentButtonCallbacks
import com.app.share.fragments.homepageFragments.*
import com.app.share.fragments.homepageFragments.button1Fragments.*
import com.app.share.fragments.tradingFragments.TradingFragment1
import com.app.share.fragments.tradingFragments.TradingFragment2
import com.app.share.interfaces.Button1FragmentCallbacks
import com.app.share.interfaces.HomeFragmentCallback
import com.app.share.interfaces.TradingFragmentBackPress
import com.app.share.utils.Utils
import com.app.share.utils.Utils.isNetworkAvailable
import com.app.share.utils.Utils.showAlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    TradingFragmentButtonCallbacks, HomePageButtonsClickListener, Utils.DialogButtonsCallback,
    Button1FragmentCallbacks, HomeFragmentCallback, TradingFragmentBackPress {
    var navigationView: BottomNavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var ivMenu: ImageView? = null
    var tvAbout: TextView? = null
    var tvTerm: TextView? = null
    var tvPolicy: TextView? = null
    var toolbar: ConstraintLayout? = null

    //BottomBarFragments
    var tvFragment: TvFragment? = null
    var learnFragment: LearnFragment? = null
    var homeFragment: HomeFragment? = null
    var newsFragment: NewsFragment? = null
    var tradingFragment: TradingFragment? = null
    var tradingFragment1: TradingFragment1? = null
    var tradingFragment2: TradingFragment2? = null

    //HomeFragments
    var allPatternFragment: AllPatternFragment? = null
    var banner2Activity: Banner2Activity? = null
    var bearCandleFragment: BearCandleFragment? = null
    var bearPatternFragment: BearPatternFragment? = null
    var bullCandleFragment: BullCandleFragment? = null
    var bullPatternFragment: BullPatternFragment? = null
    var optionChainFragment: OptionChainFragment? = null

    var upstoxApplyFragment: UpstoxApplyFragment? = null
    var upstoxHtaFragment: UpstoxHtaFragment? = null
    var zerodhaHtaFragment: ZerodhaHtaFragment? = null
    var zerodhaApplyFragment: ZerodhaApplyFragment? = null
    var wazirxHtaFragment: WazirxHtaFragment? = null
    var wazirxApplyFragment: WazirxApplyFragment? = null

    var builder: AlertDialog.Builder? = null
    var currentFragment: Fragment? = null
    var currentHomeFragment: Fragment? = null
    var currentTradingFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navView.itemIconTintList = null
        initViews()
        builder = AlertDialog.Builder(this)

        addFragments()

        navigationView!!.setOnNavigationItemSelectedListener(this)
        navigationView!!.selectedItemId = R.id.mhome
        performNavActions()
        ivMenu!!.setOnClickListener { v: View? -> drawerLayout!!.openDrawer(Gravity.LEFT) }
    }

    private fun addFragments() {
        tvFragment = TvFragment()
        learnFragment = LearnFragment()
        homeFragment = HomeFragment(this)
        newsFragment = NewsFragment()
        tradingFragment = TradingFragment(this)
        tradingFragment1 = TradingFragment1(this)
        tradingFragment2 = TradingFragment2(this)
        bullCandleFragment = BullCandleFragment(this)
        bearCandleFragment = BearCandleFragment(this)
        optionChainFragment = OptionChainFragment(this)
        bullPatternFragment = BullPatternFragment(this)
        bearPatternFragment = BearPatternFragment(this)
        allPatternFragment = AllPatternFragment(this)

        upstoxApplyFragment = UpstoxApplyFragment(this)
        upstoxHtaFragment = UpstoxHtaFragment(this)
        zerodhaApplyFragment = ZerodhaApplyFragment(this)
        zerodhaHtaFragment = ZerodhaHtaFragment(this)
        wazirxApplyFragment = WazirxApplyFragment(this)
        wazirxHtaFragment = WazirxHtaFragment(this)

        val ft = supportFragmentManager.beginTransaction()

        ft.add(R.id.flLayout, tvFragment!!)
        ft.add(R.id.flLayout, learnFragment!!)
        ft.add(R.id.flLayout, homeFragment!!)
        ft.add(R.id.flLayout, newsFragment!!)
        ft.add(R.id.flLayout, tradingFragment!!)
        ft.add(R.id.flLayout, tradingFragment1!!)
        ft.add(R.id.flLayout, tradingFragment2!!)
        ft.add(R.id.flLayout, bullCandleFragment!!)
        ft.add(R.id.flLayout, bearCandleFragment!!)
        ft.add(R.id.flLayout, optionChainFragment!!)
        ft.add(R.id.flLayout, bullPatternFragment!!)
        ft.add(R.id.flLayout, bearPatternFragment!!)
        ft.add(R.id.flLayout, allPatternFragment!!)

        ft.add(R.id.flLayout, upstoxApplyFragment!!)
        ft.add(R.id.flLayout, upstoxHtaFragment!!)
        ft.add(R.id.flLayout, zerodhaApplyFragment!!)
        ft.add(R.id.flLayout, zerodhaHtaFragment!!)
        ft.add(R.id.flLayout, wazirxApplyFragment!!)
        ft.add(R.id.flLayout, wazirxHtaFragment!!)

        ft.commit()
    }

    private fun performNavActions() {
        tvAbout!!.setOnClickListener { view: View? ->
            startActivity(Intent(applicationContext, AboutActivity::class.java))
            drawerLayout!!.close()
        }
        tvTerm!!.setOnClickListener { view: View? ->
            startActivity(Intent(applicationContext, TermsAndConditionActivity::class.java))
            drawerLayout!!.close()
        }
        tvPolicy!!.setOnClickListener { view: View? ->
            startActivity(Intent(applicationContext, PrivacyPolicyActivity::class.java))
            drawerLayout!!.close()
        }
    }

    private fun replaceFragment(fragment: Fragment?) {
        val ft = supportFragmentManager.beginTransaction()
        ft.hide(tvFragment!!)
        ft.hide(learnFragment!!)
        ft.hide(homeFragment!!)
        ft.hide(newsFragment!!)
        ft.hide(tradingFragment!!)
        ft.hide(tradingFragment1!!)
        ft.hide(tradingFragment2!!)
        ft.hide(bullCandleFragment!!)
        ft.hide(bearCandleFragment!!)
        ft.hide(optionChainFragment!!)
        ft.hide(bullPatternFragment!!)
        ft.hide(bearPatternFragment!!)
        ft.hide(allPatternFragment!!)

        ft.hide(upstoxApplyFragment!!)
        ft.hide(upstoxHtaFragment!!)
        ft.hide(zerodhaApplyFragment!!)
        ft.hide(zerodhaHtaFragment!!)
        ft.hide(wazirxApplyFragment!!)
        ft.hide(wazirxHtaFragment!!)

        ft.show(fragment!!)
        ft.commit()
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer)
        ivMenu = findViewById(R.id.ivMenu)
        navigationView = findViewById(R.id.bottom_navigation)
        tvAbout = findViewById(R.id.tvAbout)
        tvPolicy = findViewById(R.id.tvPolicy)
        tvTerm = findViewById(R.id.tvTerms)
        toolbar = findViewById(R.id.mainToolbar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mhome -> {
                toolbar?.visibility = View.VISIBLE
                currentFragment = homeFragment
                if (currentHomeFragment is BullCandleFragment) {
                    currentHomeFragment = bullCandleFragment
                    replaceFragment(bullCandleFragment)
                } else if (currentHomeFragment is BearCandleFragment) {
                    currentHomeFragment = bearCandleFragment
                    replaceFragment(bearCandleFragment)
                } else if (currentHomeFragment is OptionChainFragment) {
                    currentHomeFragment = optionChainFragment
                    replaceFragment(optionChainFragment)
                } else if (currentHomeFragment is BullPatternFragment) {
                    currentHomeFragment = bullPatternFragment
                    replaceFragment(bullPatternFragment)
                } else if (currentHomeFragment is BearPatternFragment) {
                    currentHomeFragment = bearPatternFragment
                    replaceFragment(bearPatternFragment)
                } else if (currentHomeFragment is AllPatternFragment) {
                    currentHomeFragment = allPatternFragment
                    replaceFragment(allPatternFragment)
                } else {
                    currentHomeFragment = homeFragment
                    replaceFragment(homeFragment)
                }
                return true
            }
            R.id.mlearn -> {
                currentFragment = learnFragment
                replaceFragment(learnFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mtelevison -> {
                currentFragment = tvFragment
                replaceFragment(tvFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mtrading -> {
                currentFragment = tradingFragment
                if (currentTradingFragment is TradingFragment1) {
                    replaceFragment(tradingFragment1)
                } else if (currentTradingFragment is TradingFragment2) {
                    replaceFragment(tradingFragment2)
                } else {
                    currentTradingFragment = tradingFragment
                    replaceFragment(tradingFragment)
                }
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mnews -> {
                currentFragment = newsFragment
                replaceFragment(newsFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        if (currentFragment is TradingFragment && currentTradingFragment is TradingFragment) {
            showExitAlert()
            return
        } else if (currentFragment is TradingFragment) {
            currentFragment = tradingFragment
            replaceFragment(tradingFragment)
            return
        } else if (currentFragment is HomeFragment && currentHomeFragment is HomeFragment) {
            showExitAlert()
            return
        } else if (currentFragment is HomeFragment) {
            currentHomeFragment = homeFragment
            replaceFragment(homeFragment)
            return
        } else {
            showExitAlert()
        }
    }

    private fun showExitAlert() {
        builder!!.setMessage(R.string.dialog_message)
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id -> finish() }
            .setNegativeButton("No") { dialog, id -> dialog.cancel() }
        val alert = builder!!.create()
        alert.show()
    }

    override fun onBtn1Clicked() {
        showDisclaimerPopup(
            "Disclaimer",
            "We do not own demo Website (Virtual Trading Platform)",
            this
        )
    }

    override fun onBtn2Clicked() {
        currentTradingFragment = tradingFragment2
        replaceFragment(tradingFragment2)
        toolbar!!.visibility = View.GONE
    }

    override fun onHomePageButtonClicked(id: Int) {
        when (id) {
            1 -> {
                val intent = Intent(this@MainActivity, ZerodhaActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(this@MainActivity, UpstoxActivity::class.java)
                startActivity(intent)
            }
            3 -> {
                val intent = Intent(this@MainActivity, WazirxActivity::class.java)
                startActivity(intent)
            }
            4 -> {
                val intent = Intent(this@MainActivity, Banner2Activity::class.java)
                startActivity(intent)
            }
            5 -> {
                val intent = Intent(this@MainActivity, Link1Activity::class.java)
                startActivity(intent)
            }
            6 -> {
                val intent = Intent(this@MainActivity, Link2Activity::class.java)
                startActivity(intent)
            }
            7 -> {
                val intent = Intent(this@MainActivity, Link3Activity::class.java)
                startActivity(intent)
            }
            8 -> {
                replaceFragment(bullCandleFragment)
                toolbar!!.visibility = View.GONE
                currentHomeFragment = bullCandleFragment
            }
            9 -> {
                replaceFragment(bearCandleFragment)
                toolbar!!.visibility = View.GONE
                currentHomeFragment = bearCandleFragment
            }
            10 -> {
                replaceFragment(optionChainFragment)
                toolbar!!.visibility = View.GONE
                currentHomeFragment = optionChainFragment
            }
            11 -> {
                replaceFragment(bullPatternFragment)
                toolbar!!.visibility = View.GONE
                currentHomeFragment = bullPatternFragment
            }
            12 -> {
                replaceFragment(bearPatternFragment)
                toolbar!!.visibility = View.GONE
                currentHomeFragment = bearPatternFragment
            }
            13 -> {
                replaceFragment(allPatternFragment)
                toolbar!!.visibility = View.GONE
                currentHomeFragment = allPatternFragment
            }
            14 -> {
                val intent = Intent(this@MainActivity, Link4Activity::class.java)
                startActivity(intent)
            }
            15 -> {
                val intent = Intent(this@MainActivity, Link5Activity::class.java)
                startActivity(intent)
            }
            16 -> {
                val intent = Intent(this@MainActivity, Link6Activity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isNetworkAvailable(this)) {
            showAlertDialog(
                "Internet Not Connected",
                "Your device is not connected to the internet. This app needs stable internet connection to perform it's operations. Please try again!",
                this,
                this
            )
        }
    }

    override fun onDialogPositiveClick() {
        startActivity(
            Intent(
                this@MainActivity,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
        finish()
    }

    override fun onUpstoxApplyClicked() {
        currentHomeFragment = upstoxApplyFragment
        replaceFragment(upstoxApplyFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onUpstoxHTAClicked() {
        currentHomeFragment = upstoxHtaFragment
        replaceFragment(upstoxHtaFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onZerodhaApplyClicked() {
        currentHomeFragment = zerodhaApplyFragment
        replaceFragment(zerodhaApplyFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onZerodhaHtaClicked() {
        currentHomeFragment = zerodhaHtaFragment
        replaceFragment(zerodhaHtaFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onWazirxApplyClicked() {
        currentHomeFragment = wazirxApplyFragment
        replaceFragment(wazirxApplyFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onWazirxHtaClicked() {
        currentHomeFragment = wazirxHtaFragment
        replaceFragment(wazirxHtaFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onBackPressedClicked() {
        currentHomeFragment = homeFragment
        replaceFragment(homeFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onTradingFragmentBackPressed() {
        if (currentTradingFragment is TradingFragment1 || currentTradingFragment is TradingFragment2) {
            currentTradingFragment = tradingFragment
            replaceFragment(tradingFragment)
            toolbar!!.visibility = View.GONE
        } else if (currentTradingFragment is TradingFragment) {
            super.onBackPressed()
        }
    }

    private fun showDisclaimerPopup(
        title: String?,
        message: String?,
        context: Context?
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog: DialogInterface?, which: Int ->
                run {
                    dialog?.dismiss()
                    currentTradingFragment = tradingFragment1
                    replaceFragment(tradingFragment1)
                    toolbar!!.visibility = View.GONE
                }
            }
            .setNegativeButton(
                "Learn More"
            ) { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()
                startActivity(Intent(this@MainActivity, HelpActivity::class.java))
            }
            .setNeutralButton("Close") { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(false)
            .show()
    }

}