package br.edu.ufpe.recife.tads.recgo.models;

import java.util.List;

public class User {
    private Long id;
    private String nickname;
    private Integer experience;
    private Integer level;
    private Double actualLat;
    private Double actualLon;
    private String laursaColor;
    private String imgUrl;

    private CollectionItem headItem;
    private CollectionItem legsItem;
    private CollectionItem armsItem;
    private CollectionItem faceItem;
    private CollectionItem backgroundItem;

    private List<CollectionItem> collectionItemList;
    private List<Place> visitedPlaces;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLaursaColor() {
        return laursaColor;
    }

    public void setLaursaColor(String laursaColor) {
        this.laursaColor = laursaColor;
    }

    public CollectionItem getHeadItem() {
        return headItem;
    }

    public void setHeadItem(CollectionItem headItem) {
        this.headItem = headItem;
    }

    public CollectionItem getLegsItem() {
        return legsItem;
    }

    public void setLegsItem(CollectionItem legsItem) {
        this.legsItem = legsItem;
    }

    public CollectionItem getArmsItem() {
        return armsItem;
    }

    public void setArmsItem(CollectionItem armsItem) {
        this.armsItem = armsItem;
    }

    public CollectionItem getFaceItem() {
        return faceItem;
    }

    public void setFaceItem(CollectionItem faceItem) {
        this.faceItem = faceItem;
    }

    public CollectionItem getBackgroundItem() {
        return backgroundItem;
    }

    public void setBackgroundItem(CollectionItem backgroundItem) {
        this.backgroundItem = backgroundItem;
    }

    public Double getActualLat() {
        return actualLat;
    }

    public void setActualLat(Double actualLat) {
        this.actualLat = actualLat;
    }

    public Double getActualLon() {
        return actualLon;
    }

    public void setActualLon(Double actualLon) {
        this.actualLon = actualLon;
    }

    public List<Place> getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(List<Place> visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<CollectionItem> getCollectionItemList() {
        return collectionItemList;
    }

    public void setCollectionItemList(List<CollectionItem> collectionItemList) {
        this.collectionItemList = collectionItemList;
    }
}
