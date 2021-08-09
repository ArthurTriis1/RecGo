package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ufpe.recife.tads.recgo.R;

public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventário");
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }
}