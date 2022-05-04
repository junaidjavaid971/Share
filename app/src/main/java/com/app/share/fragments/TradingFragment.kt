package com.app.share.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.share.R

class TradingFragment : Fragment {
    var btn1: Button? = null
    var btn2: Button? = null
    var callback: TradingFragmentButtonCallbacks? = null

    constructor() {}
    constructor(callback: TradingFragmentButtonCallbacks?) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trading, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn1?.setOnClickListener { v: View? -> callback!!.onBtn1Clicked() }
        btn2?.setOnClickListener { v: View? -> callback!!.onBtn2Clicked() }
    }

    override fun onResume() {
        super.onResume()
    }

    interface TradingFragmentButtonCallbacks {
        fun onBtn1Clicked()
        fun onBtn2Clicked()
    }
}