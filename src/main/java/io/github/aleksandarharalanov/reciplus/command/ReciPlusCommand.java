package io.github.aleksandarharalanov.reciplus.command;

import io.github.aleksandarharalanov.reciplus.ReciPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.aleksandarharalanov.reciplus.ReciPlus.*;
import static io.github.aleksandarharalanov.reciplus.handler.RecipeHandler.initializeRecipes;
import static io.github.aleksandarharalanov.reciplus.util.AboutUtil.about;
import static io.github.aleksandarharalanov.reciplus.util.AccessUtil.hasPermission;
import static io.github.aleksandarharalanov.reciplus.util.ColorUtil.translate;
import static io.github.aleksandarharalanov.reciplus.util.LoggerUtil.logInfo;

public class ReciPlusCommand implements CommandExecutor {

    private final ReciPlus plugin;

    public ReciPlusCommand(ReciPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("reciplus") && !command.getName().equalsIgnoreCase("rp"))
            return true;

        if (args.length == 0) {
            helpCommand(sender);
            return true;
        }

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "about":
                    about(sender, plugin);
                    break;
                case "reload":
                    reloadCommand(sender);
                    break;
                default:
                    helpCommand(sender);
            }
            return true;
        }

        helpCommand(sender);
        return true;
    }

    private static void helpCommand(CommandSender sender) {
        String[] messages = {
                "&bReciPlus commands:",
                "&e/rp &7- Displays this message.",
                "&e/rp about &7- About ReciPlus.",
                "&bReciPlus staff commands:",
                "&e/rp reload &7- Reload ReciPlus configuration."
        };

        for (String message : messages) {
            if (sender instanceof Player) sender.sendMessage(translate(message));
            else logInfo(message.replaceAll("&.", ""));
        }
    }

    private void reloadCommand(CommandSender sender) {
        if (!hasPermission(sender, "reciplus.config", "You don't have permission to reload the ReciPlus configuration.")) return;

        getShaped().loadConfig();
        getShapeless().loadConfig();
        getFurnace().loadConfig();
        initializeRecipes(plugin.getDescription());

        if (sender instanceof Player) sender.sendMessage(translate("&aReciPlus configuration reloaded."));
    }
}
