package br.edu.ufpe.recife.tads.recgo.models.dto;

public class RequestWearItemDTO {
    private Item headItem;
    private Item armItem;
    private Item backgroundItem;
    private Item legItem;
    private Item faceItem;
    private Item handItem;

    public RequestWearItemDTO(Item item){
        switch (item.getKind()){
            case "HEAD":
                headItem = item;
                break;
            case "ARM":
                armItem = item;
                break;
            case "LEG":
                legItem = item;
                break;
            case "HAND":
                handItem = item;
                break;
            case "FACE":
                faceItem = item;
                break;
            case "BACKGROUND":
                backgroundItem = item;
                break;
        }
    }
}
