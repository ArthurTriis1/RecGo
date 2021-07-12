package br.edu.ufpe.recife.tads.recgo.models;

import br.edu.ufpe.recife.tads.recgo.enums.CollectionType;

public class CollectionItem {
    private Long id;
    private String imgUrl;
    private String name;
    private Place place;

    private CollectionType type;

    public CollectionType getType() {
        return type;
    }

    public void setType(CollectionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
