package club.therepo;

import org.bukkit.plugin.java.JavaPlugin;

public class ApproveRules extends JavaPlugin {
    public static ApproveRulesPlugin plugin;

    @Override
    public void onEnable(){
        //Fired when the server starts and enables all plugins
        plugin = new ApproveRulesPlugin(this);
        plugin.enable();

    }



    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
        plugin.disable();
    }
}
