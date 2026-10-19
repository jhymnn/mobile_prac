package com.example.demo12.controller;

public class ItemDTO {
    private String itemname;
    private String itemprice;
    public ItemDTO() {
    }
    public ItemDTO(String itemname, String
            itemprice) {
        this.itemname = itemname;
        this.itemprice = itemprice;
    }
    public String getItemname() {
        return itemname;
    }
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    public String getItemprice() {
        return itemprice;
    }
    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }
}
