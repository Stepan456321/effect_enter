import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION, 0);

        player.spawnParticle(Particle.HEART, player.getLocation(), 100);

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count >= 10) {
                    cancel();
                    return;
                }

                Location loc = player.getLocation().add(0, 1, 0);
                player.getWorld().spawnFallingBlock(loc, Material.GLOWSTONE, (byte) 0);
                count++;
            }
        }.runTaskTimer(this, 0, 10);
    }
}
