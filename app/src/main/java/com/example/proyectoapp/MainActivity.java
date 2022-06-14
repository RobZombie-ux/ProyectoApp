package com.example.proyectoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {

    Button buttonRegistrar, buttonLogin;
    EditText emailLogin, passwordLogin;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Animación degradado
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        //Awesome Validation
        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.txtEmailLogin, Patterns.EMAIL_ADDRESS, R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.txtPasswordLogin, ".{6,}",R.string.invalid_password);

        emailLogin = findViewById(R.id.txtEmailLogin);
        passwordLogin = findViewById(R.id.txtPasswordLogin);
        buttonRegistrar = findViewById(R.id.btnRegistrar);
        buttonLogin = findViewById(R.id.btnLogin);

        //Cambiar al activity de registro.
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentRegister();
            }
        });
        //Metodo del ButtonLogin
        buttonLogin();


    }

    //Button Login
    private void buttonLogin(){

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = emailLogin.getText().toString();
                String pass = passwordLogin.getText().toString();

                if(awesomeValidation.validate()){
                    firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Iniciaste sesión correctamente!", Toast.LENGTH_SHORT).show();
                                intentMenuPrincipal();

                            } else{
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                errores(errorCode);
                            }
                        }
                    });
                } else{
                    Toast.makeText(MainActivity.this, "Completa los datos correctamente", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    //Metodo Intent Menu Principal
    private void intentMenuPrincipal(){
        Intent intentMenu = new Intent(this, MenuPrincipal.class);
        startActivity(intentMenu);
    }

    //Metodo Errores
    private void errores(String error){

        switch (error){
            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(this, "El Formato del Token personalizado es incorrecto", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //Intent Registro
    private void IntentRegister(){
            Intent intentRegister = new Intent(this, RegisterActivity.class);
            startActivity(intentRegister);
        }



}