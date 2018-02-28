package com.android.navigationdraweractivity.helperClasses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.navigationdraweractivity.R;
import com.android.navigationdraweractivity.activities.HomeBase;

public class NavigationDrawer {
    private Activity baseActivity;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private static String CURRENT_TAG;

    private FragmentFactory fragmentFactory;
    private Handler mHandler;

    public NavigationDrawer(Activity activity) {
        baseActivity = activity;

        drawer = (DrawerLayout) baseActivity.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) baseActivity.findViewById(R.id.nav_view);
        toolbar = (Toolbar) baseActivity.findViewById(R.id.toolbar);

        fragmentFactory = new FragmentFactory(baseActivity);
        mHandler = new Handler();

        CURRENT_TAG = baseActivity.getString(R.string.nav_home);
        // initializing navigation menu
        setUpNavigationView();
    }

    public void initBegin() {
        CURRENT_TAG = baseActivity.getString(R.string.nav_home);
        loadHomeFragment();
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Drawable drawable;
                Intent intent;
                String url;
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_home);
                        break;
                    case R.id.nav_about_app:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_about_app);
                        break;
                    case R.id.nav_about_career:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_about_career);
                        break;
                    case R.id.nav_about_programs:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_about_programs);
                        break;
                    case R.id.nav_more:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_more);
                        break;
                    case R.id.nav_logout:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_logout);
                        break;
                    default:
                        CURRENT_TAG = baseActivity.getString(R.string.nav_home);
                        break;
                }
                loadHomeFragment();
                return true;
            }
        });

        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(baseActivity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        // change icon of Drawer Indicator
        Drawable drawable = ResourcesCompat.getDrawable(baseActivity.getResources(), R.drawable.menu, baseActivity.getTheme());
        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        // if setDrawerIndicatorEnabled is disabled, setup onClick Listener on navigation icon
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    public String findLastTagInStack(int indexFromLast) {
        int index = ((HomeBase) baseActivity).getSupportFragmentManager().getBackStackEntryCount() - indexFromLast;


        if (index < 0)
            return null;

        android.support.v4.app.FragmentManager.BackStackEntry backEntry = ((HomeBase) baseActivity).getSupportFragmentManager().getBackStackEntryAt(index);
        return backEntry.getName();
    }

    // Returns respected fragment that user selected from navigation menu
    public void loadHomeFragment() {
        CURRENT_TAG = NavigationDrawer.getCurrentTag();
        // set toolbar title
       /* ((HomeBase) baseActivity).getSupportActionBar().setTitle(
                baseActivity.getResources().getText(baseActivity.getResources().getIdentifier(CURRENT_TAG, "string", baseActivity.getPackageName()))
        );*/

        // if user select the current navigation menu again, don't do anything just close the navigation drawer
//        if (((HomeBase) baseActivity).getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//            drawer.closeDrawers();
//            return;
//        }

        if (CURRENT_TAG.equals(findLastTagInStack(1))) {
            drawer.closeDrawers();
            return;
        }

        //fragment stack should only contain home fragment(popping other fragments)
        ((HomeBase) baseActivity).getSupportFragmentManager().popBackStack(baseActivity.getString(R.string.nav_home), 0);


        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = fragmentFactory.getFragment(CURRENT_TAG);
                FragmentTransaction fragmentTransaction = ((HomeBase) baseActivity).getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.addToBackStack(CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        //If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        drawer.closeDrawers();

        // refresh toolbar menu
        baseActivity.invalidateOptionsMenu();
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public static String getCurrentTag() {
        return CURRENT_TAG;
    }

    public static void setCurrentTag(String currentTag) {
        CURRENT_TAG = currentTag;
    }
}

