package com.xzkj.pokmk.data;

public class ShopPok {
    public String PlayerName;
    public String PokName;
    public String PokUUID;
    public String PokEC;
    public PokData PokData;
    public ShopPok(String PlayerName, String PokName, String PokUUID, String PokEC, PokData PokData){
        this.PlayerName =PlayerName;
        this.PokName = PokName;
        this.PokUUID = PokUUID;
        this.PokEC = PokEC;
        this.PokData = PokData;
    }
}
