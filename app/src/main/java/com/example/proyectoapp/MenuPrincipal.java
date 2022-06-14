package com.example.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;

import com.example.proyectoapp.Adapter.CategoryAdapter;
import com.example.proyectoapp.Categorias.HombreActivity;
import com.example.proyectoapp.Categorias.MujerActivity;
import com.example.proyectoapp.Categorias.NinaActivity;
import com.example.proyectoapp.Categorias.NinoActivity;
import com.example.proyectoapp.Categorias.OfertaActivity;
import com.example.proyectoapp.Domain.CategoryDomain;

import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity implements RecyclerViewInterface{
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recyclerViewCategory();
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Hombres","men"));
        category.add(new CategoryDomain("Mujeres","woman1"));
        category.add(new CategoryDomain("Niños","boy"));
        category.add(new CategoryDomain("Niñas","girl"));
        category.add(new CategoryDomain("Ofertas","offer"));

        adapter = new CategoryAdapter(category, this);
        recyclerViewCategoryList.setAdapter(adapter);

    }

    //Cambiar el activity al cliclear sobre el item del recyclerView
    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0:{
                //Agregar el resto de activitys para cada categoria! Lo lograremos, pero no tendremos que perder el tiempo jugando.
                Intent intent0 = new Intent(this, HombreActivity.class);
                startActivity(intent0);
                break;
            }
            case 1:{
                Intent intent1 = new Intent(this, MujerActivity.class);
                startActivity(intent1);
                break;
            }
            case 2:{
                Intent intent2 = new Intent(this, NinoActivity.class);
                startActivity(intent2);
                break;
            }
            case 3:{
                Intent intent3 = new Intent(this, NinaActivity.class);
                startActivity(intent3);
                break;
            }
            case 4:{
                Intent intent4 = new Intent(this, OfertaActivity.class);
                startActivity(intent4);
                break;
            }
        }
    }



}