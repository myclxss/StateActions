package myclass.stateactions;

import myclass.stateactions.command.FirstCommand;
import myclass.stateactions.listener.UserListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class API {

    private static API instance;
    private final StateActions main;

    public API(final StateActions plugin) {

        instance = this;
        main = plugin;

        loadCommand();
        loadListener();
    }

    public void loadCommand() {
        main.getCommand("state").setExecutor(new FirstCommand());
    }

    public void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new UserListener(), main);
    }

    public StateActions getMain() {
        return main;
    }

    public static API getInstance() {
        return instance;
    }
}
