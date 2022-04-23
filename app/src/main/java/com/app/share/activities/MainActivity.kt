package com.app.share.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.*
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
import com.app.share.utils.Utils
import com.app.share.utils.Utils.isNetworkAvailable
import com.app.share.utils.Utils.showAlertDialog
import com.app.share.fragments.*
import com.app.share.fragments.HomeFragment.HomePageButtonsClickListener
import com.app.share.fragments.TradingFragment.TradingFragmentButtonCallbacks
import com.app.share.fragments.homepageFragments.*
import com.app.share.fragments.homepageFragments.button1Fragments.*
import com.app.share.fragments.tradingFragments.TradingFragment1
import com.app.share.fragments.tradingFragments.TradingFragment2
import com.app.share.interfaces.Button1FragmentCallbacks
import com.app.share.interfaces.HomeFragmentCallback
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    TradingFragmentButtonCallbacks, HomePageButtonsClickListener, Utils.DialogButtonsCallback,
    Button1FragmentCallbacks, HomeFragmentCallback {
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
    var allPatternActivity: AllPatternActivity? = null
    var banner2Activity: Banner2Activity? = null
    var bearCandleActivity: BearCandleActivity? = null
    var bearPatternActivity: BearPatternActivity? = null
    var bullCandleActivity: BullCandleActivity? = null
    var bullPatternActivity: BullPatternActivity? = null
    var link1Activity: Link1Activity? = null
    var link2Activity: Link2Activity? = null
    var link3Activity: Link3Activity? = null
    var link4Activity: Link4Activity? = null
    var link5Activity: Link5Activity? = null
    var link6Activity: Link6Activity? = null
    var optionChainActivity: OptionChainActivity? = null

    //    var upstoxFragment: UpstoxFragment? = null
    var wazirxActivity: WazirxActivity? = null
    var zerodhaActivity: ZerodhaActivity? = null

    var upstoxApplyFragment: UpstoxApplyFragment? = null
    var upstoxHtaFragment: UpstoxHtaFragment? = null
    var zerodhaHtaFragment: ZerodhaHtaFragment? = null
    var zerodhaApplyFragment: ZerodhaApplyFragment? = null
    var wazirxHtaFragment: WazirxHtaFragment? = null
    var wazirxApplyFragment: WazirxApplyFragment? = null

    var builder: AlertDialog.Builder? = null
    var currentFragment: Fragment? = null
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
        tradingFragment1 = TradingFragment1()
        tradingFragment2 = TradingFragment2()

        //Home Fragments
        /*allPatternActivity = AllPatternActivity(this)
        banner2Activity = Banner2Activity(this)
        bearCandleActivity = BearCandleActivity(this)
        bearPatternActivity = BearPatternActivity(this)
        bullCandleActivity = BullCandleActivity(this)
        bullPatternActivity = BullPatternActivity(this)
        link1Activity = Link1Activity(this)
        link2Activity = Link2Activity(this)
        link3Activity = Link3Activity(this)
        link4Activity = Link4Activity(this)
        link5Activity = Link5Activity(this)
        link6Activity = Link6Activity(this)
        optionChainActivity = OptionChainActivity(this)
        upstoxFragment = UpstoxFragment(this, this)
        wazirxActivity = WazirxActivity(this, this)
        zerodhaActivity = ZerodhaActivity(this, this)*/

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

        /*ft.add(R.id.flLayout, allPatternActivity!!)
        ft.add(R.id.flLayout, banner2Activity!!)
        ft.add(R.id.flLayout, bearCandleActivity!!)
        ft.add(R.id.flLayout, bearPatternActivity!!)
        ft.add(R.id.flLayout, bullCandleActivity!!)
        ft.add(R.id.flLayout, bullPatternActivity!!)
        ft.add(R.id.flLayout, link1Activity!!)
        ft.add(R.id.flLayout, link2Activity!!)
        ft.add(R.id.flLayout, link3Activity!!)
        ft.add(R.id.flLayout, link4Activity!!)
        ft.add(R.id.flLayout, link5Activity!!)
        ft.add(R.id.flLayout, link6Activity!!)
        ft.add(R.id.flLayout, optionChainActivity!!)
        ft.add(R.id.flLayout, upstoxFragment!!)
        ft.add(R.id.flLayout, wazirxActivity!!)
        ft.add(R.id.flLayout, zerodhaActivity!!)*/

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
        /*ft.hide(allPatternActivity!!)
        ft.hide(banner2Activity!!)
        ft.hide(bearCandleActivity!!)
        ft.hide(bearPatternActivity!!)
        ft.hide(bullCandleActivity!!)
        ft.hide(bullPatternActivity!!)
        ft.hide(link1Activity!!)
        ft.hide(link2Activity!!)
        ft.hide(link3Activity!!)
        ft.hide(link4Activity!!)
        ft.hide(link5Activity!!)
        ft.hide(link6Activity!!)
        ft.hide(optionChainActivity!!)
        ft.hide(upstoxFragment!!)
        ft.hide(wazirxActivity!!)
        ft.hide(zerodhaActivity!!)*/

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
        toolbar = findViewById(R.id.toolbar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mhome -> {
                /*if (currentFragment is AllPatternActivity) {
                    replaceFragment(allPatternActivity)
                } else if (currentFragment is Banner2Activity) {
                    replaceFragment(banner2Activity)
                } else if (currentFragment is BearCandleActivity) {
                    replaceFragment(bearCandleActivity)
                } else if (currentFragment is BearPatternActivity) {
                    replaceFragment(bearPatternActivity)
                } else if (currentFragment is BullCandleActivity) {
                    replaceFragment(bullCandleActivity)
                } else if (currentFragment is BullPatternActivity) {
                    replaceFragment(bullPatternActivity)
                } else if (currentFragment is Link1Activity) {
                    replaceFragment(link1Activity)
                } else if (currentFragment is Link2Activity) {
                    replaceFragment(link2Activity)
                } else if (currentFragment is Link3Activity) {
                    replaceFragment(link3Activity)
                } else if (currentFragment is Link4Activity) {
                    replaceFragment(link4Activity)
                } else if (currentFragment is Link5Activity) {
                    replaceFragment(link5Activity)
                } else if (currentFragment is Link6Activity) {
                    replaceFragment(link6Activity)
                } else if (currentFragment is OptionChainActivity) {
                    replaceFragment(optionChainActivity)
                }*//* else if (currentFragment is UpstoxFragment) {
                    replaceFragment(upstoxFragment)
                }*//* else if (currentFragment is WazirxActivity) {
                    replaceFragment(wazirxActivity)
                } else if (currentFragment is ZerodhaActivity) {
                    replaceFragment(zerodhaActivity)
                } else if (currentFragment is ZerodhaHtaFragment) {
                    replaceFragment(zerodhaHtaFragment)
                } else if (currentFragment is ZerodhaApplyFragment) {
                    replaceFragment(zerodhaApplyFragment)
                } else if (currentFragment is WazirxApplyFragment) {
                    replaceFragment(wazirxApplyFragment)
                } else if (currentFragment is WazirxHtaFragment) {
                    replaceFragment(wazirxHtaFragment)
                } else if (currentFragment is UpstoxHtaFragment) {
                    replaceFragment(upstoxHtaFragment)
                } else if (currentFragment is UpstoxApplyFragment) {
                    replaceFragment(upstoxApplyFragment)
                } else {
                    replaceFragment(homeFragment)
                }*/

                replaceFragment(homeFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mlearn -> {
                replaceFragment(learnFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mtelevison -> {
                replaceFragment(tvFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mtrading -> {
                if (currentFragment is TradingFragment1) {
                    replaceFragment(tradingFragment1)
                } else if (currentFragment is TradingFragment2) {
                    replaceFragment(tradingFragment2)
                } else {
                    showDisclaimerPopup(
                        "Disclaimer",
                        "We do not own demo Website (Virtual Trading Platform)",
                        this
                    )
                }
                toolbar!!.visibility = View.GONE
                return true
            }
            R.id.mnews -> {
                replaceFragment(newsFragment)
                toolbar!!.visibility = View.GONE
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        if (currentFragment is TradingFragment1 || currentFragment is TradingFragment2) {
            replaceFragment(tradingFragment)
            currentFragment = tradingFragment
            return
        }/* else if (currentFragment is AllPatternActivity || currentFragment is Banner2Activity || currentFragment is BearCandleActivity
            || currentFragment is BearPatternActivity || currentFragment is BullCandleActivity || currentFragment is BullPatternActivity
            || currentFragment is Link1Activity || currentFragment is Link2Activity || currentFragment is Link3Activity
            || currentFragment is Link4Activity || currentFragment is Link5Activity || currentFragment is Link6Activity
            || currentFragment is OptionChainActivity /*|| currentFragment is UpstoxFragment*/ || currentFragment is WazirxActivity
            || currentFragment is ZerodhaActivity
        ) {
            replaceFragment(homeFragment)
            currentFragment = homeFragment
            return
        } else if (currentFragment is UpstoxApplyFragment || currentFragment is UpstoxHtaFragment) {
            replaceFragment(upstoxFragment)
            currentFragment = upstoxFragment
            return
        } else if (currentFragment is ZerodhaApplyFragment || currentFragment is ZerodhaHtaFragment) {
            replaceFragment(zerodhaActivity)
            currentFragment = zerodhaActivity
            return
        } else if (currentFragment is WazirxHtaFragment || currentFragment is WazirxApplyFragment) {
            replaceFragment(wazirxActivity)
            currentFragment = wazirxActivity
            return
        }*/
        showExitAlert()
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
        replaceFragment(tradingFragment1)
        toolbar!!.visibility = View.GONE
        currentFragment = tradingFragment1
    }

    override fun onBtn2Clicked() {
        replaceFragment(tradingFragment2)
        toolbar!!.visibility = View.GONE
        currentFragment = tradingFragment2
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
                val intent = Intent(this@MainActivity, BullCandleActivity::class.java)
                startActivity(intent)
            }
            9 -> {
                val intent = Intent(this@MainActivity, BearCandleActivity::class.java)
                startActivity(intent)
            }
            10 -> {
                val intent = Intent(this@MainActivity, OptionChainActivity::class.java)
                startActivity(intent)
            }
            11 -> {
                val intent = Intent(this@MainActivity, BullPatternActivity::class.java)
                startActivity(intent)
            }
            12 -> {
                val intent = Intent(this@MainActivity, BearPatternActivity::class.java)
                startActivity(intent)
            }
            13 -> {
                val intent = Intent(this@MainActivity, AllPatternActivity::class.java)
                startActivity(intent)
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
        currentFragment = upstoxApplyFragment
        replaceFragment(upstoxApplyFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onUpstoxHTAClicked() {
        currentFragment = upstoxHtaFragment
        replaceFragment(upstoxHtaFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onZerodhaApplyClicked() {
        currentFragment = zerodhaApplyFragment
        replaceFragment(zerodhaApplyFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onZerodhaHtaClicked() {
        currentFragment = zerodhaHtaFragment
        replaceFragment(zerodhaHtaFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onWazirxApplyClicked() {
        currentFragment = wazirxApplyFragment
        replaceFragment(wazirxApplyFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onWazirxHtaClicked() {
        currentFragment = wazirxHtaFragment
        replaceFragment(wazirxHtaFragment)
        toolbar!!.visibility = View.GONE
    }

    override fun onBackPressedClicked() {
        currentFragment = homeFragment
        replaceFragment(homeFragment)
        toolbar!!.visibility = View.GONE
    }

    fun showDisclaimerPopup(
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
                    replaceFragment(tradingFragment)
                }
            }
            .setNegativeButton(
                "Learn More"
            ) { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()
                startActivity(Intent(this@MainActivity, HelpActivity::class.java))
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(false)
            .show()
    }
}