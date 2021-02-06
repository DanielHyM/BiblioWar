package com.example.bibliowar;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.bibliowar.ui.favoritos.Favoritos;
import com.example.bibliowar.ui.lugares.Lugar;
import com.example.bibliowar.ui.personajes.Persona;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    public  NavController navController;
    private DrawerLayout mDrawerLayout;
    private Button setting;
    public  ArrayList<Persona> listaPersonas = new ArrayList<>();
    public ArrayList<Lugar> listaLugares = new ArrayList<>();
    private Favoritos fav;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fav = new Favoritos();
        fav.setUser(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.fragment_characters_container)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);


    }






    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                removePreferences();
                break;
        }

        return false ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:

                break;

            case R.id.nav_places:

                 navController.navigate(R.id.placesFragment);
                break;

            case R.id.nav_characters:
                navController.navigate(R.id.characterFragment);
                break;

            case R.id.nav_favorites:

                if(listaPersonas.size() > 0) {
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("listaPersonas", fav.getListaPersonas());
                    navController.navigate(R.id.favoriteFragment,b);
                }else{
                    Toast.makeText(getApplicationContext(),"NO TIENES FAVORITOS AÚN", Toast.LENGTH_LONG).show();
                }

                break;


        }

        drawer.close();
        return false;
    }


    private void removePreferences(){
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = preferences.getString("user","noExistInfo").toString();
        String pass = preferences.getString("pass","noExistInfo").toString();

        if(!user.equals("noExistInfo") && !pass.equals("noExistInfo")){
            Toast.makeText(getApplicationContext(),"Prefencias eliminadas",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("user");
            editor.remove("pass");
            editor.commit();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {



    }
}