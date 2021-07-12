package br.edu.ufpe.recife.tads.recgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class LocationDetailActivity extends AppCompatActivity {

    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        setAllViews();

    }

    private void setAllViews() {
        backIcon = findViewById(R.id.location_detail_back_icon);
        backIcon.setOnClickListener(v -> {
            finish();
        });
    }
}