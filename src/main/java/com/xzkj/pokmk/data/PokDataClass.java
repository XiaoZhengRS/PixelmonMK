package com.xzkj.pokmk.data;

public class PokDataClass {
    public int attack;
    public int speed;
    public int defence;
    public int specialDefence;
    public int specialAttack;
    public int hp;
    public int level;
    public PokDataClass(int attack, int defence, int specialAttack, int specialDefence, int speed, int hp, int level) {
        this.attack = attack;
        this.defence = defence;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
        this.speed = speed;
        this.hp = hp;
        this.level = level;
    }
}
