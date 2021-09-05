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
import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignUpDTO;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    EditText inputNickname, inputEmail, inputPassword, inputConfirmPassword;

    AuthService authService;
    UserService userService;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authService = new RecGoApi().getAuthService();
        userService = UserService.getInstance();

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
            register();
        });

        inputEmail = findViewById(R.id.sign_up_email);
        inputNickname = findViewById(R.id.sign_up_nickname);
        inputPassword = findViewById(R.id.sign_up_password);
        inputConfirmPassword = findViewById(R.id.sign_up_confirm_password);
    }

    public void register() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String nickname = inputNickname.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if(!password.equals(confirmPassword) || password.length() <= 0){
            Toast.makeText(this, "Senha e confirmação são incompativeis", Toast.LENGTH_LONG).show();
            return;
        }

        SignUpDTO signUpDTO = new SignUpDTO(email, nickname, password);

        Call<SignResponseDTO> signResponseDTOCall = authService.signUp(signUpDTO);

        signResponseDTOCall.enqueue(new Callback<SignResponseDTO>() {
            @Override
            public void onResponse(Call<SignResponseDTO> call, Response<SignResponseDTO> response) {
                SignResponseDTO signResponseDTO = response.body();
                if(signResponseDTO != null){
                    userService.setUserData(signResponseDTO);
                    Toast.makeText(ctx, signResponseDTO.getJwt(), Toast.LENGTH_LONG).show();
                    goTo(null, SignInActivity.class);
                } else {
                    Toast.makeText(ctx, "Erro na requisição, verifique os dados cadatrais", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SignResponseDTO> call, Throwable t) {
                Toast.makeText(ctx, "Erro na requisição, verifique os dados cadatrais", Toast.LENGTH_LONG).show();
            }
        });
    }
}