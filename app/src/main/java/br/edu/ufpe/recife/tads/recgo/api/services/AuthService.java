package br.edu.ufpe.recife.tads.recgo.api.services;

import br.edu.ufpe.recife.tads.recgo.models.dto.SignInDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignUpDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/local/register")
    public Call<SignResponseDTO> signUp(@Body SignUpDTO signUpDTO);

    @POST("auth/local")
    public Call<SignResponseDTO> signIn(@Body SignInDTO signInDTO);
}
