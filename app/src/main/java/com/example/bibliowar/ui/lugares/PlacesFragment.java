package com.example.bibliowar.ui.lugares;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibliowar.R;
import com.example.bibliowar.ui.personajes.CharacterAdapter;
import com.example.bibliowar.ui.personajes.Persona;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlacesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlacesFragment extends Fragment {

    private RecyclerView rvLugares;
    private GridLayoutManager glm;
    private PlacesAdapter adapter;
    private StorageReference storageRef;
    ArrayList<Lugar> listaLugares = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlacesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlacesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlacesFragment newInstance(String param1, String param2) {
        PlacesFragment fragment = new PlacesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //loadDataOnCloudFirestore();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();



        db.collection("lugares")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("lugarInsert", document.getId() + " => " + document.getData());
                                listaLugares.add(new Lugar(document.get("nombre").toString(),document.get("pais").toString(),
                                        document.get("continente").toString(),document.get("info").toString(),document.get("foto").toString()));
                            }
                        } else {
                            Log.w("personaInsert", "Error getting documents.", task.getException());
                        }

                        rvLugares = view.findViewById(R.id.rv_places);
                        glm = new GridLayoutManager(view.getContext(),1);
                        rvLugares.setLayoutManager(glm);
                        adapter = new PlacesAdapter(listaLugares, view.getContext());
                        rvLugares.setAdapter(adapter);

                    }
                });
    }

    public void loadDataOnCloudFirestore(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listaLugares.add(new Lugar("Normandía","Francia","Europa","https://es.wikipedia.org/wiki/Batalla_de_Normand%C3%ADa",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fnormandia.jpg?alt=media&token=65657ca1-9ac4-4fd5-82cd-17c7c3d7d40f"));
        listaLugares.add(new Lugar("Pearl Harbor","EE.UU","America","https://es.wikipedia.org/wiki/Ataque_a_Pearl_Harbor",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fharbor.jpg?alt=media&token=b1979d9e-1a3c-4c7b-b5f3-26f32f3f4922"));
        listaLugares.add(new Lugar("Kehlsteinhaus","Alemania","Europa","https://es.wikipedia.org/wiki/Kehlsteinhaus",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fnidoaguila.jpg?alt=media&token=929cd044-bf78-488e-a767-df357bfc7fef"));
        listaLugares.add(new Lugar("Auschwitz","Alemania","Europa","https://es.wikipedia.org/wiki/Auschwitz",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fausch.jpg?alt=media&token=067b61aa-f698-41ca-b84f-f27f5a96251e"));
        listaLugares.add(new Lugar("Río Kwai","Tailandia","Asia","https://es.wikipedia.org/wiki/Puente_sobre_el_r%C3%ADo_Kwai",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fkwai.jpg?alt=media&token=4786f332-5566-4f99-82ac-8f0070e37fc6"));
        listaLugares.add(new Lugar("Berlin","Alemania","Europa","https://es.wikipedia.org/wiki/Batalla_de_Berl%C3%ADn",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fberlin.jpg?alt=media&token=5dce67c1-d80b-4f9f-ad72-a67c0a1c427b"));


        for(int f = 0; f < listaLugares.size(); f++){

            db.collection("lugares")
                    .add(listaLugares.get(f))
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("lugar", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("lugar", "Error adding document", e);
                        }
                    });

        }


    }


}