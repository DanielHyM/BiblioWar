package com.example.bibliowar.ui.favoritos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibliowar.MainActivity;
import com.example.bibliowar.R;
import com.example.bibliowar.ui.personajes.CharacterAdapter;
import com.example.bibliowar.ui.personajes.CharacterFragment;
import com.example.bibliowar.ui.personajes.Persona;

import java.util.ArrayList;

public class PersonFavoriteFragment extends Fragment {

    private RecyclerView rvPersona;
    private GridLayoutManager glm;
    private CharacterAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_person_favorite, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPersona = view.findViewById(R.id.rv_favorite_character);
        glm = new GridLayoutManager(view.getContext(), 1);
        rvPersona.setLayoutManager(glm);
        adapter = new CharacterAdapter(((MainActivity)getActivity()).listaPersonas, view.getContext());
        rvPersona.setAdapter(adapter);



    }
}