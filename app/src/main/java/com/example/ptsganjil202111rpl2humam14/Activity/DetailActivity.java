package com.example.ptsganjil202111rpl2humam14.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ptsganjil202111rpl2humam14.R;
import com.example.ptsganjil202111rpl2humam14.ui.RealmHelper;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {
    String judul, tanggal, deskripsi, gambar,  vote;
    Integer id;
    Boolean favorite;
    Bundle bundle;
    TextView tv_judul, tv_tanggalrilis, tv_deskripsi;
    ImageView img;
    RealmHelper realmHelper;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tv_judul= (TextView) findViewById(R.id.tv_judul);
        tv_tanggalrilis = (TextView) findViewById(R.id.tv_tanggal);
        img = (ImageView) findViewById(R.id.img_gambar);
        tv_deskripsi = (TextView) findViewById(R.id.tv_deskripsi);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            judul = bundle.getString("judul");
            deskripsi = bundle.getString("deskripsi");
            gambar = bundle.getString("gambar");
            tanggal = bundle.getString("tanggal");
            favorite = bundle.getBoolean("favorite");
            id = bundle.getInt("id");
            vote = bundle.getString("vote");
            tv_judul.setText(judul);
            tv_tanggalrilis.setText(tanggal);
            tv_deskripsi.setText(deskripsi);
            Picasso.get()
                    .load(gambar)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher_round)
                    .into(img);
        }

    }
}
