package myclass.stateactions.command;

import myclass.stateactions.manager.StateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FirstCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Estas en el estado " + StateManager.getPlayerState(player));
            return true;
        }
        if (args[0].equals("playing")) {
            player.sendMessage("Cambiaste al estado playing");
            StateManager.setPlayerState(player, StateManager.STATE_PLAYING);
            return true;
        }
        return false;
    }
}
