package com.example.ammarsaqib.docapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referencing main navigation view
        navMain = findViewById(R.id.nav_main);
        navMain.setNavigationItemSelectedListener(this);


        // loading home screen fragment
        load_home_frag();

        // setting drawer
        drawerLayout = findViewById(R.id.draw_main);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void load_home_frag()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //commit
        FragmentOne home = new FragmentOne();
        ft.replace(R.id.fragment_replaceable, home);

        // adding to back stack
        ft.addToBackStack("home");
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("navigation on click", "function called");
        Fragment fragment = null;
        Class fragmentClass;

        String stack = " ";

        switch(item.getItemId()) {

            case R.id.aboutus:

                fragmentClass = null;
                aboutus frag = new aboutus();
                frag.show(this.getSupportFragmentManager(),"DialogFragment" );
                stack = "about_us";

                // toast is showing up on the main activity
//                Toast.makeText(this, "testing",Toast.LENGTH_LONG).show();

                break;

            default:

                fragmentClass = FragmentOne.class;

        }


        if (fragmentClass!=null)
        {
            // execute the block if only there is some class assigned to the variable
            try {

                fragment = (Fragment) fragmentClass.newInstance();

            } catch (Exception e) {

                e.printStackTrace();

            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_replaceable, fragment);
            ft.addToBackStack(stack);
            ft.commit();
        }
        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);

        // Set action bar title
        setTitle(item.getTitle());

        // Close the navigation drawer
        drawerLayout.closeDrawers();

        return true;
    }
}