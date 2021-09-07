package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.api.RecGoApi;
import br.edu.ufpe.recife.tads.recgo.api.services.UserManagementService;
import br.edu.ufpe.recife.tads.recgo.models.dto.Image;
import br.edu.ufpe.recife.tads.recgo.models.dto.LevelInfo;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;
import br.edu.ufpe.recife.tads.recgo.services.UserService;
import br.edu.ufpe.recife.tads.recgo.utils.PropertiesConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    ImageView closeIcon;
    TextView inventoryText;
    ImageView logoutIcon;
    ImageView laursaHeadItem;
    ImageView laursaArmItem;
    ImageView laursaHandItem;
    ImageView laursaBackgroundItem;

    TextView profileLevel;
    ProgressBar progressBar;

    TextView profileName;


    UserManagementService userManagementService;
    UserService userService;

    User user;
    String jwt;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userManagementService = new RecGoApi().getUserManagementService();
        userService = UserService.getInstance();
        user = userService.getUser();
        jwt = "Bearer " + userService.getJWT();

        defineViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setLaursaWear();
        setLevelInfos();
    }

    private void defineViews(){
        this.closeIcon = findViewById(R.id.user_profile_close_icon);
        this.closeIcon.setOnClickListener(v -> {
            finish();
        });

        this.inventoryText = findViewById(R.id.user_profile_text_inventory);
        this.inventoryText.setOnClickListener(v -> {
            this.goTo(v, InventoryActivity.class);
        });

        this.logoutIcon = findViewById(R.id.user_profile_logout_icon);
        this.logoutIcon.setOnClickListener(v -> {
            this.goTo(v, SignInActivity.class);
        });

        laursaHeadItem = findViewById(R.id.laursa_head_item);
        laursaArmItem = findViewById(R.id.laursa_arm_item);
        laursaHandItem = findViewById(R.id.laursa_hand_item);
        laursaBackgroundItem = findViewById(R.id.laursa_background_item);

        profileName = findViewById(R.id.user_profile_username);
        profileName.setText(user.getUsername());

        profileLevel = findViewById(R.id.profile_level);
        progressBar = findViewById(R.id.profile_progressbar);
    }

    private void goTo(View v, Class c){
        Intent AllLocalsActivity = new Intent(this, c);
        startActivity(AllLocalsActivity);
    }

    private void setLaursaWear() {
        Call<User> getUserCall = userManagementService.getUser(user.getId(), jwt);

        getUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    userService.setUser(user);
                    wearLaursa(user);
                } else {
                    Toast.makeText(ctx, "Erro ao buscar dados, tente novamente mais tarde", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ctx, "Erro ao buscar dados, tente novamente mais tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setLevelInfos(){
        LevelInfo levelInfo = new LevelInfo(user);
        profileLevel.setText(levelInfo.getLevel() + "");
        progressBar.setProgress(levelInfo.getPartialExperience());
    }

    private void wearLaursa(User user){
        if(user.getHandItem() != null){



            Picasso
                .get()
                .load(PropertiesConfig.getBaseUrl() + user.getHandItem().getImage().url)
                .into(laursaHandItem);

            ViewGroup.LayoutParams imageLayoutParams = getImageLayoutParams(user.getHandItem().getImage(), laursaHandItem);
            laursaHandItem.setLayoutParams(imageLayoutParams);
        }

        if(user.getHeadItem() != null){
            Picasso
                    .get()
                    .load(PropertiesConfig.getBaseUrl() + user.getHeadItem().getImage().url)
                    .into(laursaHeadItem);

            ViewGroup.LayoutParams imageLayoutParams = getImageLayoutParams(user.getHeadItem().getImage(), laursaHeadItem);
            laursaHeadItem.setLayoutParams(imageLayoutParams);
        }

        if(user.getArmItem() != null){
            Picasso
                    .get()
                    .load(PropertiesConfig.getBaseUrl() + user.getArmItem().getImage().url)
                    .into(laursaArmItem);


            ViewGroup.LayoutParams imageLayoutParams = getImageLayoutParams(user.getArmItem().getImage(), laursaArmItem);
            laursaArmItem.setLayoutParams(imageLayoutParams);
        }

        if(user.getBackgroundItem() != null){
            Picasso
                    .get()
                    .load(PropertiesConfig.getBaseUrl() + user.getBackgroundItem().getImage().url)
                    .into(laursaBackgroundItem);
        }

    }

    private ViewGroup.LayoutParams getImageLayoutParams(Image image, ImageView imageView){
        final float scale = this.getResources().getDisplayMetrics().density;
        int height_ = (int) (image.height * scale);
        int width_ = (int) (image.width * scale);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = height_;
        params.width = width_;
        return params;

    }
}