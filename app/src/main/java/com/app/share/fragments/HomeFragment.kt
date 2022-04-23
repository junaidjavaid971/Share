package com.app.share.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.app.share.R
import com.app.share.utils.SliderItem
import com.app.share.adpaters.SliderAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.share.databinding.FragmentHomeBinding
import java.util.ArrayList

class HomeFragment(var callback: HomePageButtonsClickListener) : Fragment() {
    private val sliderHandler = Handler()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewpager()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.zerodhabtn.setOnClickListener {
            callback.onHomePageButtonClicked(1)
        }
        binding.upstoxbtn.setOnClickListener {
            callback.onHomePageButtonClicked(2)
        }
        binding.wazirxbtn.setOnClickListener {
            callback.onHomePageButtonClicked(3)
        }
        binding.telegrambtn2.setOnClickListener {
            callback.onHomePageButtonClicked(4)
        }
        binding.link1.setOnClickListener {
            callback.onHomePageButtonClicked(5)
        }
        binding.link2.setOnClickListener {
            callback.onHomePageButtonClicked(6)
        }
        binding.link3.setOnClickListener {
            callback.onHomePageButtonClicked(7)
        }
        binding.bullcandle.setOnClickListener {
            callback.onHomePageButtonClicked(8)
        }
        binding.bearcandle.setOnClickListener {
            callback.onHomePageButtonClicked(9)
        }
        binding.bullcandle2.setOnClickListener {
            callback.onHomePageButtonClicked(10)
        }
        binding.bullpattern.setOnClickListener {
            callback.onHomePageButtonClicked(11)
        }
        binding.bullpattern2.setOnClickListener {
            callback.onHomePageButtonClicked(12)
        }
        binding.bearpattern.setOnClickListener {
            callback.onHomePageButtonClicked(13)
        }
        binding.link4.setOnClickListener {
            callback.onHomePageButtonClicked(14)
        }
        binding.link5.setOnClickListener {
            callback.onHomePageButtonClicked(15)
        }
        binding.link6.setOnClickListener {
            callback.onHomePageButtonClicked(16)
        }
    }

    private fun initViewpager() {
        ///////////////////////////// Start ViewPager Code  ////////////////////////////
        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        sliderItems.add(SliderItem(R.drawable.demobanner))
        binding.viewPagerImageSlider.adapter =
            SliderAdapter(
                sliderItems,
                binding.viewPagerImageSlider
            )
        binding.viewPagerImageSlider.clipToPadding = false
        binding.viewPagerImageSlider.clipChildren = false
        binding.viewPagerImageSlider.offscreenPageLimit = 3
        binding.viewPagerImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(0))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.8f + r * 0.15f
        }
        binding.viewPagerImageSlider.setPageTransformer(compositePageTransformer)
        binding.viewPagerImageSlider.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })

        ///////////////////////////// End ViewPager Code  ////////////////////////////
    }

    ///////// View Pager ////////
    private val sliderRunnable =
        Runnable {
            binding.viewPagerImageSlider.currentItem =
                binding.viewPagerImageSlider.currentItem + 1
        }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    ///////// View Pager /////////
    private fun gotoUrl(s: String) {
        val uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    interface HomePageButtonsClickListener {
        fun onHomePageButtonClicked(id: Int)
    }
}