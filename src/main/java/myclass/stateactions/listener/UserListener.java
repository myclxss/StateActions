package myclass.stateactions.listener;

import myclass.stateactions.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        StateManager.setPlayerState(player, StateManager.STATE_LOBBY);
        Bukkit.getConsoleSender().sendMessage("El usuario " + player.getName() + " se agrego al estado");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damager = (Player) event.getEntity();
            Player victim = (Player) event.getDamager();

            String damagerState = damager.getMetadata("state").get(0).asString();
            String victimState = victim.getMetadata("state").get(0).asString();

            if (!damagerState.equals(StateManager.STATE_PLAYING) || !victimState.equals(StateManager.STATE_PLAYING)) {
                damager.sendMessage("Estas en el estado lobby no puedes hacer pvp");
                victim.sendMessage("Estas en el estado lobby no puedes hacer pvp");
                event.setCancelled(true);
                return;
            }
            damager.sendMessage("Estas en el estado playing puedes hacer pvp");
            victim.sendMessage("Estas en el estado playing puedes hacer pvp");
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String state = player.getMetadata("state").get(0).asString();

        if (!state.equals(StateManager.STATE_PLAYING)) {
            player.sendMessage("No estas en el estado playing, no puedes romper");
            event.setCancelled(true);
        } else {
            player.sendMessage("Estas en el estado playing puedes romper");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        StateManager.removePlayerState(player);
        Bukkit.getConsoleSender().sendMessage("Usuario removido de su estado");
    }
}
