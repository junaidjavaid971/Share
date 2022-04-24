package com.app.share.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.share.R;
import com.app.share.fragments.tradingFragments.TradingFragment1;
import com.app.share.fragments.tradingFragments.TradingFragment2;

public class TradingFragment extends Fragment  {
    View view;
    Button btn1, btn2;
    TradingFragment1 tradingFragment1;
    TradingFragment2 tradingFragment2;
    TradingFragmentButtonCallbacks callback;

    public TradingFragment() {
    }

    public TradingFragment(TradingFragmentButtonCallbacks callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_trading, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);

        btn1.setOnClickListener(v -> {
            callback.onBtn1Clicked();
        });

        btn2.setOnClickListener(v -> {
            callback.onBtn2Clicked();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public interface TradingFragmentButtonCallbacks {
        void onBtn1Clicked();

        void onBtn2Clicked();
    }
}