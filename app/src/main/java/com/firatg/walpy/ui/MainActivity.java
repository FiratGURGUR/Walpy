package com.firatg.walpy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;


import com.firatg.walpy.R;
import com.firatg.walpy.Statics;
import com.firatg.walpy.adapter.OnItemClickListener;
import com.firatg.walpy.adapter.WaalAdapter;
import com.firatg.walpy.model.Item;
import com.firatg.walpy.model.PhotosItem;
import com.firatg.walpy.viewmodel.FavoritiesViewModel;
import com.firatg.walpy.viewmodel.WaalViewModel;

public class MainActivity extends AppCompatActivity {
    private WaalViewModel waalViewModel;
    private FavoritiesViewModel favViewModel;
    private WaalAdapter adapter;
    private RecyclerView recyclerview;
    GridLayoutManager manager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Statics.actionbarcentertitle(this, getDrawable(R.drawable.ic_arrow_back), getString(R.string.app_name), Color.WHITE, true, false);
        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        recyclerview= findViewById(R.id.recyclerView);
        adapter = new WaalAdapter();
        manager  = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(manager);
        recyclerview.setHasFixedSize(true);
        waalViewModel = ViewModelProviders.of(this).get(WaalViewModel.class);
        NavigationDrawerMenuYukle();
        favViewModel  =ViewModelProviders.of(this).get(FavoritiesViewModel.class);
        waalViewModel.userPagedList.observe(this, new Observer<PagedList<PhotosItem>>() {
            @Override
            public void onChanged(PagedList<PhotosItem> waals) {
                adapter.submitList(waals);
            }
        });

        recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, PhotosItem waal, int pos) {

                //favViewModel.insert(new Item(waal.id,waal.getSrc().portrait,true));
                ImageView imageStart = (ImageView) v.findViewById(R.id.imageView);
                Intent intent = new Intent(MainActivity.this,WaalDetail.class);
                String transitionName = "imageTransition";
                ViewCompat.setTransitionName(imageStart, transitionName);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, imageStart, transitionName);
                intent.putExtra("waal" , waal);
                startActivity(intent,options.toBundle());
            }

        });

    }
    //soldan acılan menüyü yükleyen fonksiyon
    public void NavigationDrawerMenuYukle() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        Fragment fragment = new NavigationDrawerFragment();
        ((NavigationDrawerFragment) fragment).setUpNavigationDrawer(drawerLayout, toolbar);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, fragment);
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



}
