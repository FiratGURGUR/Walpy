package com.firatg.walpy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firatg.walpy.R;
import com.firatg.walpy.adapter.OnItemClickListener;
import com.firatg.walpy.adapter.WaalAdapter;
import com.firatg.walpy.model.PhotosItem;
import com.firatg.walpy.viewmodel.CategoryViewModel;
import com.firatg.walpy.viewmodel.SearchViewModel;

public class CategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbar_title;
    private CategoryViewModel waalViewModel;
    private WaalAdapter adapter;
    private RecyclerView recyclerview;
    GridLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();

        toolbar = findViewById(R.id.toolbar_cat);
        toolbar_title = findViewById(R.id.toolbar_cat_title);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle extras = getIntent().getExtras();
        final String section = extras.getString("section");
        toolbar_title.setText(section);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        recyclerview = findViewById(R.id.rc_category);
        adapter = new WaalAdapter();
        manager  = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(manager);
        recyclerview.setHasFixedSize(true);

        loadList(section);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, PhotosItem waal, int pos) {

                ImageView imageStart = (ImageView) v.findViewById(R.id.imageView);
                Intent intent = new Intent(CategoryActivity.this,WaalDetail.class);
                String transitionName = "imageTransition";
                ViewCompat.setTransitionName(imageStart, transitionName);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(CategoryActivity.this, imageStart, transitionName);
                intent.putExtra("waal" , waal);
                startActivity(intent,options.toBundle());
            }

        });
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

    public void loadList(CharSequence s){
        waalViewModel = ViewModelProviders.of(CategoryActivity.this).get(CategoryViewModel.class);
        waalViewModel.init(String.valueOf(s));
        waalViewModel.userPagedList.observe(CategoryActivity.this, new Observer<PagedList<PhotosItem>>() {
            @Override
            public void onChanged(PagedList<PhotosItem> waals) {
                adapter.submitList(waals);
                adapter.notifyDataSetChanged();
                recyclerview.setAdapter(adapter);
            }
        });
    }

}
