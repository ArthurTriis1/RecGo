package br.edu.ufpe.recife.tads.recgo.activitys.fragments;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.PlaceService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Place;
import br.edu.ufpe.recife.tads.recgo.ui.adapters.InventoryRecyclerAdapter;
import br.edu.ufpe.recife.tads.recgo.ui.adapters.PlacesRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllLocalsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Place> placesList;
    private PlacesRecyclerAdapter placesRecyclerAdapter;
    private PlaceService placeService;

    Context ctx;

    private Location userLocation;

    public AllLocalsFragment(Context ctx, Location location) {
        this.ctx = ctx;
        this.placeService = new RecGoApi().getPlaceService();
        this.userLocation = location;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_all_locals, container, false);

        findRoundLocations(inflate);
        return inflate;
    }

    private void configRecyclerView(View inflate) {
        recyclerView = inflate.findViewById(R.id.places_list_recyclerview);
        placesRecyclerAdapter = new PlacesRecyclerAdapter(ctx, placesList, userLocation);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(placesRecyclerAdapter);
    }

    private void findRoundLocations(View inflate) {

        Call<List<Place>> signResponseDTOCall = this.placeService.getPlaces();

        signResponseDTOCall.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                List<Place> placesListFinded = response.body();
                if(placesListFinded != null){
                    placesList = placesListFinded;
                    configRecyclerView(inflate);
                } else {
                    Toast.makeText(ctx, "Erro ao buscar locais", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(ctx, "Erro ao buscar locais", Toast.LENGTH_LONG).show();
            }
        });
    }
}