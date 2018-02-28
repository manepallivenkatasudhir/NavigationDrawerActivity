package com.android.navigationdraweractivity.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.navigationdraweractivity.R;
import com.android.navigationdraweractivity.activities.SplashScreen;


public class AboutApp extends Fragment {

    Activity baseActivity;
    View mainView;
    public AboutApp() {
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
        Button next = (Button)mainView.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(baseActivity, SplashScreen.class);
                baseActivity.startActivity(intent);
            }
        });
        return mainView;
    }

}
