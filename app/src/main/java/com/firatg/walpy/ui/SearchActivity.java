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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.firatg.walpy.R;
import com.firatg.walpy.adapter.OnItemClickListener;
import com.firatg.walpy.adapter.WaalAdapter;
import com.firatg.walpy.api.data.SearchDataSource;
import com.firatg.walpy.model.PhotosItem;
import com.firatg.walpy.viewmodel.SearchViewModel;
import com.firatg.walpy.viewmodel.WaalViewModel;

public class SearchActivity extends AppCompatActivity {
    EditText search;
    private SearchViewModel waalViewModel;
    private WaalAdapter adapter;
    private RecyclerView recyclerview;
    GridLayoutManager manager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = findViewById(R.id.editText);
        recyclerview = findViewById(R.id.rc_search);
        adapter = new WaalAdapter();
        manager  = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(manager);
        recyclerview.setHasFixedSize(true);

        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar_search);
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


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadList(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, PhotosItem waal, int pos) {

                ImageView imageStart = (ImageView) v.findViewById(R.id.imageView);
                Intent intent = new Intent(SearchActivity.this,WaalDetail.class);
                String transitionName = "imageTransition";
                ViewCompat.setTransitionName(imageStart, transitionName);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SearchActivity.this, imageStart, transitionName);
                intent.putExtra("waal" , waal);
                startActivity(intent,options.toBundle());
            }

        });
    }


    public void loadList(CharSequence s){
        waalViewModel = ViewModelProviders.of(SearchActivity.this).get(SearchViewModel.class);
        waalViewModel.init(String.valueOf(s));
        waalViewModel.userPagedList.observe(SearchActivity.this, new Observer<PagedList<PhotosItem>>() {
            @Override
            public void onChanged(PagedList<PhotosItem> waals) {
                adapter.submitList(waals);
                adapter.notifyDataSetChanged();
                recyclerview.setAdapter(adapter);
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
}
