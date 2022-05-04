package com.app.share.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.webkit.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.share.R;
import com.app.share.fragments.learnfragments.CandleFragment;
import com.app.share.fragments.learnfragments.IndicatorFragment;
import com.app.share.fragments.learnfragments.PatternFragment;
import com.app.share.fragments.learnfragments.StrategyFragment;
import com.app.share.fragments.learnfragments.TutorialFragment;

public class LearnFragment extends Fragment {
    TextView candle, pattern, indicator, strategy, tutorial;
    View view;

    //LearnFragments
    CandleFragment candleFragment;
    IndicatorFragment indicatorFragment;
    PatternFragment patternFragment;
    StrategyFragment strategyFragment;
    TutorialFragment tutorialFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_learn, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        intializeLearnFragments();

        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        ft2.add(R.id.framelayout, candleFragment);
        ft2.add(R.id.framelayout, patternFragment);
        ft2.add(R.id.framelayout, indicatorFragment);
        ft2.add(R.id.framelayout, strategyFragment);
        ft2.add(R.id.framelayout, tutorialFragment);

        ft2.hide(patternFragment);
        ft2.hide(indicatorFragment);
        ft2.hide(strategyFragment);
        ft2.hide(tutorialFragment);
        ft2.commit();

        performActions();
    }


    private void replaceInnerFragment(Fragment fragment) {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        ft2.hide(candleFragment);
        ft2.hide(patternFragment);
        ft2.hide(indicatorFragment);
        ft2.hide(strategyFragment);
        ft2.hide(tutorialFragment);

        ft2.show(fragment);
        ft2.commit();
    }

    private void intializeLearnFragments() {
        candleFragment = new CandleFragment();
        indicatorFragment = new IndicatorFragment();
        patternFragment = new PatternFragment();
        strategyFragment = new StrategyFragment();
        tutorialFragment = new TutorialFragment();
    }

    private void performActions() {
        Drawable btnDrawable = candle.getBackground();
        btnDrawable = DrawableCompat.wrap(btnDrawable);
        DrawableCompat.setTint(btnDrawable, Color.RED);
        candle.setBackground(btnDrawable);
        replaceInnerFragment(candleFragment);

        candle.setOnClickListener(view -> {
            Drawable buttonDrawable = candle.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.RED);
            candle.setBackground(buttonDrawable);
            replaceInnerFragment(candleFragment);

            Drawable indicatorBtn = indicator.getBackground();
            indicatorBtn = DrawableCompat.wrap(indicatorBtn);
            DrawableCompat.setTint(indicatorBtn, getResources().getColor(R.color.light_gray));
            indicator.setBackground(indicatorBtn);

            Drawable patterBtn = pattern.getBackground();
            patterBtn = DrawableCompat.wrap(patterBtn);
            DrawableCompat.setTint(patterBtn, getResources().getColor(R.color.light_gray));
            pattern.setBackground(patterBtn);

            Drawable strategyBtn = strategy.getBackground();
            strategyBtn = DrawableCompat.wrap(strategyBtn);
            DrawableCompat.setTint(strategyBtn, getResources().getColor(R.color.light_gray));
            strategy.setBackground(strategyBtn);

            Drawable tutorialBtn = tutorial.getBackground();
            tutorialBtn = DrawableCompat.wrap(tutorialBtn);
            DrawableCompat.setTint(tutorialBtn, getResources().getColor(R.color.light_gray));
            tutorial.setBackground(tutorialBtn);

        });

        pattern.setOnClickListener(view -> {
            Drawable buttonDrawable = pattern.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.GREEN);
            pattern.setBackground(buttonDrawable);
            replaceInnerFragment(patternFragment);

            Drawable indicatorBtn = indicator.getBackground();
            indicatorBtn = DrawableCompat.wrap(indicatorBtn);
            DrawableCompat.setTint(indicatorBtn, getResources().getColor(R.color.light_gray));
            indicator.setBackground(indicatorBtn);

            Drawable candleBtn = candle.getBackground();
            candleBtn = DrawableCompat.wrap(candleBtn);
            DrawableCompat.setTint(candleBtn, getResources().getColor(R.color.light_gray));
            candle.setBackground(candleBtn);

            Drawable strategyBtn = strategy.getBackground();
            strategyBtn = DrawableCompat.wrap(strategyBtn);
            DrawableCompat.setTint(strategyBtn, getResources().getColor(R.color.light_gray));
            strategy.setBackground(strategyBtn);

            Drawable tutorialBtn = tutorial.getBackground();
            tutorialBtn = DrawableCompat.wrap(tutorialBtn);
            DrawableCompat.setTint(tutorialBtn, getResources().getColor(R.color.light_gray));
            tutorial.setBackground(tutorialBtn);

        });

        indicator.setOnClickListener(view -> {
            Drawable buttonDrawable = indicator.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.BLUE);
            indicator.setBackground(buttonDrawable);
            replaceInnerFragment(indicatorFragment);

            Drawable patternBtn = pattern.getBackground();
            patternBtn = DrawableCompat.wrap(patternBtn);
            DrawableCompat.setTint(patternBtn, getResources().getColor(R.color.light_gray));
            pattern.setBackground(patternBtn);

            Drawable candleBtn = candle.getBackground();
            candleBtn = DrawableCompat.wrap(candleBtn);
            DrawableCompat.setTint(candleBtn, getResources().getColor(R.color.light_gray));
            candle.setBackground(candleBtn);

            Drawable strategyBtn = strategy.getBackground();
            strategyBtn = DrawableCompat.wrap(strategyBtn);
            DrawableCompat.setTint(strategyBtn, getResources().getColor(R.color.light_gray));
            strategy.setBackground(strategyBtn);

            Drawable tutorialBtn = tutorial.getBackground();
            tutorialBtn = DrawableCompat.wrap(tutorialBtn);
            DrawableCompat.setTint(tutorialBtn, getResources().getColor(R.color.light_gray));
            tutorial.setBackground(tutorialBtn);
        });
        strategy.setOnClickListener(view -> {
            Drawable buttonDrawable = strategy.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.CYAN);
            strategy.setBackground(buttonDrawable);
            replaceInnerFragment(strategyFragment);

            Drawable indicatorBtn = indicator.getBackground();
            indicatorBtn = DrawableCompat.wrap(indicatorBtn);
            DrawableCompat.setTint(indicatorBtn, getResources().getColor(R.color.light_gray));
            indicator.setBackground(indicatorBtn);

            Drawable candleBtn = candle.getBackground();
            candleBtn = DrawableCompat.wrap(candleBtn);
            DrawableCompat.setTint(candleBtn, getResources().getColor(R.color.light_gray));
            candle.setBackground(candleBtn);

            Drawable patternBtn = pattern.getBackground();
            patternBtn = DrawableCompat.wrap(patternBtn);
            DrawableCompat.setTint(patternBtn, getResources().getColor(R.color.light_gray));
            pattern.setBackground(patternBtn);

            Drawable tutorialBtn = tutorial.getBackground();
            tutorialBtn = DrawableCompat.wrap(tutorialBtn);
            DrawableCompat.setTint(tutorialBtn, getResources().getColor(R.color.light_gray));
            tutorial.setBackground(tutorialBtn);

        });

        tutorial.setOnClickListener(view -> {
            Drawable buttonDrawable = tutorial.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.MAGENTA);
            tutorial.setBackground(buttonDrawable);
            replaceInnerFragment(tutorialFragment);

            Drawable indicatorBtn = indicator.getBackground();
            indicatorBtn = DrawableCompat.wrap(indicatorBtn);
            DrawableCompat.setTint(indicatorBtn, getResources().getColor(R.color.light_gray));
            indicator.setBackground(indicatorBtn);

            Drawable candleBtn = candle.getBackground();
            candleBtn = DrawableCompat.wrap(candleBtn);
            DrawableCompat.setTint(candleBtn, getResources().getColor(R.color.light_gray));
            candle.setBackground(candleBtn);

            Drawable strategyBtn = strategy.getBackground();
            strategyBtn = DrawableCompat.wrap(strategyBtn);
            DrawableCompat.setTint(strategyBtn, getResources().getColor(R.color.light_gray));
            strategy.setBackground(strategyBtn);

            Drawable patternBtn = pattern.getBackground();
            patternBtn = DrawableCompat.wrap(patternBtn);
            DrawableCompat.setTint(patternBtn, getResources().getColor(R.color.light_gray));
            pattern.setBackground(patternBtn);

        });
    }

    private void initViews() {
        candle = view.findViewById(R.id.tvCandle);
        pattern = view.findViewById(R.id.tvPattern);
        indicator = view.findViewById(R.id.tvIndicator);
        strategy = view.findViewById(R.id.tvStrategy);
        tutorial = view.findViewById(R.id.tvTutorials);
    }
}