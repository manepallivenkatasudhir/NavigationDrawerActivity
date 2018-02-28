package com.android.navigationdraweractivity.helperClasses;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.android.navigationdraweractivity.R;
import com.android.navigationdraweractivity.activities.CirclePageIndicator;
import com.android.navigationdraweractivity.fragments.AboutApp;
import com.android.navigationdraweractivity.fragments.Career;
import com.android.navigationdraweractivity.fragments.Home;
import com.android.navigationdraweractivity.fragments.More;
import com.android.navigationdraweractivity.fragments.Programs;

public class FragmentFactory {
    private Activity baseActivity;

    public FragmentFactory(Activity activity) {
        baseActivity = activity;
    }

    public Fragment getFragment(String CURRENT_TAG) {
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_home))) {
            return new CirclePageIndicator();
        }
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_about_app))) {
            return new AboutApp();
        }
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_about_career))) {
            return new Career();
        }
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_about_programs))) {
            return new Programs();
        }
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_more))) {
            return  new More();
        }
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_logout))) {
            baseActivity.finish();
        }

        return new Home();
    }
}

