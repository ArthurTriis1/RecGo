package br.edu.ufpe.recife.tads.recgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class UserProfileActivity extends AppCompatActivity {

    ImageView closeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        defineViews();
    }

    private void defineViews(){
        this.closeIcon = findViewById(R.id.user_profile_close_icon);
        this.closeIcon.setOnClickListener(v -> {
            finish();
        });

    }
}