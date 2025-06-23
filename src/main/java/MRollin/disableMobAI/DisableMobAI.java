package MRollin.disableMobAI;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;
import java.util.logging.Logger;

public final class DisableMobAI extends JavaPlugin implements Listener{

    private List<String> DisableMobAIWorlds;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().enablePlugin(this);
        getConfig().getStringList("DisableMobAi-Worlds");

        getLogger().info("World Where MobAI has been Disable");
        for (int i = 0; i < DisableMobAIWorlds.size(); i++) {
            getLogger().info(DisableMobAIWorlds.get(i));
        }
        getLogger().info("DisableMobAI enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getPluginManager().disablePlugin(this);
        getLogger().info("DisableMobAI Disable");
    }

    // Disables AI for mobs on spawn
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event){
        World world = event.getLocation().getWorld();
        if(DisableMobAIWorlds.contains(world.getName())){
            LivingEntity mob = event.getEntity();
            mob.setAI(false);
            getLogger().info(mob.toString() + "'s AI as been disabled");
        }

    }
}
