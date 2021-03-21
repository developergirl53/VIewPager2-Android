package com.lntfusion.nowyouseeme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setLayoutOnboardingIndicators();

        setCurrentOnboardingIndicatory(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicatory(position );
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);

                }
                else{
                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                    finish();
                }
            }
        });



    }


    private void setupOnboardingItems(){

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemNever = new OnboardingItem();
        itemNever.setTitle("Never Mind IT");
        itemNever.setDescription("Please Process  the thought");
        itemNever.setImage(R.drawable.never);

        OnboardingItem itemRunningDream = new OnboardingItem();
        itemRunningDream.setTitle("Pay Online Please");
        itemRunningDream.setDescription("Please Process  the thought");
        itemRunningDream.setImage(R.drawable.running_dream);

        OnboardingItem itemPassion = new OnboardingItem();
        itemPassion.setTitle("Pay Online Please ");
        itemPassion.setDescription("Please Process  the thought");
        itemPassion.setImage(R.drawable.passion);

        onboardingItems.add(itemNever);
        onboardingItems.add(itemRunningDream);
        onboardingItems.add(itemPassion);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }


    private void setLayoutOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i =0;i<indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable
                    (getApplicationContext(),R.drawable.onboarding_indicator_inactive));

            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicatory(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i=0;i<childCount;i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount() - 1){
            buttonOnboardingAction.setText("Start");
        }else{
            buttonOnboardingAction.setText("Next");
        }
    }
}