package com.example.bibliowar.ui.personajes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bibliowar.MainActivity;
import com.example.bibliowar.R;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<Persona> listaPersonas;
    private Context context;

    public CharacterAdapter(ArrayList<Persona> listaPersonas, Context c) {
        this.listaPersonas = listaPersonas;
        this.context = c;
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {

        Persona p = listaPersonas.get(position);
        Glide.with(context).load(p.getFoto()).into(holder.imgPersona);
        holder.tvNombre.setText(p.getNombre());
        holder.tvApellido.setText(p.getApellido());
        holder.tvFechaNac.setText(p.getFechaNac());
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgStar;
        ImageView imgPersona;
        TextView tvNombre;
        TextView tvApellido;
        TextView tvFechaNac;
        Button buttonDetail;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);


            imgPersona = itemView.findViewById(R.id.image_cardView_character);
            tvNombre = itemView.findViewById(R.id.tv_card_character_nombre);
            tvApellido = itemView.findViewById(R.id.tv_card_character_apellido);
            tvFechaNac = itemView.findViewById(R.id.tv_card_character_fechaNac);
            buttonDetail = itemView.findViewById(R.id.button_card_character_details);
            imgStar = itemView.findViewById(R.id.img_persona_star);


            buttonDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetailCharacterActivity.class);
                    Persona p = listaPersonas.get(getAdapterPosition());
                    i.putExtra("persona", (Parcelable) p);
                    v.getContext().startActivity(i);
                }
            });

            imgStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), MainActivity.class);
                    Persona p = listaPersonas.get(getAdapterPosition());
                    i.putExtra("persona", (Parcelable) p);
                    ((Activity) v.getContext()).startActivityForResult(i,1);
                }
            });


        }
    }
}
