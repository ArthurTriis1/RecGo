package br.edu.ufpe.recife.tads.recgo.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.services.UserManagementService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Item;
import br.edu.ufpe.recife.tads.recgo.models.dto.RequestWearItemDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import br.edu.ufpe.recife.tads.recgo.utils.PropertiesConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryRecyclerAdapter extends RecyclerView.Adapter<InventoryRecyclerAdapter.InventoryViewHolder> {

    private Context ctx;
    private List<Item> itemList;

    private UserManagementService userManagementService;
    private UserService userService;
    private Long userId;
    private String jwt;

    public InventoryRecyclerAdapter(Context ctx, List<Item> itemList, UserManagementService userManagementService, Long userId, String jwt) {
        this.ctx = ctx;
        this.itemList = itemList;
        this.userManagementService = userManagementService;
        this.userId = userId;
        this.jwt = jwt;

        userService = UserService.getInstance();
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.grid_inventory_item, parent, false);
        return new InventoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Item item = this.itemList.get(position);

        holder.itemName.setText(item.getName());

        Picasso
            .get()
            .load(PropertiesConfig.getBaseUrl() + item.getImage().url)
            .into(holder.itemImage);

        holder.itemCard.setOnClickListener(v -> {
            new AlertDialog.Builder(ctx)
                    .setTitle("Equipar item")
                    .setMessage("Você deseja equipar o item " + item.getName() + "?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateWearItem(item);
                        }

                    })
                    .setNegativeButton("Não", null)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private void updateWearItem(Item item){
        RequestWearItemDTO requestWearItemDTO = new RequestWearItemDTO(item);

        Call<User> updateWearItemCall = userManagementService.updateWearItem(requestWearItemDTO, userId, "Bearer " + jwt);

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

    public static  class  InventoryViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemName;
        CardView itemCard;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemCard = itemView.findViewById(R.id.inventory_item);
            this.itemImage = itemView.findViewById(R.id.inventory_item_image);
            this.itemName = itemView.findViewById(R.id.inventory_item_name);
        }
    }
}
