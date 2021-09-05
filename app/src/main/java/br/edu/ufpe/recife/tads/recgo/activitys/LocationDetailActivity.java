package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.models.dto.Place;
import br.edu.ufpe.recife.tads.recgo.utils.PropertiesConfig;

public class LocationDetailActivity extends AppCompatActivity {

    ImageView backIcon;

    TextView viewName;
    TextView viewLocation;
    TextView viewExperience;
    TextView viewDescription;
    ImageView viewImage;


    Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        setAllViews();

        setPlaceDetails();
    }

    private void setPlaceDetails() {
        Place place = (Place) getIntent().getExtras().get("place");
        viewName.setText(place.getName());
        viewDescription.setText(place.getDescription());
        viewLocation.setText(place.getLocation());
        viewExperience.setText(place.getExperience() + "XP");

        Picasso
            .get()
            .load(PropertiesConfig.getBaseUrl() + place.getImage().url)
            .into(viewImage);
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
    }
}