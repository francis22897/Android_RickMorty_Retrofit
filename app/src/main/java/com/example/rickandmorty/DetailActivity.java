package com.example.rickandmorty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView image;
    TextView name, id, status, species, gender, origin, lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            Picasso.get().load(intent.getStringExtra("IMAGE")).resize(Utils.detailImageWidth, Utils.detailImageHeight).into(image);
            name.setText(intent.getStringExtra("NAME"));
            id.setText(String.valueOf(intent.getIntExtra("ID", 0)));
            status.setText(intent.getStringExtra("STATUS"));
            species.setText(intent.getStringExtra("SPECIES"));
            gender.setText(intent.getStringExtra("GENDER"));
            origin.setText(intent.getStringExtra("ORIGIN"));
            lastLocation.setText(intent.getStringExtra("LOCATION"));
        }
    }

    private void init(){
        image = findViewById(R.id.detail_character_image);
        name = findViewById(R.id.detail_name_text);
        id = findViewById(R.id.detail_id_text);
        status = findViewById(R.id.detail_status_text);
        species = findViewById(R.id.detail_species_text);
        gender = findViewById(R.id.detail_gender_text);
        origin = findViewById(R.id.detail_origin_text);
        lastLocation = findViewById(R.id.detail_lastLocation_text);
    }
}
