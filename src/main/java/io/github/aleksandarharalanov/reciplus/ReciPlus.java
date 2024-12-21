package io.github.aleksandarharalanov.reciplus;

import io.github.aleksandarharalanov.reciplus.command.ReciPlusCommand;
import io.github.aleksandarharalanov.reciplus.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.aleksandarharalanov.reciplus.handler.RecipeHandler.initializeRecipes;
import static io.github.aleksandarharalanov.reciplus.util.LoggerUtil.logInfo;
import static io.github.aleksandarharalanov.reciplus.util.UpdateUtil.checkForUpdates;

public class ReciPlus extends JavaPlugin {

    public static ConfigUtil shaped;
    public static ConfigUtil shapeless;
    public static ConfigUtil furnace;

    @Override
    public void onEnable() {
        checkForUpdates(this, "https://api.github.com/repos/AleksandarHaralanov/ReciPlus/releases/latest");

        shaped = new ConfigUtil(this, "recipes/shaped.yml");
        shaped.loadConfig();
        shapeless = new ConfigUtil(this, "recipes/shapeless.yml");
        shapeless.loadConfig();
        furnace = new ConfigUtil(this, "recipes/furnace.yml");
        furnace.loadConfig();

        initializeRecipes(getDescription());

        final ReciPlusCommand command = new ReciPlusCommand(this);
        getCommand("reciplus").setExecutor(command);

        logInfo(String.format("[%s] v%s Enabled.", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onDisable() {
        logInfo(String.format("[%s] v%s Disabled.", getDescription().getName(), getDescription().getVersion()));
    }

    public static ConfigUtil getShaped() {
        return shaped;
    }

    public static ConfigUtil getShapeless() {
        return shapeless;
    }

    public static ConfigUtil getFurnace() {
        return furnace;
    }
}
