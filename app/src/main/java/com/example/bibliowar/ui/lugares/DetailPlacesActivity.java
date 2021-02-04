package com.example.bibliowar.ui.lugares;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bibliowar.R;

public class DetailPlacesActivity extends AppCompatActivity {

    ImageView image;
    TextView place_name;
    TextView country;
    TextView continente;
    Button button_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_places);

        image = findViewById(R.id.img_detail_places);
        place_name = findViewById(R.id.detail_places_name);
        country = findViewById(R.id.detail_places_country);
        continente = findViewById(R.id.detail_places_continente);
        button_detail = findViewById(R.id.detail_places_button);

        Lugar lug = getIntent().getParcelableExtra("lugar");
        Glide.with(getApplicationContext()).load(lug.getFoto()).into(image);
        place_name.setText(lug.getNombre().toString());
        country.setText(lug.getPais().toString());
        continente.setText(lug.getContinente().toString());

        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String q = lug.getInfo().toString();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);


            }
        });






    }
}