package com.firatg.walpy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.firatg.walpy.R;
import com.firatg.walpy.adapter.FavWaalAdapter;
import com.firatg.walpy.adapter.WaalAdapter;
import com.firatg.walpy.model.Item;
import com.firatg.walpy.model.PhotosItem;
import com.firatg.walpy.viewmodel.FavoritiesViewModel;
import com.firatg.walpy.viewmodel.WaalViewModel;

import java.util.List;

public class FavoritiesActivity extends AppCompatActivity {
    private FavoritiesViewModel wm;
    private FavWaalAdapter adapter;
    private RecyclerView recyclerview;
    GridLayoutManager manager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorities);
        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar_fav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setForegroundGravity(Gravity.CENTER_HORIZONTAL);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        recyclerview= findViewById(R.id.recyclerView2);
        adapter = new FavWaalAdapter();
        manager  = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(manager);
        recyclerview.setHasFixedSize(true);

        wm = ViewModelProviders.of(this).get(FavoritiesViewModel.class);
        wm.getAllNotes().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> photosItems) {
                adapter.submitList(photosItems);
            }
        });

        recyclerview.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
