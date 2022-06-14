package com.example.proyectoapp.Domain;

public class ClothingMenDomain {

    String clothingmen;
    String descripcion;
    String marca;
    String precio;


    public ClothingMenDomain() {
    }

    public String getClothingmen() {
        return this.clothingmen;
    }

    public void setClothingmen(String clothingmen) {
        this.clothingmen = clothingmen;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public  String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public  String getPrecio(){
        return this.precio;
    }

    public void setPrecio(String precio){
        this.precio = precio;
    }

}