package br.edu.ufpe.recife.tads.recgo.models.dto;

import java.util.List;

public class User {
    private long id;
    private String username;
    private String email;
    private String provider;
    private boolean confirmed;
    private String blocked = null;
    Role RoleObject;
    private String created_at;
    private String updated_at;
    private List<Item> storageItems;
    private Item headItem;
    private Item armItem;
    private Item backgroundItem;
    private Item legItem;
    private Item faceItem;
    private Item handItem;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getProvider() {
        return provider;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public String getBlocked() {
        return blocked;
    }

    public Role getRole() {
        return RoleObject;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public void setRole(Role roleObject) {
        this.RoleObject = roleObject;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<Item> getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(List<Item> storageItems) {
        this.storageItems = storageItems;
    }

    public Item getHeadItem() {
        return headItem;
    }

    public void setHeadItem(Item headItem) {
        this.headItem = headItem;
    }

    public Item getArmItem() {
        return armItem;
    }

    public void setArmItem(Item armItem) {
        this.armItem = armItem;
    }

    public Item getBackgroundItem() {
        return backgroundItem;
    }

    public void setBackgroundItem(Item backgroundItem) {
        this.backgroundItem = backgroundItem;
    }

    public Item getLegItem() {
        return legItem;
    }

    public void setLegItem(Item legItem) {
        this.legItem = legItem;
    }

    public Item getFaceItem() {
        return faceItem;
    }

    public void setFaceItem(Item faceItem) {
        this.faceItem = faceItem;
    }

    public Item getHandItem() {
        return handItem;
    }

    public void setHandItem(Item handItem) {
        this.handItem = handItem;
    }
}
