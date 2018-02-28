package com.android.navigationdraweractivity.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.navigationdraweractivity.R;
import com.android.navigationdraweractivity.adapters.SlidingImage_Adapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CirclePageIndicator extends Fragment {
    private static ViewPager mPager;
    View mainView;
    Activity baseActivity;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
     private static final Integer[] IMAGES= {R.drawable.nature, R.drawable.nature1, R.drawable.nature2,
             R.drawable.nature3,R.drawable.nature4,R.drawable.nature,R.drawable.nature1,R.drawable.nature2,
             R.drawable.nature3,R.drawable.nature4,R.drawable.nature};
private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    public CirclePageIndicator() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseActivity = getActivity();
        mainView = inflater.inflate(R.layout.activity_viewpager, container, false);
        init();
        return mainView;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        init();
    }*/
    private void init() {


        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) mainView.findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(baseActivity,ImagesArray));


        com.viewpagerindicator.CirclePageIndicator indicator = (com.viewpagerindicator.CirclePageIndicator)
               mainView.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);



        NUM_PAGES =IMAGES.length;



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


}
