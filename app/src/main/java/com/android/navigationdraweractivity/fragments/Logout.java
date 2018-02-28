package com.android.navigationdraweractivity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.navigationdraweractivity.R;


/**
 * Created by viswas on 1/26/2017.
 */
public class Logout extends Fragment {
    Activity baseActivity;
    View mainView;
    public Logout() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseActivity = getActivity();
        mainView = inflater.inflate(R.layout.fragment_about_app, container, false);
        return mainView;
    }
}
