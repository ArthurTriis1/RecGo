package br.edu.ufpe.recife.tads.recgo.api.services;

import java.util.List;

import br.edu.ufpe.recife.tads.recgo.models.dto.Place;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceService {
    @GET("places")
    public Call<List<Place>> getPlaces();
}
