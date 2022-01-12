package com.example.ptsganjil202111rpl2humam14.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ptsganjil202111rpl2humam14.Adapter.Adapter;
import com.example.ptsganjil202111rpl2humam14.R;
import com.example.ptsganjil202111rpl2humam14.model.model;
import com.example.ptsganjil202111rpl2humam14.ui.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class favoriteActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<model> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().hide();
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        arrayList = new ArrayList<>();
        arrayList = realmHelper.getAllMovies();
        show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
        show();
    }
    public void show() {
        recyclerView = findViewById(R.id.rece);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(favoriteActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        adapter = new Adapter(arrayList, favoriteActivity.this, new Adapter.Callback() {
            @Override
            public void calling(int v) {
                model Operator = arrayList.get(v);
                Intent move = new Intent(getApplicationContext(), detail.class);
                move.putExtra("deskripsi", Operator.getDeskripsi());
                move.putExtra("judul", Operator.getJudul());
                move.putExtra("gambar", Operator.getGambar());
                startActivity(move);
            }
        });
        recyclerView.setAdapter(adapter);

    }
}