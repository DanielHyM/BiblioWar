package com.example.bibliowar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button button_register;
    EditText et_login;
    EditText et_pass;
    Button button_log_in;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loadPreferences();
        mAuth = FirebaseAuth.getInstance();
        button_log_in = findViewById(R.id.button_log_in);

        button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });


        et_login = findViewById(R.id.et_login_textbox);
        et_pass = findViewById(R.id.et_password_textbox);



       button_log_in.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String email = et_login.getText().toString();
               String password = et_pass.getText().toString();

               mAuth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   Log.d("InicioSesionFirebase", "signInWithEmail:success");
                                   savePreferences();
                                   FirebaseUser user = mAuth.getCurrentUser();
                                   Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                   startActivity(i);
                               } else {
                                   // If sign in fails, display a message to the user.
                                   Log.w("InicioSesionFirebase", "signInWithEmail:failure", task.getException());
                                   Toast.makeText(LoginActivity.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                                   //updateUI(null);
                               }
                           }
                       });
           }
       });

        }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
       FirebaseUser currentUser = mAuth.getCurrentUser();

        //updateUI(currentUser);
    }

    private void savePreferences(){
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = et_login.getText().toString();
        String password = et_pass.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",usuario);
        editor.putString("pass",password);

        editor.commit();
        Toast.makeText(getApplicationContext(),"Prefencias guardadas",Toast.LENGTH_SHORT).show();

    }

    private void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = preferences.getString("user","noExistInfo").toString();
        String pass = preferences.getString("pass","noExistInfo").toString();

        if(!user.equals("noExistInfo") && !pass.equals("noExistInfo")){
            Toast.makeText(getApplicationContext(),"Prefencias cargadas",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }


}