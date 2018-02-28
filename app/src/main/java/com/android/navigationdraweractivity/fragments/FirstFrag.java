package com.android.navigationdraweractivity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.navigationdraweractivity.R;

/**
 * Created by manep on 8/21/2017.
 */

public class FirstFrag extends Fragment {
    Activity basecactivity;
    View mainview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       basecactivity = getActivity();
        mainview =inflater.inflate(R.layout.fragment_more,container,false);
        return mainview;

    }
}
