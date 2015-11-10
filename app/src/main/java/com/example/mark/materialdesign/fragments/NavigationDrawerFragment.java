package com.example.mark.materialdesign.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mark.materialdesign.R;
import com.example.mark.materialdesign.SharedPreferencesHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    private final String PREFERENCE_FILE = "NavigationDrawerFragment";
    private final String PREF_KEY_USER_KNOW_DRAWER = "userKnowDrawer";
    private ActionBarDrawerToggle mDrawerToogle; //drawer listener
    private DrawerLayout mDrawerLayout;
    private View mContainerView;
    private boolean mUserKnowDrawer;
    private boolean mFromSavedInstance;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserKnowDrawer = isUserAwareOfDrawer();

        if( null != savedInstanceState){
            mFromSavedInstance = true;
        }
    }

    private boolean isUserAwareOfDrawer(){
        return Boolean.valueOf(
                SharedPreferencesHelper.readFromPreference(
                        getActivity(), PREFERENCE_FILE, PREF_KEY_USER_KNOW_DRAWER, "false"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setup(int drawerFragmentId, DrawerLayout layout, Toolbar toolbar){
        mDrawerLayout = layout;
        mContainerView = getActivity().findViewById(drawerFragmentId);
        mDrawerToogle = getActionBarDrawerToggle(toolbar);
        mDrawerLayout.setDrawerListener(mDrawerToogle);

        //display "hamburguer" icon on top left corner of toolbar
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToogle.syncState();
            }
        });

        displayDrawerForFirstTime(mContainerView);
    }



    //display drawer if first time user uses app
    private void displayDrawerForFirstTime( View view){
        if(!mUserKnowDrawer && !mFromSavedInstance){
            mDrawerLayout.openDrawer( view );
        }
    }

    // get the drawer open close listener
    private ActionBarDrawerToggle getActionBarDrawerToggle(Toolbar toolbar){
        return new ActionBarDrawerToggle(
                getActivity(), mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if( !mUserKnowDrawer){
                    mUserKnowDrawer = true;
                    updateUserKnowOnPreference();
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
    }

    private void updateUserKnowOnPreference(){
        SharedPreferencesHelper.saveToSharePreferences(
                getActivity(), PREFERENCE_FILE, PREF_KEY_USER_KNOW_DRAWER,
                String.valueOf(true));
    }


}
