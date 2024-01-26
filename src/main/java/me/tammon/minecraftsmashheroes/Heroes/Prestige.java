package me.tammon.minecraftsmashheroes.Heroes;

public enum Prestige {
    ZERO(100),
    ONE(102),
    TWO(105),
    THREE(109),
    FOUR(114),
    FIVE(120);

    private final int value;

    Prestige(final int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

}
