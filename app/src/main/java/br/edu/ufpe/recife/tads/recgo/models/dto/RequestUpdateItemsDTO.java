package br.edu.ufpe.recife.tads.recgo.models.dto;

import java.util.List;

public class RequestUpdateItemsDTO {
    private List<Integer> storageItems;

    public RequestUpdateItemsDTO(List<Integer> storageItems) {
        this.storageItems = storageItems;
    }

    public List<Integer> getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(List<Integer> storageItems) {
        this.storageItems = storageItems;
    }
}
