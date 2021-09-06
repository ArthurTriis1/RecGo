package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.UserManagementService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Item;
import br.edu.ufpe.recife.tads.recgo.models.dto.Place;
import br.edu.ufpe.recife.tads.recgo.models.dto.RequestUpdateItemsDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import br.edu.ufpe.recife.tads.recgo.utils.PropertiesConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationDetailActivity extends AppCompatActivity {

    ImageView backIcon;

    TextView viewName;
    TextView viewLocation;
    TextView viewExperience;
    TextView viewDescription;
    ImageView viewImage;
    CardView viewItem;
    TextView viewItemName;
    ImageView viewItemImage;

    Place place;
    boolean canAccess;

    boolean alreadyHaveItem = false;

    UserService userService;

    UserManagementService userManagementService;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.canAccess = (boolean) getIntent().getExtras().get("canAccess");

        userService = UserService.getInstance();

        userManagementService = new RecGoApi().getUserManagementService();

        setContentView(R.layout.activity_location_detail);
        setAllViews();

        setPlaceDetails();
    }

    private void setPlaceDetails() {
        Place place = (Place) getIntent().getExtras().get("place");
        this.place = place;
        viewName.setText(place.getName());
        viewDescription.setText(place.getDescription());
        viewLocation.setText(place.getLocation());
        viewExperience.setText(place.getExperience() + "XP");
        viewItemName.setText(place.getItem().getName());

        alreadyHaveItem = userService.getUser().getStorageItems().stream().map(Item::getId).anyMatch(i -> {
           return i.equals(place.getItem().getId());
        });

        Picasso
            .get()
            .load(PropertiesConfig.getBaseUrl() + place.getImage().url)
            .into(viewImage);

        Picasso
            .get()
            .load(PropertiesConfig.getBaseUrl() + place.getItem().getImage().url)
            .into(viewItemImage);

        viewItem.setOnClickListener((View v) -> {
            if(alreadyHaveItem){
                this.alreadyHaveItem();
            } else {
                if(this.canAccess){
                    this.getItem();
                } else {
                    this.cantGetItem();
                }
            }
        });

        if(!canAccess){
            viewItem.setAlpha(0.4F);
        }
    }

    private void setAllViews() {
        backIcon = findViewById(R.id.location_detail_back_icon);
        backIcon.setOnClickListener(v -> {
            finish();
        });

        viewName = findViewById(R.id.location_details_name);
        viewLocation = findViewById(R.id.location_details_location);
        viewExperience = findViewById(R.id.location_details_experience);
        viewDescription = findViewById(R.id.location_details_description);
        viewImage = findViewById(R.id.location_details_image);
        viewItem = findViewById(R.id.location_details_item);
        viewItemName = findViewById(R.id.location_details_item_name);
        viewItemImage = findViewById(R.id.location_details_item_image);
    }

    private void getItem(){
        String jwt = "Bearer " + userService.getJWT();
        long userId = userService.getUser().getId();
        List<Integer> itemsList = userService.getUser().getStorageItems().stream().map(Item::getId).collect(Collectors.toList());
        itemsList.add(place.getItem().getId());

        RequestUpdateItemsDTO requestUpdateItemsDTO = new RequestUpdateItemsDTO(itemsList);

        Call<User> signResponseDTOCall = userManagementService.updateItems(requestUpdateItemsDTO, userId, jwt);

        signResponseDTOCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user != null){
                    userService.setUser(user);
                    alreadyHaveItem = true;
                    Toast toast = Toast.makeText(ctx, "Item adicionado, vá no inventario para equipa-lo", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast.makeText(ctx, "Erro ao adicionar item, tente novamente mais tarde", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ctx, "Erro ao adicionar item, tente novamente mais tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cantGetItem(){
        Toast toast = Toast.makeText(this, "Vá até o local para poder resgatar o item", Toast.LENGTH_LONG);
        toast.show();
    }

    private void alreadyHaveItem(){
        Toast toast = Toast.makeText(this, "Você já tem esse item, vá no inventario para equipa-lo", Toast.LENGTH_LONG);
        toast.show();
    }
}