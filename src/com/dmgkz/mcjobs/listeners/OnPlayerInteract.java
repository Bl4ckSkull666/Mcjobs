/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmgkz.mcjobs.listeners;

import com.dmgkz.mcjobs.McJobs;
import com.dmgkz.mcjobs.commands.jobs.SubCommandInfo;
import com.dmgkz.mcjobs.commands.jobs.SubCommandJoin;
import com.dmgkz.mcjobs.commands.jobs.SubCommandLeave;
import com.dmgkz.mcjobs.util.JobSign;
import org.bukkit.block.Sign;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Bl4ckSkull666
 */
public class OnPlayerInteract implements Listener {
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getClickedBlock() != null) {
            if(e.getClickedBlock() instanceof Sign && McJobs.getPlugin().getSignManager().isSign(e.getClickedBlock().getLocation())) {
                JobSign js = McJobs.getPlugin().getSignManager().getJobSign(e.getClickedBlock().getLocation());
                switch(js.getSignType()) {
                    case JOIN:
                        SubCommandJoin.command(e.getPlayer(), new String[] {"join", js.getJob()});
                        break;
                    case LEAVE:
                        SubCommandLeave.command(e.getPlayer(), new String[] {"leave", js.getJob()});
                        break;
                    case INFO:
                        SubCommandInfo.command(e.getPlayer(), new String[] {"info", js.getJob()});
                        break;
                }
            }
        }
    }
}