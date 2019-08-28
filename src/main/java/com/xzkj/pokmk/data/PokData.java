package com.xzkj.pokmk.data;

public class PokData {
    public String PokName;
    public PokDataClass dataIVS;
    public PokDataClass dataEVS;
    public PokData(String PokName, PokDataClass dataIVS, PokDataClass dataEVS){
        this.PokName = PokName;
        this.dataIVS = dataIVS;
        this.dataEVS = dataEVS;
    }
}
