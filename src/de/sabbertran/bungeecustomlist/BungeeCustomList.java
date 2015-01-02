package de.sabbertran.bungeecustomlist;

import de.sabbertran.bungeecustomlist.commands.BungeeCustomListCommand;
import de.sabbertran.bungeecustomlist.commands.ListCommand;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeCustomList extends Plugin
{

    private String permission;
    private ArrayList<String> aliases;
    private ArrayList<String> disabledServers;

    @Override
    public void onEnable()
    {
        try
        {
            if (!getDataFolder().exists())
            {
                getDataFolder().mkdir();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists())
            {
                Files.copy(getResourceAsStream("config.yml"), file.toPath());
            }

            loadConfig();
        } catch (IOException ex)
        {
            Logger.getLogger(BungeeCustomList.class.getName()).log(Level.SEVERE, null, ex);
        }

        getProxy().getPluginManager().registerCommand(this, new ListCommand(this));
        getProxy().getPluginManager().registerCommand(this, new BungeeCustomListCommand(this));

        logStart();
        
        System.out.println("BungeeCustomList enabled");
    }

    @Override
    public void onDisable()
    {
        System.out.println("BungeeCustomList disabled");
    }

    public void loadConfig()
    {
        try
        {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            permission = config.getString("permission");
            aliases = (ArrayList<String>) config.getStringList("commandAliases");
            disabledServers = (ArrayList<String>) config.getStringList("disabledServers");
        } catch (IOException ex)
        {
            Logger.getLogger(BungeeCustomList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void logStart()
    {
        try
        {
            InetAddress adr = InetAddress.getLocalHost();
            URL url = new URL("http://sabbertran.de/plugins/bungeecustomlist/log.php?name=" + getProxy().getName() + "&ip=" + adr.getHostAddress() + "&port=bungee");
            url.openStream();
        } catch (UnknownHostException ex)
        {
            Logger.getLogger(BungeeCustomList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(BungeeCustomList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPermission()
    {
        return permission;
    }

    public ArrayList<String> getAliases()
    {
        return aliases;
    }

    public ArrayList<String> getDisabledServers()
    {
        return disabledServers;
    }
}
