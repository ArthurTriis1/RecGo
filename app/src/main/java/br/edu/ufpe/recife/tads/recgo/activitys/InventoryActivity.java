package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.UserManagementService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Item;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import br.edu.ufpe.recife.tads.recgo.ui.adapters.InventoryRecyclerAdapter;

public class InventoryActivity extends AppCompatActivity {

    private UserService userService;
    private UserManagementService userManagementService;
    private User user;
    private String jwt;

    private RecyclerView recyclerView;
    private List<Item> itemsList;
    private InventoryRecyclerAdapter inventoryRecyclerAdapter;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventário");

        userService = UserService.getInstance();
        userManagementService = new RecGoApi().getUserManagementService();

        this.user = userService.getUser();
        this.itemsList = this.user.getStorageItems();
        this.jwt = userService.getJWT();

        configRecyclerView();


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

    private void configRecyclerView() {
        recyclerView = findViewById(R.id.inventory_recyclerview);
        inventoryRecyclerAdapter = new InventoryRecyclerAdapter(this, itemsList, userManagementService, user.getId(), jwt);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(inventoryRecyclerAdapter);
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }
}