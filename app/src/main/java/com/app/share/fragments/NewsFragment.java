package com.app.share.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.share.R;
import com.app.share.fragments.newsfragments.CryptoFragment;
import com.app.share.fragments.newsfragments.GlobalFragment;
import com.app.share.fragments.newsfragments.IPOFragment;
import com.app.share.fragments.newsfragments.MarketFragment;
import com.app.share.fragments.newsfragments.StocksFragment;

public class NewsFragment extends Fragment {
    TextView markets, stocks, crypto, IPO, global;
    View view;
    private String webURL = "https://m.economictimes.com/";

    //NewsFragments
    MarketFragment marketFragment;
    StocksFragment stocksFragment;
    CryptoFragment cryptoFragment;
    IPOFragment ipoFragment;
    GlobalFragment globalFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        intializeNewsFragments();

        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        ft2.add(R.id.framelayout, marketFragment);
        ft2.add(R.id.framelayout, stocksFragment);
        ft2.add(R.id.framelayout, cryptoFragment);
        ft2.add(R.id.framelayout, ipoFragment);
        ft2.add(R.id.framelayout, globalFragment);

        ft2.hide(stocksFragment);
        ft2.hide(cryptoFragment);
        ft2.hide(ipoFragment);
        ft2.hide(globalFragment);
        ft2.commit();

