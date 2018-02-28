package com.android.navigationdraweractivity.helperClasses;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.navigationdraweractivity.R;

public class ActionBarMenu {
    NavigationDrawer navigationDrawer;
    Activity baseActivity;

    public ActionBarMenu(Activity activity) {
        baseActivity = activity;
        navigationDrawer = new NavigationDrawer(baseActivity);
    }

    // show menu for each fragment is selected
    public void onCreateMenu(Menu menu) {
        String CURRENT_TAG = NavigationDrawer.getCurrentTag();
        if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_home))) {
        } else if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_about_app))) {
        } else if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_about_career))) {
        } else if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_about_programs))) {
        } else if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_more))) {
        } else if (CURRENT_TAG.equals(baseActivity.getString(R.string.nav_logout))) {
        }
    }
    public void onOptionItemSelect(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            navigationDrawer.setCurrentTag(baseActivity.getString(R.string.nav_home));
            navigationDrawer.loadHomeFragment();
            return;
        }

    }
}

