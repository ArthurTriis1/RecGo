package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.PlaceService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Place;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int FINE_LOCATION_REQUEST = 0;
    private GoogleMap mMap;
    private boolean fine_location;
    private ImageView userIcon;
    private ImageView viewIcon;
    private TextView viewUserName;

    private UserService userService;

    private PlaceService placeService;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.userService = UserService.getInstance();

        this.placeService = new RecGoApi().getPlaceService();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setAllViews();
        requestPermission();
        currentLocation();
        setUserData();
    }

    private void setUserData() {
        String userName = userService.getUser().getUsername();
        this.viewUserName.setText(userName);
    }

    private void setAllViews() {
        this.userIcon = findViewById(R.id.maps_user_icon);
        this.viewIcon = findViewById(R.id.maps_view_icon);
        this.viewUserName = findViewById(R.id.maps_user_name);
        this.viewIcon.setOnClickListener(v -> {this.goTo(v, ListAllLocalsActivity.class);});
        this.userIcon.setOnClickListener(v -> {this.goTo(v, UserProfileActivity.class);});
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationClickListener(
                new GoogleMap.OnMyLocationClickListener() {
                    @Override
                    public void onMyLocationClick(@NonNull Location location) {
                        Toast.makeText(MapsActivity.this,
                                "Você está aqui!", Toast.LENGTH_SHORT).show();
                    }
                });

        this.setMyLocaleEnabled();


        findRoundLocations(googleMap);
    }

    private void requestPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        this.fine_location = (permissionCheck == PackageManager.PERMISSION_GRANTED);
        if (this.fine_location) return;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                FINE_LOCATION_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean granted = (grantResults.length > 0) &&
                (grantResults[0] == PackageManager.PERMISSION_GRANTED);
        this.fine_location = (requestCode == FINE_LOCATION_REQUEST) && granted;

        if (mMap != null) {
            this.setMyLocaleEnabled();
        }
    }

    public void currentLocation() {
        Task<Location> task = this.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null) {
                    mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(
                                            location.getLatitude(),
                                            location.getLongitude()
                                    ),18));
                }
            }
        });
    }

    private void setMyLocaleEnabled(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mMap.setMyLocationEnabled(this.fine_location);
    }

    private Task<Location> getLastLocation(){
        FusedLocationProviderClient fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return fusedLocationProviderClient.getLastLocation();
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }

    private void findRoundLocations(GoogleMap googleMap) {

        Call<List<Place>> signResponseDTOCall = this.placeService.getPlaces();

        signResponseDTOCall.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                List<Place> placesList = response.body();
                if(placesList != null){
                    setFindedLocations(googleMap, placesList);

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

    private void setFindedLocations(GoogleMap googleMap, List<Place> listPlaces){
        mMap = googleMap;

        listPlaces.forEach(place -> {
            LatLng position = new LatLng(place.getCoordinates().latitude, place.getCoordinates().longitude);
            Marker marker = mMap.addMarker( new MarkerOptions().
                    position(position).
                    title(place.getName()).
                    icon(BitmapDescriptorFactory.defaultMarker(240)));
            marker.setTag(place);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Place place = (Place) marker.getTag();

                    Intent AllLocalsActivity = new Intent(ctx, LocationDetailActivity.class);
                    AllLocalsActivity.putExtra("place", place);
                    startActivity(AllLocalsActivity);
                    return false;
                }
            });
        });
    }

}