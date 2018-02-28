package com.android.navigationdraweractivity.activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.navigationdraweractivity.R;
import com.android.navigationdraweractivity.helperClasses.ActionBarMenu;
import com.android.navigationdraweractivity.helperClasses.FragmentFactory;
import com.android.navigationdraweractivity.helperClasses.NavigationDrawer;
import com.android.navigationdraweractivity.helperClasses.Permissions;
import com.android.navigationdraweractivity.interfaces.HomePageInterface;

/**
 * Created by Prathyusha on 11/25/2016.
 */

public class HomeBase extends AppCompatActivity implements HomePageInterface {
    public NavigationDrawer navigationDrawer;
    private ActionBarMenu actionBarMenu;
    private DrawerLayout drawer;
    private Handler mHandler;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        // This code loads home fragment when back key is pressed when user is in other fragment than home
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (!NavigationDrawer.getCurrentTag().equals(getString(R.string.nav_home))) {
            String lastTag = navigationDrawer.findLastTagInStack(2);
            NavigationDrawer.setCurrentTag(lastTag);

            getSupportActionBar().setTitle(lastTag);
            invalidateOptionsMenu();
            super.onBackPressed();
            return;
        }

     /*   if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.pressDoubleBack), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, GlobalConstants.doubleBackPressDelay);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitleTextColor(Color.WHITE);
       // toolbar.setTitleTextColor(getResources().getColor(R.color.navigation_drawer_title_color));
        setSupportActionBar(toolbar);

        navigationDrawer = new NavigationDrawer(this);
        actionBarMenu = new ActionBarMenu(this);
        drawer = navigationDrawer.getDrawer();
        mHandler = new Handler();

        if (savedInstanceState == null) {
            navigationDrawer.initBegin();
        }
        Permissions.getCallPermissions(HomeBase.this);
        Permissions.getCameraPermissons(HomeBase.this);
        Permissions.getStoragePermission(HomeBase.this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("currentTag", NavigationDrawer.getCurrentTag());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("currentTag")) {
                String currentTag = savedInstanceState.getString("currentTag");
                NavigationDrawer.setCurrentTag(currentTag);
                this.getSupportActionBar().setTitle(getResources().getText(getResources().getIdentifier(currentTag, "string", getPackageName())));
                invalidateOptionsMenu();
            }
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        actionBarMenu.onCreateMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks
        // on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        actionBarMenu.onOptionItemSelect(item);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reloadFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(NavigationDrawer.getCurrentTag());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(fragment);
        fragmentTransaction.attach(fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void loadNonDrawerFragment(final String currentTag, final Bundle bundle, String title) {
        NavigationDrawer.setCurrentTag(currentTag);
        getSupportActionBar().setTitle(title);
        final FragmentFactory fragmentFactory = new FragmentFactory(this);

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = fragmentFactory.getFragment(currentTag);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
                //fragmentTransaction.replace(R.id.frame, fragment, currentTag);
                fragmentTransaction.addToBackStack(currentTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        //If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        invalidateOptionsMenu();
    }
}