        performActions();

    }

    private void replaceInnerFragment(Fragment fragment) {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        ft2.hide(marketFragment);
        ft2.hide(stocksFragment);
        ft2.hide(cryptoFragment);
        ft2.hide(ipoFragment);
        ft2.hide(globalFragment);

        ft2.show(fragment);
        ft2.commit();
    }

    private void intializeNewsFragments() {
        marketFragment = new MarketFragment();
        stocksFragment = new StocksFragment();
        cryptoFragment = new CryptoFragment();
        ipoFragment = new IPOFragment();
        globalFragment = new GlobalFragment();
    }

    private void performActions() {
        Drawable drawablebtn = markets.getBackground();
        drawablebtn = DrawableCompat.wrap(drawablebtn);
        DrawableCompat.setTint(drawablebtn, getResources().getColor(R.color.black));
        markets.setBackground(drawablebtn);

        markets.setOnClickListener(view -> {
            Drawable buttonDrawable = markets.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.green));
            replaceInnerFragment(marketFragment);

            Drawable stockBtn = stocks.getBackground();
            stockBtn = DrawableCompat.wrap(stockBtn);
            DrawableCompat.setTint(stockBtn, getResources().getColor(R.color.light_gray));
            stocks.setBackground(stockBtn);

            Drawable cryptoBtn = crypto.getBackground();
            cryptoBtn = DrawableCompat.wrap(cryptoBtn);
            DrawableCompat.setTint(cryptoBtn, getResources().getColor(R.color.light_gray));
            crypto.setBackground(cryptoBtn);

            Drawable ipoBtn = IPO.getBackground();
            ipoBtn = DrawableCompat.wrap(ipoBtn);
            DrawableCompat.setTint(ipoBtn, getResources().getColor(R.color.light_gray));
            IPO.setBackground(ipoBtn);

            Drawable globalBtn = global.getBackground();
            globalBtn = DrawableCompat.wrap(globalBtn);
            DrawableCompat.setTint(globalBtn, getResources().getColor(R.color.light_gray));
            global.setBackground(globalBtn);
        });

        stocks.setOnClickListener(view -> {
            Drawable buttonDrawable = stocks.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.green));
            stocks.setBackground(buttonDrawable);
            replaceInnerFragment(stocksFragment);

            Drawable marketBtn = markets.getBackground();
            marketBtn = DrawableCompat.wrap(marketBtn);
            DrawableCompat.setTint(marketBtn, getResources().getColor(R.color.light_gray));
            markets.setBackground(marketBtn);

            Drawable cryptoBtn = crypto.getBackground();
            cryptoBtn = DrawableCompat.wrap(cryptoBtn);
            DrawableCompat.setTint(cryptoBtn, getResources().getColor(R.color.light_gray));
            crypto.setBackground(cryptoBtn);

            Drawable ipoBtn = IPO.getBackground();
            ipoBtn = DrawableCompat.wrap(ipoBtn);
            DrawableCompat.setTint(ipoBtn, getResources().getColor(R.color.light_gray));
            IPO.setBackground(ipoBtn);

            Drawable globalBtn = global.getBackground();
            globalBtn = DrawableCompat.wrap(globalBtn);
            DrawableCompat.setTint(globalBtn, getResources().getColor(R.color.light_gray));
            global.setBackground(globalBtn);
        });

        crypto.setOnClickListener(view -> {
            Drawable buttonDrawable = crypto.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.green));
            crypto.setBackground(buttonDrawable);
            replaceInnerFragment(cryptoFragment);

            Drawable marketBtn = markets.getBackground();
            marketBtn = DrawableCompat.wrap(marketBtn);
            DrawableCompat.setTint(marketBtn, getResources().getColor(R.color.light_gray));
            markets.setBackground(marketBtn);

            Drawable stockBtn = stocks.getBackground();
            stockBtn = DrawableCompat.wrap(stockBtn);
            DrawableCompat.setTint(stockBtn, getResources().getColor(R.color.light_gray));
            stocks.setBackground(stockBtn);

            Drawable ipoBtn = IPO.getBackground();
            ipoBtn = DrawableCompat.wrap(ipoBtn);
            DrawableCompat.setTint(ipoBtn, getResources().getColor(R.color.light_gray));
            IPO.setBackground(ipoBtn);

            Drawable globalBtn = global.getBackground();
            globalBtn = DrawableCompat.wrap(globalBtn);
            DrawableCompat.setTint(globalBtn, getResources().getColor(R.color.light_gray));
            global.setBackground(globalBtn);

        });

        IPO.setOnClickListener(view -> {
            Drawable buttonDrawable = IPO.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.green));
            IPO.setBackground(buttonDrawable);
            replaceInnerFragment(ipoFragment);

            Drawable marketBtn = markets.getBackground();
            marketBtn = DrawableCompat.wrap(marketBtn);
            DrawableCompat.setTint(marketBtn, getResources().getColor(R.color.light_gray));
            markets.setBackground(marketBtn);

            Drawable stockBtn = stocks.getBackground();
            stockBtn = DrawableCompat.wrap(stockBtn);
            DrawableCompat.setTint(stockBtn, getResources().getColor(R.color.light_gray));
            stocks.setBackground(stockBtn);

            Drawable cryptoBtn = crypto.getBackground();
            cryptoBtn = DrawableCompat.wrap(cryptoBtn);
            DrawableCompat.setTint(cryptoBtn, getResources().getColor(R.color.light_gray));
            crypto.setBackground(cryptoBtn);

            Drawable globalBtn = global.getBackground();
            globalBtn = DrawableCompat.wrap(globalBtn);
            DrawableCompat.setTint(globalBtn, getResources().getColor(R.color.light_gray));
            global.setBackground(globalBtn);


        });

        global.setOnClickListener(view -> {
            Drawable buttonDrawable = global.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.green));
            global.setBackground(buttonDrawable);
            replaceInnerFragment(globalFragment);

            Drawable marketBtn = markets.getBackground();
            marketBtn = DrawableCompat.wrap(marketBtn);
            DrawableCompat.setTint(marketBtn, getResources().getColor(R.color.light_gray));
            markets.setBackground(marketBtn);

            Drawable stockBtn = stocks.getBackground();
            stockBtn = DrawableCompat.wrap(stockBtn);
            DrawableCompat.setTint(stockBtn, getResources().getColor(R.color.light_gray));
            stocks.setBackground(stockBtn);

            Drawable cryptoBtn = crypto.getBackground();
            cryptoBtn = DrawableCompat.wrap(cryptoBtn);
            DrawableCompat.setTint(cryptoBtn, getResources().getColor(R.color.light_gray));
            crypto.setBackground(cryptoBtn);

            Drawable ipoBtn = IPO.getBackground();
            ipoBtn = DrawableCompat.wrap(ipoBtn);
            DrawableCompat.setTint(ipoBtn, getResources().getColor(R.color.light_gray));
            IPO.setBackground(ipoBtn);

        });
    }

    private void initViews() {
        markets = view.findViewById(R.id.tvMarkets);
        stocks = view.findViewById(R.id.tvStocks);
        crypto = view.findViewById(R.id.tvCrypto);
        IPO = view.findViewById(R.id.tvIPO);
        global = view.findViewById(R.id.tvGlobal);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}