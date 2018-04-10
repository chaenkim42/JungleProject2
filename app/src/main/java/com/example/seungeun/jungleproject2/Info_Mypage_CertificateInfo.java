package com.example.seungeun.jungleproject2;

/**
 * Created by samsung on 2017-08-07.
 */

public class Info_Mypage_CertificateInfo {
    private int serialNum; // 확인증 각각 구별가능한 시리얼넘버(약식)
    private String title; // 영상연출 영화제작
    private String equip_list; // 5dmark3/배터리2개/megaled/레코더/믹서
    private String progress_state; // 승인대기

    public Info_Mypage_CertificateInfo(int _serialNum, String _title, String _equip_list, String _progress_state) {
        this.serialNum = _serialNum;
        this.title = _title;
        this.equip_list = _equip_list;
        this.progress_state = _progress_state;
    }

    public int getSerialNum(){return this.serialNum;}
    public String getTitle(){
        return this.title;
    }
    public String getEquip_list(){
        return this.equip_list;
    }
    public String getProgress_state(){
        return this.progress_state;
    }

    public void setTitle(String new_title){
        this.title = new_title;
    }
    public void setEquip_list(String new_equip_list){
        this.equip_list = new_equip_list;
    }
    public void setProgress_state(String new_progress_state){
        this.progress_state = new_progress_state;
    }
}
