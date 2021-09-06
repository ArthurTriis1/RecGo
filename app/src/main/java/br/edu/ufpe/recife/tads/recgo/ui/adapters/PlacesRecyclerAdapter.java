package br.edu.ufpe.recife.tads.recgo.ui.adapters;

import android.content.Context;

import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import br.edu.ufpe.recife.tads.recgo.R;

import br.edu.ufpe.recife.tads.recgo.activitys.LocationDetailActivity;
import br.edu.ufpe.recife.tads.recgo.models.dto.Place;
import br.edu.ufpe.recife.tads.recgo.utils.DIstanceCalculator;
import br.edu.ufpe.recife.tads.recgo.utils.PropertiesConfig;


public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.PlacesViewHolder> {

    private Context ctx;
    private List<Place> placeList;
    private Location userLocation;

    public PlacesRecyclerAdapter(Context ctx, List<Place> placeList, Location userLocation) {
        this.ctx = ctx;
        this.placeList = placeList;
        this.userLocation = userLocation;
    }

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.grid_place_item, parent, false);
        return new PlacesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHolder holder, int position) {

        Place place = placeList.get(position);
        double distance = DIstanceCalculator.calcDistanceInMeters(place.getCoordinates().latitude, place.getCoordinates().longitude, userLocation.getLatitude(), userLocation.getLongitude());
        boolean canAccess = DIstanceCalculator.canAccess(place.getCoordinates().latitude, place.getCoordinates().longitude, userLocation.getLatitude(), userLocation.getLongitude());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);


        holder.placeName.setText(place.getName());
        holder.placeExperience.setText(place.getExperience() + "XP");
        holder.placeDistance.setText(decimalFormat.format(distance) + "m");

        Picasso
            .get()
            .load(PropertiesConfig.getBaseUrl() + place.getImage().url)
            .into(holder.placeImage);

        holder.placeCard.setOnClickListener(v -> {
            Intent AllLocalsActivity = new Intent(ctx, LocationDetailActivity.class);
            AllLocalsActivity.putExtra("place", place);
            AllLocalsActivity.putExtra("canAccess", canAccess);
            ctx.startActivity(AllLocalsActivity);
        });

    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }


    public static  class  PlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName;
        TextView placeExperience;
        TextView placeDistance;
        CardView placeCard;

        public PlacesViewHolder(@NonNull View view) {
            super(view);
            this.placeImage = view.findViewById(R.id.place_item_image);
            this.placeCard = view.findViewById(R.id.place_item);
            this.placeExperience = view.findViewById(R.id.place_item_experience);
            this.placeDistance = view.findViewById(R.id.place_item_distance);
            this.placeName = view.findViewById(R.id.place_item_name);
        }
    }
}

