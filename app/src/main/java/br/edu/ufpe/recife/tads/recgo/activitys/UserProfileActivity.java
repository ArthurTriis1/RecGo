package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ufpe.recife.tads.recgo.R;

public class UserProfileActivity extends AppCompatActivity {

    ImageView closeIcon;
    TextView inventoryText;
    ImageView logoutIcon;

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

        this.inventoryText = findViewById(R.id.user_profile_text_inventory);
        this.inventoryText.setOnClickListener(v -> {
            this.goTo(v, InventoryActivity.class);
        });

        this.logoutIcon = findViewById(R.id.user_profile_logout_icon);
        this.logoutIcon.setOnClickListener(v -> {
            this.goTo(v, SignInActivity.class);
        });
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }
}