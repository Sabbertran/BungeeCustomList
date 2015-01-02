package de.sabbertran.bungeecustomlist.commands;

import de.sabbertran.bungeecustomlist.BungeeCustomList;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ListCommand extends Command
{

    private BungeeCustomList main;

    public ListCommand(BungeeCustomList main)
    {
        super("list", main.getPermission(), main.getAliases().toArray(new String[main.getAliases().size()]));
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        int online = 0;
        for (ServerInfo s : main.getProxy().getServers().values())
        {
            if (!main.getDisabledServers().contains(s.getName()))
            {
                String players = "";
                for (ProxiedPlayer p : s.getPlayers())
                {
                    players = players + p.getName() + ", ";
                }
                if (players.length() >= 2)
                {
                    players = players.substring(0, players.length() - 2);
                }
                sender.sendMessage(ChatColor.GREEN + "[" + s.getName() + "] " + ChatColor.YELLOW + "(" + s.getPlayers().size() + "): " + ChatColor.RESET + players);
                online = online + s.getPlayers().size();
            }
        }
        sender.sendMessage("Total players online: " + online);
    }

}
