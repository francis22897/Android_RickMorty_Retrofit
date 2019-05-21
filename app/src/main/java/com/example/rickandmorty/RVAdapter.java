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
        TextView name, id, species;

        CharacterViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            image = itemView.findViewById(R.id.character_image);
            name = itemView.findViewById(R.id.name_text);
            id = itemView.findViewById(R.id.id_text);
            species = itemView.findViewById(R.id.species_text);
        }
    }

    List<Character> characters;
    Context context;
    CustomItemclick customItemclick;

    public RVAdapter(Context context, List<Character> characters, CustomItemclick customItemclick){
        this.context = context;
        this.characters = characters;
        this.customItemclick = customItemclick;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.character_item, viewGroup, false);
        final CharacterViewHolder cvh = new CharacterViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemclick.onClick(v, characters.get(cvh.getAdapterPosition()).getId());
            }
        });

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder characterViewHolder, int i) {
        Picasso.get().load(characters.get(i).getImage()).resize(Utils.imageWidth, Utils.imageHeight).into(characterViewHolder.image);
        characterViewHolder.id.setText(String.valueOf(characters.get(i).getId()));
        characterViewHolder.name.setText(characters.get(i).getName());
        characterViewHolder.species.setText(characters.get(i).getSpecies());
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }


}
