package com.example.proyectoapp;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, name, surname;
    Button buttonRegisterUser;
    boolean passwordVisible;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;
    private FirebaseFirestore firebaseFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseFireStore = FirebaseFirestore.getInstance();
        email = findViewById(R.id.etMail);
        password = findViewById(R.id.etPassword);
        name = findViewById(R.id.etNombre);
        surname = findViewById(R.id.etApellido);
        buttonRegisterUser = findViewById(R.id.btnRegistrarUser);

        //Invisibility password
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Rigth=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Rigth].getBounds().width()){
                        int selection=password.getSelectionEnd();
                        if(passwordVisible){
                            //Mostrar drawable
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_invisibility,0);
                            //Ocultar Contraseña
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            //Mostrar drawable
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility,0);
                            //Mostrar Contraseña
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        //FirebaseAutentication
        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        autentication();


    }

    //Autenticacion
    private void autentication(){

        buttonRegisterUser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String mail = email.getText().toString();
            String pass = password.getText().toString();

            String nameUser = name.getText().toString().trim();
            String surnameUser = surname.getText().toString().trim();

            //agregar datos del usuario.
            if(nameUser.isEmpty() && surnameUser.isEmpty()){
                Toast.makeText(getApplicationContext(),"Ingrese los datos", Toast.LENGTH_SHORT);

            }else{
                adduser(nameUser, surnameUser);
                finish();
            }

            if(awesomeValidation.validate()){
                firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Se registró correctamente", Toast.LENGTH_SHORT).show();

                            finish();

                        } else{
                            String errorCode =((FirebaseAuthException) task.getException()).getErrorCode();
                            error(errorCode);
                        }
                    }
                });
            } else{
                Toast.makeText(RegisterActivity.this, "Completa los datos correctamente", Toast.LENGTH_SHORT).show();
            }


        }
    });

    }

    //Metodo para agregar datos del usuario
    private void adduser(String nameUser, String surnameUser){
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre_usuario", nameUser);
        map.put("Apellido_usuario", surnameUser);

        firebaseFireStore.collection("usuarios").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Se registró los datos correctamente", Toast.LENGTH_LONG).show();
                //finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error al ingresar el dato", Toast.LENGTH_LONG).show();
            }
        });


    }


    //Mensajes de error en login
    private void error(String errores){
        switch (errores){
            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(this, "El Formato del Token personalizado es incorrecto", Toast.LENGTH_SHORT).show();
                break;
        }
    }





}