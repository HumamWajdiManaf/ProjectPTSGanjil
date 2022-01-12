package com.example.ptsganjil202111rpl2humam14.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import com.example.ptsganjil202111rpl2humam14.Adapter.Mainadapter;
import com.example.ptsganjil202111rpl2humam14.R;

import com.example.ptsganjil202111rpl2humam14.model.model;
import com.example.ptsganjil202111rpl2humam14.ui.RealmHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Mainadapter mainadapter;
    RealmHelper realmHelper;
    Realm realm;
    private List<model> Model;
    RecyclerView recyclerView;
    final String API = "https://api.themoviedb.org/3/movie/popular";
    final String key = "a008cc3d5b543d48437262c1d83800ec";
    String judul, rating, deskripsi, gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        AndroidNetworking.initialize(getApplicationContext());
        getdata();
    }
    public void getdata(){
         Model = new ArrayList<>();
        AndroidNetworking.get(API)
                .addQueryParameter("api_key", key)
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", "yes");
                        try {
                            JSONArray resultArray = response.getJSONArray("results");
                            for (int i = 0; i < resultArray.length(); i++) {
                                JSONObject resultObj = resultArray.getJSONObject(i);
                                judul = resultObj.getString("title");
                                deskripsi = resultObj.getString("overview");
                                gambar = "https://image.tmdb.org/t/p/w500/".concat(resultObj.getString("poster_path"));
                                rating = resultObj.getString("vote_average");
                                Model.add(new model(i, false, gambar, judul, deskripsi, rating));
                                final RealmResults<model> model = realm.where(model.class).equalTo("gambar", gambar).findAll();
                                if (!model.isEmpty()) {
                                    Model.get(i).setFavorite(true);
                                }

                            }

                            mainadapter = new Mainadapter(Model, MainActivity.this, new Mainadapter.Callback() {
                                @Override
                                public void call(int v) {
                                    model Operator = Model.get(v);
                                    Intent move = new Intent(getApplicationContext(), detail.class);
                                    move.putExtra("deskripsi", Operator.getDeskripsi());
                                    move.putExtra("judul", Operator.getJudul());
                                    move.putExtra("vote", Operator.getRating());
                                    move.putExtra("gambar", Operator.getGambar());
                                    startActivity(move);
                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(mainadapter);


                        } catch (Exception e) {
                            Log.d("Error: ", e.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Something error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}