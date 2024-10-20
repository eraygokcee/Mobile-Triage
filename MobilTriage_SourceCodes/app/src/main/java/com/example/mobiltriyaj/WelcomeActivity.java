package com.example.mobiltriyaj;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class WelcomeActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Locale currentLocale = getCurrentLocale();
        imageView = findViewById(R.id.splash_logo);
        if (currentLocale.getLanguage().equals(new Locale("tr").getLanguage())) {
            imageView.setImageResource(R.drawable.mobil_triyaj_tr);

        } else {
            imageView.setImageResource(R.drawable.mobil_triyaj_en);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000); // 3 saniye gecikme
    }

    private Locale getCurrentLocale() {
        Configuration config = getResources().getConfiguration();
        return config.getLocales().get(0);
    }
}
