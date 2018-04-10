package com.example.seungeun.jungleproject2;

//위시리스트 폴더

import java.util.ArrayList;
import java.util.List;

public class WishListFolder {
    public String folderName;
    public List<Product> childList = new ArrayList<Product>();

    public WishListFolder(String position){
        this.folderName = position;
    }

    public String toString () {
        return folderName;
    }

}
