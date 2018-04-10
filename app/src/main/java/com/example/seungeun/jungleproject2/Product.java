package com.example.seungeun.jungleproject2;

import android.graphics.drawable.Drawable;
import android.widget.Button;

// 보여지는 장비 속성

public class Product {
    private Drawable equip_image ;
    private String equip_name, stockNumStr,equip_serialnumber,equip_category,
            equip_specification,equip_condition,equip_rentalstate,
            equip_note,equip_instruction;
   /* private String descStr ;//'잔여재고 개수'나타내 주는 스트링
    private Button cartBtn; //장바구니 담기 버튼*/

    public Product( String equip_name, String stockNumStr) {
        //this.equip_image = equip_image;
        this.equip_name = equip_name;
        this.stockNumStr = stockNumStr;
    }

    public void setEquip_image(Drawable equip_image) {
        this.equip_image = equip_image;
    }

    public void setEquip_name(String equip_name) {
        this.equip_name = equip_name;
    }

    public void setStockNumStr(String stockNumStr) {
        this.stockNumStr = stockNumStr;
    }

    public void setEquip_serialnumber(String equip_serialnumber) {
        this.equip_serialnumber = equip_serialnumber;
    }

    public void setEquip_category(String equip_category) {
        this.equip_category = equip_category;
    }

    public void setEquip_specification(String equip_specification) {
        this.equip_specification = equip_specification;
    }

    public void setEquip_condition(String equip_condition) {
        this.equip_condition = equip_condition;
    }

    public void setEquip_rentalstate(String equip_rentalstate) {
        this.equip_rentalstate = equip_rentalstate;
    }

    public void setEquip_note(String equip_note) {
        this.equip_note = equip_note;
    }

    public void setEquip_instruction(String equip_instruction) {
        this.equip_instruction = equip_instruction;
    }

    public Drawable getEquip_image() {
        return equip_image;
    }

    public String getEquip_name() {
        return equip_name;
    }

    public String getStockNumStr() {
        return stockNumStr;
    }

    public String getEquip_serialnumber() {
        return equip_serialnumber;
    }

    public String getEquip_category() {
        return equip_category;
    }

    public String getEquip_specification() {
        return equip_specification;
    }

    public String getEquip_condition() {
        return equip_condition;
    }

    public String getEquip_rentalstate() {
        return equip_rentalstate;
    }

    public String getEquip_note() {
        return equip_note;
    }

    public String getEquip_instruction() {
        return equip_instruction;
    }
}
