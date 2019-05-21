package com.example.rickandmorty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CharacterViewHolder> {

    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        TextView name, id, status, species, gender, origin, lastLocation;

        CharacterViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            image = itemView.findViewById(R.id.character_image);
            name = itemView.findViewById(R.id.name_text);
            id = itemView.findViewById(R.id.id_text);
            status = itemView.findViewById(R.id.status_text);
            species = itemView.findViewById(R.id.species_text);
            gender = itemView.findViewById(R.id.gender_text);
            origin = itemView.findViewById(R.id.origin_text);
            lastLocation = itemView.findViewById(R.id.lastLocation_text);
        }
    }

    List<Character> characters;
    Context context;

    public RVAdapter(Context context, List<Character> characters){
        this.context = context;
        this.characters = characters;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.character_item, viewGroup, false);
        final CharacterViewHolder cvh = new CharacterViewHolder(v);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder characterViewHolder, int i) {
        Picasso.get().load(characters.get(i).getImage()).resize(Utils.imageWidth, Utils.imageHeight).into(characterViewHolder.image);
        characterViewHolder.id.setText(String.valueOf(characters.get(i).getId()));
        characterViewHolder.name.setText(characters.get(i).getName());
        characterViewHolder.status.setText(characters.get(i).getStatus());
        characterViewHolder.species.setText(characters.get(i).getSpecies());
        characterViewHolder.gender.setText(characters.get(i).getGender());
        characterViewHolder.origin.setText(characters.get(i).getOrigin().getName());
        characterViewHolder.lastLocation.setText(characters.get(i).getLocation().getName());
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }


}
