package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ItemLoadingBar {
    private final ItemStack itemTimer;
    private final Material readyState;
    private final long duration;

    private boolean isReady = false;
    private BukkitTask bukkitTask;
    private final int maxDurability;
    private final int slot;

    public ItemLoadingBar(Material loadingState, int maxDurability, Material readyState, long duration, int slot){
        this.readyState = readyState;
        this.duration = duration;

        this.maxDurability = maxDurability;
        this.itemTimer = new ItemStack(loadingState);
        this.slot = slot;
    }

    public ItemLoadingBar(Material loadingState, Material readyState, long duration, int slot){
        this.readyState = readyState;
        this.duration = duration;

        this.maxDurability = loadingState.getMaxDurability();
        this.itemTimer = new ItemStack(loadingState);
        this.slot = slot;
    }

    public void start(Player player){
        bukkitTask = new BukkitRunnable(){
            long ticksElapsed = 0;
            final float incrementAmount = (float) maxDurability / duration;

            @Override
            public void run() {
                ItemStack timer = player.getInventory().getItem(slot);
                if (timer == null){
                    this.cancel();
                } else {
                    if (ticksElapsed >= duration){
                        timer.setType(readyState);
                        isReady = true;
                        this.cancel();
                    }

                    Damageable meta = (Damageable) timer.getItemMeta();

                    assert meta != null;
                    meta.setDamage(maxDurability - (int)(ticksElapsed * incrementAmount));
                    timer.setItemMeta(meta);
                    ticksElapsed++;
                }
            }
        }.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0L, 1L);
    }

    public void forceFinish(Player player){
        this.stop();

        isReady = true;
        ItemStack timer = player.getInventory().getItem(this.slot);

        if (timer != null)
            timer.setType(this.readyState);
    }

    public void reset(Player player){
        this.stop();
        player.getInventory().setItem(this.slot, this.itemTimer);
        this.isReady = false;
    }

    public void restart(Player player){
        stop();
        start(player);
    }

    public void stop(){
        if (bukkitTask != null && !bukkitTask.isCancelled())
            bukkitTask.cancel();
    }

    public ItemStack getItemTimer(){
        return this.itemTimer;
    }

    public boolean isReady(){
        return this.isReady;
    }
}

