package de.sabbertran.bungeecustomlist.commands;

import de.sabbertran.bungeecustomlist.BungeeCustomList;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCustomListCommand extends Command
{

    private BungeeCustomList main;

    public BungeeCustomListCommand(BungeeCustomList main)
    {
        super("bungeecustomlist", "bungeecustomlist.reload");
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload"))
        {
            main.loadConfig();
            sender.sendMessage("Config reloaded");
        }
    }
}
