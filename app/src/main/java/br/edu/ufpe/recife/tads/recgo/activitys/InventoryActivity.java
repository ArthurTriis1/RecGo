package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.UserManagementService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Item;
import br.edu.ufpe.recife.tads.recgo.models.dto.RequestWearItemDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryActivity extends AppCompatActivity {

    private UserService userService;
    private UserManagementService userManagementService;
    private User user;
    private String jwt;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventário");

        userService = UserService.getInstance();
        userManagementService = new RecGoApi().getUserManagementService();

        this.user = userService.getUser();
        this.jwt = userService.getJWT();

//        Call<User> userCall = userManagementService.getUser(this.user.getId(), this.jwt);

//        userCall.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                User user = response.body();
//                if(user != null){
//                    userService.setUser(user);
//                    goTo(null, MapsActivity.class);
//                } else {
//                    Toast.makeText(ctx, "Erro na requisição, verifique os dados cadatrais", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(ctx, "Erro na requisição, verifique login e senha", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }

    private void updateWearItem(Item item){
        RequestWearItemDTO requestWearItemDTO = new RequestWearItemDTO(item);

        Call<User> updateWearItemCall = userManagementService.updateWearItem(requestWearItemDTO, this.user.getId(), jwt);

        updateWearItemCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user != null){
                    userService.setUser(user);
                    Toast toast = Toast.makeText(ctx, item.getName() + " vestido, vá para La Ursa ver o resultado", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast.makeText(ctx, "Erro ao vestir item, tente novamente mais tarde", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ctx, "Erro ao vestir item, tente novamente mais tarde", Toast.LENGTH_LONG).show();
            }
        });
    }
}