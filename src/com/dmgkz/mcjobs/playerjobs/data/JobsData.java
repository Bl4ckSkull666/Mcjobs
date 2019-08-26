package com.dmgkz.mcjobs.playerjobs.data;

import com.dmgkz.mcjobs.McJobs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.dmgkz.mcjobs.playerjobs.display.JobsDisplay;
import com.dmgkz.mcjobs.util.EnchantTypeAdv;
import com.dmgkz.mcjobs.util.PotionTypeAdv;
import com.dmgkz.mcjobs.util.MatClass;
import com.dmgkz.mcjobs.util.RegionPositions;
import com.dmgkz.mcjobs.util.SpigotMessage;
import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Player;


public class JobsData{
    private final CompData _compare;
    private final LoadJob _loadjob;
    private final JobsDisplay _display;
    
    private final HashMap<String, HashMap<Integer, ArrayList<MatClass>>> _hBlocksBPC = new HashMap<>();
    private final HashMap<String, HashMap<Integer, ArrayList<EntityType>>> _hBlocksK = new HashMap<>();
    private final HashMap<String, HashMap<Integer, ArrayList<PotionTypeAdv>>> _hPotions = new HashMap<>();
    private final HashMap<String, HashMap<Integer, ArrayList<EnchantTypeAdv>>> _hEnchants = new HashMap<>();
    private final HashMap<String, Boolean> _bTierPays = new HashMap<>();
    
    //Type like Break, Place, Craft... , ArrayList<Tool like pickaxt, sword....>
    private final HashMap<String, ArrayList<String>> _tools = new HashMap<>();
    
    protected boolean[] _bShow;
    protected boolean[] _bCP;
    protected boolean   _bShowEveryTime;
    protected boolean   _bDefaultJob;
    
    protected String _sJobName;
    
    protected double _dBasepay;
    protected double _exp_modifier;
    
    protected RegionPositions _jobInfoZone;
    protected List<String> _jobInfoZoneMessage = new ArrayList<>();
    protected SpigotMessage _spigotMessage;
    protected List<String> _signStringMessage = new ArrayList<>();
    protected SpigotMessage _signSpigotMessage;

    public JobsData() {
        _compare = new CompData(this);
        _loadjob = new LoadJob(this);
        _display = new JobsDisplay(this);
        
        _bShow = new boolean[8];
        _bCP   = new boolean[2];
        Arrays.fill(_bShow, false);
        Arrays.fill(_bCP, false);
        
        _bShowEveryTime = false;
        _bDefaultJob    = false;
        
        _sJobName     = "no_name";
        
        _dBasepay = 0.25D;
        _exp_modifier = 1D;
    }

    public boolean getShowEveryTime() {
        return _bShowEveryTime;
    }
    
    public Double getBasePay() {
        return _dBasepay;
    }
    
    public double getEXP() {
        return _exp_modifier;
    }
    
    public LoadJob loadJob() {
        return _loadjob;
    }
    
    public CompData compJob() {
        return _compare;
    }
        
    public JobsDisplay display() {
        return _display;
    }
    
    public HashMap<String, HashMap<Integer, ArrayList<MatClass>>> getMatHash() {
        return _hBlocksBPC;
    }

    public HashMap<String, HashMap<Integer, ArrayList<EntityType>>> getEntHash() {
        return _hBlocksK;
    }
    
    public HashMap<String, HashMap<Integer, ArrayList<PotionTypeAdv>>> getPotHash() {
        return _hPotions;
    }
    
    public HashMap<String, HashMap<Integer, ArrayList<EnchantTypeAdv>>> getEnchantHash() {
        return _hEnchants;
    }
    
    public HashMap<String, ArrayList<String>> getTools() {
        return _tools;
    }
    
    public String getName() {
        return _sJobName;
    }
    
    public String getName(UUID uuid) {
        return McJobs.getPlugin().getLanguage().getJobNameInLang(_sJobName, uuid);
    }
    
    public String getDesc(UUID uuid) {
        return McJobs.getPlugin().getLanguage().getJobDesc(_sJobName, uuid);
    }
    
    public boolean getCostPay(boolean isPay){
        if(isPay)
            return _bCP[0];
        else
            return _bCP[1];
    }
    public boolean getShow(int i){
        return _bShow[i];
    }

    public HashMap<String, Boolean> getTierPays() {
        return _bTierPays;
    }
    
    public RegionPositions getRegionPositions() {
        try {
            return _jobInfoZone;
        } catch(Exception ex) {
            return null;
        }
    }
    
    public void sendRegionMessage(Player p) {
        try {
            if(_spigotMessage != null)
                _spigotMessage.sendMessage(p);
            else {
                for(String msg: _jobInfoZoneMessage)
                    p.sendMessage(msg);
            }
        } catch(Exception ex) {
            for(String msg: _jobInfoZoneMessage)
                p.sendMessage(msg);
        }
    }
    
    public void sensSignMessage(Player p) {
        try {
            if(_signSpigotMessage != null)
                _signSpigotMessage.sendMessage(p);
            else {
                for(String msg: _signStringMessage)
                    p.sendMessage(msg);
            }
        } catch(Exception ex) {
            for(String msg: _jobInfoZoneMessage)
                p.sendMessage(msg);
        }
    }
}