package com.firatg.walpy.ui;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.firatg.walpy.R;
import com.firatg.walpy.Statics;
import com.firatg.walpy.model.Item;
import com.firatg.walpy.model.PhotosItem;
import com.firatg.walpy.viewmodel.FavoritiesViewModel;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.SET_WALLPAPER;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class WaalDetail  extends AppCompatActivity implements View.OnClickListener {
    PhotosItem waal;
    ImageView wallpaper;
    LinearLayout downloadBtn;
    LinearLayout setBtn;
    TextView photografgher;
    Toolbar toolbar;
    ImageView fav;
    Context context;
    public Item currentItem;
    public PhotosItem genelItem;
    public FavoritiesViewModel waalViewModel;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waal_detail);
        context=this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
   /*     Statics.actionbarcentertitle(this, getDrawable(R.drawable.ic_arrow_back), getString(R.string.app_name), Color.WHITE, true, false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        wallpaper = findViewById(R.id.imageView2);
        photografgher = findViewById(R.id.textView);
        downloadBtn = findViewById(R.id.btnDownload);
        setBtn = findViewById(R.id.btnSet);
        downloadBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);
        fav = findViewById(R.id.imageView8);
        fav.setOnClickListener(this);
        if (this.getIntent().getParcelableExtra("waal") != null) {
            waal = this.getIntent().getParcelableExtra("waal");
            loadWaal(waal);
        }

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });

    }

    public void loadWaal(PhotosItem waal) {
        genelItem = waal;
        Glide.with(this).load(waal.getSrc().getPortrait()).into(wallpaper);
        photografgher.setText(waal.getPhotographer());
        waalViewModel = new FavoritiesViewModel(getApplication(),waal.id);
        waalViewModel.kolp().observe(this, new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                if (item!=null){
                    currentItem = item;
                    if (item.isLike()){
                       fav.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_favorite_full));
                    }else {
                        fav.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_favorite));
                    }
                }else {
                    //Toast.makeText(WaalDetail.this, "bu yok", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    void DownloadImage(String ImageUrl, String name) {
        showToast("Downloading Image...");
        //Asynctask to create a thread to downlaod image in the background
        new DownloadsImage(WaalDetail.this).execute(ImageUrl, name);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDownload:
                fonksiyon();
                break;
            case R.id.btnSet:
                getBitmapFromURL(waal.getSrc().getPortrait());
                break;
            case R.id.imageView8:
                //ilk defa like olan patlıyor kontrol koy.
                if (currentItem!=null){
                    currentItem.setLike(!currentItem.isLike());
                    waalViewModel.update(currentItem);
                }else {
                    Item newitem = new Item(genelItem.id,genelItem.getSrc().portrait,true);
                    waalViewModel.insert(newitem);
                }
                break;
        }
    }

    void showToast(String msg) {
        Toast.makeText(WaalDetail.this, msg, Toast.LENGTH_SHORT).show();
    }


    public void fonksiyon() {
        if (checkPermission()) {
            //yapılcak sey
            DownloadImage(waal.getSrc().getPortrait(), "waal" + String.valueOf(waal.getId()));
        } else {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), SET_WALLPAPER);
        return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED&& result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(WaalDetail.this, new String[]{
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE,
                SET_WALLPAPER
        }, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hepsiokey = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                hepsiokey = false;
                break;
            }
        }
        if (hepsiokey) {
            fonksiyon();
        }
    }



    public void getBitmapFromURL(String path) {

        Glide.with(WaalDetail.this)
                .asBitmap()
                .load(path)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            WallpaperManager.getInstance(getApplicationContext()).setBitmap(resource);
                            Toast.makeText(WaalDetail.this, "Set wallpaper successfully!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

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
