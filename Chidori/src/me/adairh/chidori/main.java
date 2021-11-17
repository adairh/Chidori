package me.adairh.chidori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import de.slikey.effectlib.EffectManager;
import net.md_5.bungee.api.ChatColor;


public class main extends JavaPlugin implements Listener{
	private EffectManager effectManager;
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        effectManager = new EffectManager(this);

    }
    private static List<UUID> cooldown = new ArrayList<UUID>();
	
	  public Boolean isPLayerHasCooldown(UUID u)
	  {
	    Boolean c = Boolean.valueOf(false);
	    for (UUID uo : cooldown) {
	      if (uo.equals(u))
	      {
	        c = Boolean.valueOf(true);
	        break;
	      }
	    }
	    return c;
	  }
	  private void createNewCooldownCache(final UUID uuid)
	  {
	    cooldown.add(uuid);
	    new BukkitRunnable()
	    {
	      public void run()
	      {
	        main.cooldown.remove(uuid);
	      }
	    }
	    
	      .runTaskLaterAsynchronously(this, 20 * 20);
	  }
    @Override
    public void onDisable() {
        // Dispose of the EffectManager
        effectManager.dispose();
        HandlerList.unregisterAll((Listener) this);
    }
	List<String> chidori = new ArrayList<String>();
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChidori(PlayerToggleSneakEvent e){
		Player p = e.getPlayer();
		if (p.hasPermission("chidori.use")){
			
			if (p.getItemInHand().getType() == Material.AIR){
				
				if (!isPLayerHasCooldown(p.getUniqueId()).booleanValue()){
				createNewCooldownCache(p.getUniqueId());
				chidori.add(p.getName());
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,200,4));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,4));
				chidori2 bleedEffect2 = new chidori2(effectManager);
		        bleedEffect2.setEntity(p);
		        bleedEffect2.iterations = 4 * 30;
		        bleedEffect2.start();
				chidori bleedEffect = new chidori(effectManager);
		        bleedEffect.setEntity(p);
		        bleedEffect.iterations = 3 * 20;
		        bleedEffect.start();
				Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable(){
					public void run(){
						chidori.remove(p.getName());
					}
				},6*20);
				
			}
		}
			else{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lBạn cần chờ đủ &c&l20 giây&6&l để hồi lại sức"));
			}
	}
	}
	HashMap<String,Location> chidori2 = new HashMap<String,Location>();
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChidiri2(EntityDamageByEntityEvent e){
		Entity dmg = e.getDamager();
		Entity en = e.getEntity();
		if (dmg instanceof Player){
			if(chidori.contains(dmg.getName())){
				if (!en.hasMetadata("shopkeeper") && !en.hasMetadata("NPC")
						   && (en.getType() != EntityType.ARMOR_STAND)){
				e.setDamage(10);
				en.getLocation().getWorld().strikeLightning(en.getLocation());
				((Damageable) en).damage(4);
				((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,200,6));
				chidori2.put(en.getName(), en.getLocation());
				int a = Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
					public void run(){
						en.teleport(chidori2.get(en.getLocation()));
					}
				},1,1);
				Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable(){
					public void run(){
						Bukkit.getScheduler().cancelTask(a);
						chidori2.remove(en.getName());
					}
				},100);
				}
			}
		}
	}
	@EventHandler
	public void onChidori3(EntityDamageEvent e){
		Entity p = e.getEntity();
		if (chidori.contains(p.getName())){
			if(e.getCause().equals(DamageCause.LIGHTNING)){
				
				e.setCancelled(true);
				
			}
		}
	}
	
}
