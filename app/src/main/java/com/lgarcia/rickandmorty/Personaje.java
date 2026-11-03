package com.lgarcia.rickandmorty;

public class Personaje {

    private int id;
    private String name;
    private String status;
    private String specie;
    private String type;
    private String gender;
    private String originName;
    private String nameLocation;
    private String imageUrl;

    public Personaje(int id, String name, String status, String specie, String type, String gender, String originName, String nameLocation, String imageUrl) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.specie = specie;
        this.type = type;
        this.gender = gender;
        this.originName = originName;
        this.nameLocation = nameLocation;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecie() {
        return specie;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getOriginName() {
        return originName;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
