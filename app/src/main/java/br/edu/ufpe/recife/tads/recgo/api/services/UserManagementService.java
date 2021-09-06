package br.edu.ufpe.recife.tads.recgo.api.services;

import br.edu.ufpe.recife.tads.recgo.models.dto.RequestUpdateItemsDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.RequestWearItemDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserManagementService {
    @PUT("users/{id}")
    public Call<User> updateItems(
        @Body RequestUpdateItemsDTO requestUpdateItemsDTO,
        @Path("id") long userId,
        @Header("authorization") String authorization
    );

    @PUT("users/{id}")
    public Call<User> updateWearItem(
            @Body RequestWearItemDTO requestWearItemDTO,
            @Path("id") long userId,
            @Header("authorization") String authorization
    );

    @GET("users/{id}")
    public Call<User> getUser(
            @Path("id") long userId,
            @Header("authorization") String authorization
    );
}
