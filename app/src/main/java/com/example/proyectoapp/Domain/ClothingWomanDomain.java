package com.example.proyectoapp.Domain;

public class ClothingWomanDomain {

    String clothingwoman;
    String descripcion;
    String marca;
    String precio;

    public ClothingWomanDomain() {
    }

    public String getClothingwoman(){
        return this.clothingwoman;
    }

    public void setClothingwomanDescripcion(String clothingwoman){
        this.clothingwoman = clothingwoman;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getPrecio(){
        return this.precio;
    }

    public void setPrecio(String precio){
        this.precio = precio;
    }


}
