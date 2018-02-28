package com.android.navigationdraweractivity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.navigationdraweractivity.R;
import com.android.navigationdraweractivity.adapters.ViewPagerAdapter;


/**
 * Created by viswas on 1/26/2017.
 */
public class Career extends Fragment {
    Activity baseActivity;
    View mainView;
    TabLayout tabLayout;
    ViewPager viewPager;

    public Career() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseActivity = getActivity();
        mainView = inflater.inflate(R.layout.fragment_career, container, false);
        tabLayout =(TabLayout)mainView.findViewById(R.id.tabs);
        viewPager =(ViewPager) mainView.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        setViewPager(viewPager);
        return mainView;
    }
    private void setViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new FirstFrag(),"First");
        viewPagerAdapter.addFrag(new SecondFrag(),"second");
        viewPagerAdapter.addFrag(new ThirdFrag(),"Third");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
