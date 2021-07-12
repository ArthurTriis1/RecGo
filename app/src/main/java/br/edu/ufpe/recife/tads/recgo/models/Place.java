package br.edu.ufpe.recife.tads.recgo.models;

public class Place {
    private Long id;
    private String name;
    private String imgUrl;

    private Double lat;
    private Double lon;

    private CollectionItem collectionItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public CollectionItem getCollectionItem() {
        return collectionItem;
    }

    public void setCollectionItem(CollectionItem collectionItem) {
        this.collectionItem = collectionItem;
    }
}
