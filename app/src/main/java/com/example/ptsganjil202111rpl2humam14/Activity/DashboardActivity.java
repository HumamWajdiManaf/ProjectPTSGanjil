package com.example.ptsganjil202111rpl2humam14.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ptsganjil202111rpl2humam14.R;

public class DashboardActivity extends AppCompatActivity {
    ImageView Film,Favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Film = findViewById(R.id.videomaker);
        Favorite = findViewById(R.id.favorite2);
        Film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Mose = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(Mose);
            }
        });
        Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Move = new Intent(DashboardActivity.this, favoriteActivity.class);
                startActivity(Move);
            }
        });
    }
}