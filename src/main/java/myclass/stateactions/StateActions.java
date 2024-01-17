package myclass.stateactions;

import org.bukkit.plugin.java.JavaPlugin;

public final class StateActions extends JavaPlugin {


    @Override
    public void onEnable() {

        new API(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
