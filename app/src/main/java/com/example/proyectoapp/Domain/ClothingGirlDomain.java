package com.example.proyectoapp.Domain;

public class ClothingGirlDomain {

    String clothingirl;
    String descripcion;
    String marca;
    String precio;

    public ClothingGirlDomain(){
    }

    public String getClothingirl(){
        return this.clothingirl;
    }

    public void setClothingirl(String clothingirl){
        this.clothingirl = clothingirl;
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
