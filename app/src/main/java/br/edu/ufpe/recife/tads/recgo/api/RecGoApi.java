package br.edu.ufpe.recife.tads.recgo.api;

import br.edu.ufpe.recife.tads.recgo.api.services.AuthService;
import br.edu.ufpe.recife.tads.recgo.api.services.PlaceService;
import br.edu.ufpe.recife.tads.recgo.utils.PropertiesConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecGoApi {

    private final String URL_BASE = PropertiesConfig.getBaseUrl();

    private final AuthService authService;
    private final PlaceService placeService;

    public RecGoApi() {
        OkHttpClient client = configureClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authService = retrofit.create(AuthService.class);
        placeService = retrofit.create(PlaceService.class);
    }

    private OkHttpClient configureClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public  PlaceService getPlaceService() { return placeService; }
}
