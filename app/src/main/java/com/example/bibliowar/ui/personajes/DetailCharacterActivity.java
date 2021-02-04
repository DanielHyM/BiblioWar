package com.example.bibliowar.ui.personajes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bibliowar.R;

public class DetailCharacterActivity extends AppCompatActivity {

    ImageView img;
    TextView nombre;
    TextView apellido;
    TextView fechaNac;
    String info;
    Button buttonInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_character);

        img = findViewById(R.id.char_detail_img);
        nombre = findViewById(R.id.detail_char_text_name_content);
        apellido = findViewById(R.id.detail_character_subname_content);
        fechaNac = findViewById(R.id.detail_character_fechaNac_content);
        buttonInfo = findViewById(R.id.button_detail_info);

        Persona p = getIntent().getParcelableExtra("persona");

        Glide.with(getApplicationContext()).load(p.getFoto()).into(img);
        nombre.setText(p.getNombre());
        apellido.setText(p.getApellido());
        fechaNac.setText(p.getFechaNac());

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = p.getInfo().toString();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });

    }
}