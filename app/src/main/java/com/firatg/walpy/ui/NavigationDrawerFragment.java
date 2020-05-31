package com.firatg.walpy.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.firatg.walpy.R;

public class NavigationDrawerFragment extends Fragment implements View.OnClickListener {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    LinearLayout menu_araba;
    LinearLayout menu_korona;
    LinearLayout menu_hayvan;
    LinearLayout menu_favorities;
    LinearLayout menu_ara;
    Context cc;

    public NavigationDrawerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vow = inflater.inflate(R.layout.navigation_drawer_fragment, container, false);
        cc = getActivity();
        menu_araba = vow.findViewById(R.id.l_car);
        menu_korona = vow.findViewById(R.id.l_corona);
        menu_hayvan = vow.findViewById(R.id.l_animals);
        menu_favorities = vow.findViewById(R.id.l_favorities);
        menu_ara = vow.findViewById(R.id.l_search);
        menu_araba.setOnClickListener(this);
        menu_korona.setOnClickListener(this);
        menu_hayvan.setOnClickListener(this);
        menu_favorities.setOnClickListener(this);
        menu_ara.setOnClickListener(this);

        return vow;

    }


    public void setUpNavigationDrawer(DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_car:
                setup("Car");
                break;
            case R.id.l_corona:
                setup("Corona");
                break;
            case R.id.l_animals:
                setup("Animals");
                break;
            case R.id.l_favorities:
                Intent intentfav = new Intent(getActivity(),FavoritiesActivity.class);
                startActivity(intentfav);
                break;
            case R.id.l_search:
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("section","Arama");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setup(String s){
        Intent intent = new Intent(getActivity(),CategoryActivity.class);
        intent.putExtra("section",s);
        startActivity(intent);
    }

}
