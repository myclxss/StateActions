package myclass.stateactions.manager;

import myclass.stateactions.API;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class StateManager {

    public static void setPlayerState(Player player, String state) {
        player.setMetadata("state", new FixedMetadataValue(API.getInstance().getMain(), state));
    }

    public static String getPlayerState(Player player) {
        return player.getMetadata("state").get(0).asString();
    }

    public static void removePlayerState(Player player) {
        player.removeMetadata("state", API.getInstance().getMain());
    }

    public static final String STATE_LOBBY = "lobby";
    public static final String STATE_PLAYING = "playing";

}
