package me.tammon.minecraftsmashheroes.Heroes;

public enum HeroNameEnum {
    BULK("Bulk"),
    GENERAL_CLUCK("General Cluck"),
    CAKE_MONSTER("Cake Monster"),
    BOTMON("Botmon"),
    TINMAN("Tinman"),
    SGT_SHIELD("Sgt. Shield"),
    CRYOMANCER("Cryomancer"),
    SKULLFIRE("Skullfire"),
    SANIC("Sanic"),
    KARAKOT("Karakot"),
    PUG("Pug"),
    SPOODERMAN("Spooderman"),
    SHOOP("Shoop"),
    GREEN_HOOD("Green Hood"),
    VOID_CRAWLER("Void Crawler");

    private final String value;

    HeroNameEnum(final String value){
        this.value = value;
    }

    public String get_clean_name(){
        return this.value;
    }
}
