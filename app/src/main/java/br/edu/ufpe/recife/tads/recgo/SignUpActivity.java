package br.edu.ufpe.recife.tads.recgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        defineViews();
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }

    private void defineViews(){
        loginButton = findViewById(R.id.sign_up_login_button);

        loginButton.setOnClickListener(l -> {
            goTo(null, SignInActivity.class);
        });

        registerButton = findViewById(R.id.sign_up_register_button);

        registerButton.setOnClickListener(l -> {
            goTo(null, SignInActivity.class);
        });
    }
}