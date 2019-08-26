package com.dmgkz.mcjobs.listeners;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import com.dmgkz.mcjobs.playerdata.CompCache;
import com.dmgkz.mcjobs.playerdata.PlayerCache;
import com.dmgkz.mcjobs.playerdata.PlayerData;
import com.dmgkz.mcjobs.playerjobs.PlayerJobs;
import com.dmgkz.mcjobs.playerjobs.data.CompData;
import org.bukkit.GameMode;


public class Enchanting implements Listener{

    @EventHandler(priority = EventPriority.LOW)
    public void getEnchantments(EnchantItemEvent event){
        if(event.isCancelled())
            return;
        
        Player play = event.getEnchanter();
        Map<Enchantment, Integer> enchantments = event.getEnchantsToAdd();

        
        if(MCListeners.isMultiWorld()){
            if(!play.hasPermission("mcjobs.world.all") && !play.hasPermission("mcjobs.world." + play.getWorld().getName()))
                return;
        }
                
                if(play.getGameMode() == GameMode.CREATIVE){
            if(!play.hasPermission("mcjobs.paycreative"))
                return;
        }
        
        for(Map.Entry<String, PlayerJobs> pair: PlayerJobs.getJobsList().entrySet()) {
            String sJob = pair.getKey();

            if(PlayerData.hasJob(play.getUniqueId(), sJob)){
                CompCache comp = new CompCache(sJob, play.getLocation(), play, enchantments, "enchant");
                CompData.getCompCache().add(comp);
        
//                if(PlayerJobs.joblist.get(sJob).getData().compJob().compEnchant(enchantments, play, "enchant")){        
//                    play.sendMessage("You enchanted " + ChatColor.DARK_GREEN + enchantments.keySet().toString() + ChatColor.WHITE + ".");
//                }
            }
        }        
    }
}