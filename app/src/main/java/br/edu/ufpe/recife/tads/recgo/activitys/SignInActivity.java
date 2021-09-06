package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.AuthService;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignInDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    EditText inputEmail;
    EditText inputPassowrd;
    AuthService authService;

    UserService userService;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authService = new RecGoApi().getAuthService();
        userService = UserService.getInstance();

        setContentView(R.layout.activity_sign_in);
        defineViews();
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }

    private void defineViews(){
        loginButton = findViewById(R.id.sign_in_login_button);

        loginButton.setOnClickListener(l -> {this.login();});

        registerButton = findViewById(R.id.sign_in_register_button);

        registerButton.setOnClickListener(l -> {
            goTo(null, SignUpActivity.class);
        });

        inputEmail = findViewById(R.id.sign_in_email);
        inputPassowrd = findViewById(R.id.sign_in_password);
    }

    private void login(){

        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setIdentifier(inputEmail.getText().toString());
        signInDTO.setPassword(inputPassowrd.getText().toString());

        Call<SignResponseDTO> signResponseDTOCall = authService.signIn(signInDTO);

        signResponseDTOCall.enqueue(new Callback<SignResponseDTO>() {
            @Override
            public void onResponse(Call<SignResponseDTO> call, Response<SignResponseDTO> response) {
                SignResponseDTO signResponseDTO = response.body();
                if(signResponseDTO != null){
                    userService.setUserData(signResponseDTO);
                    goTo(null, MapsActivity.class);
                } else {
                    Toast.makeText(ctx, "Erro na requisição, verifique os dados cadatrais", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SignResponseDTO> call, Throwable t) {
                Toast.makeText(ctx, "Erro na requisição, verifique login e senha", Toast.LENGTH_LONG).show();
            }
        });

//        new BaseAsyncTask<>(() -> {
//            try {
//                Response<SignResponseDTO> signResponseDTOResponse = signResponseDTOCall.execute();
//                return signResponseDTOResponse.body();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }, signResponseDTO -> {
//            if(signResponseDTO != null){
//                Toast.makeText(this, signResponseDTO.getJwt(), Toast.LENGTH_LONG).show();
//                goTo(null, MapsActivity.class);
//                return;
//            }
//
//            Toast.makeText(this, "Erro na requisição, verifique login e senha", Toast.LENGTH_LONG).show();
//        }).execute();
    }

}