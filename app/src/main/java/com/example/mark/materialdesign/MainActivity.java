package com.example.mark.materialdesign;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mark.materialdesign.fragments.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWidgets();
        setupDrawer();
    }


    private void setupWidgets(){
        mToolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

    }

    private void setupDrawer(){
        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.drawer_fragment);
        mDrawerFragment.setup( R.id.drawer_fragment,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Toast.makeText(this, "SETTINGS", Toast.LENGTH_LONG).show();
            return true;
            case R.id.next_activity:
                startActivity(new Intent(this, SubActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
