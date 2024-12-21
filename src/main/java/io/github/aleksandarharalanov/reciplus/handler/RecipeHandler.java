package io.github.aleksandarharalanov.reciplus.handler;

import org.bukkit.Bukkit;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.List;

import static io.github.aleksandarharalanov.reciplus.ReciPlus.*;
import static io.github.aleksandarharalanov.reciplus.util.LoggerUtil.logInfo;

public class RecipeHandler {

    public static void initializeRecipes(PluginDescriptionFile pdf) {
        shapedRecipes(pdf);
        shapelessRecipes(pdf);
        furnaceRecipes(pdf);
    }

    private static void shapedRecipes(PluginDescriptionFile pdf) {
        List<String> shaped = getShaped().getKeys("shaped");
        if (shaped != null) {
            for (int i = 0; i < shaped.size(); i++) {
                String resultEntry = getShaped().getString(String.format("shaped.r%d.result.id", i + 1));
                String[] resultIdData = resultEntry.contains(":") ? resultEntry.split(":") : new String[]{resultEntry, "0"};
                int resultId = Integer.parseInt(resultIdData[0]);
                byte resultData = Byte.parseByte(resultIdData[1]);
                int resultAmount = getShaped().getInt(String.format("shaped.r%d.result.amount", i + 1), 1);
                ItemStack result = new ItemStack(resultId, resultAmount, resultData);

                ShapedRecipe shapedRecipe = new ShapedRecipe(result);
                List<String> rows = getShaped().getStringList(String.format("shaped.r%d.grid", i + 1), new ArrayList<>());
                String[] grid = rows.toArray(new String[0]);
                shapedRecipe.shape(grid[0], grid[1], grid[2]);

                List<String> ingredients = getShaped().getStringList(String.format("shaped.r%d.ingredients", i + 1), new ArrayList<>());
                for (String entry : ingredients) {
                    String[] ingredientCharMat = entry.split(";");
                    char key = ingredientCharMat[0].charAt(0);
                    String[] ingredientIdData = ingredientCharMat[1].contains(":") ? ingredientCharMat[1].split(":") : new String[]{ingredientCharMat[1], "0"};
                    int ingredientId = Integer.parseInt(ingredientIdData[0]);
                    byte ingredientData = Byte.parseByte(ingredientIdData[1]);
                    shapedRecipe.setIngredient(key, new MaterialData(ingredientId, ingredientData));
                }

                Bukkit.getServer().addRecipe(shapedRecipe);
            }
        }
        logRecipeCount(pdf.getName(), "Shaped", shaped);
    }

    private static void shapelessRecipes(PluginDescriptionFile pdf) {
        List<String> shapeless = getShapeless().getKeys("shapeless");
        if (shapeless != null) {
            for (int i = 0; i < shapeless.size(); i++) {
                String resultEntry = getShapeless().getString(String.format("shapeless.r%d.result.id", i + 1));
                String[] resultIdData = resultEntry.contains(":") ? resultEntry.split(":") : new String[]{resultEntry, "0"};
                int resultId = Integer.parseInt(resultIdData[0]);
                byte resultData = Byte.parseByte(resultIdData[1]);
                int resultAmount = getShapeless().getInt(String.format("shapeless.r%d.result.amount", i + 1), 1);
                ItemStack result = new ItemStack(resultId, resultAmount, resultData);

                ShapelessRecipe shapelessRecipe = new ShapelessRecipe(result);

                List<String> ingredients = getShapeless().getStringList(String.format("shapeless.r%d.ingredients", i + 1), new ArrayList<>());
                for (String entry : ingredients) {
                    String[] ingredientIdData = entry.contains(":") ? entry.split(":") : new String[]{entry, "0"};
                    int ingredientId = Integer.parseInt(ingredientIdData[0]);
                    byte ingredientData = Byte.parseByte(ingredientIdData[1]);
                    shapelessRecipe.addIngredient(1, new MaterialData(ingredientId, ingredientData));
                }

                Bukkit.getServer().addRecipe(shapelessRecipe);
            }
        }
        logRecipeCount(pdf.getName(), "Shapeless", shapeless);
    }

    private static void furnaceRecipes(PluginDescriptionFile pdf) {
        List<String> furnace = getFurnace().getKeys("furnace");
        if (furnace != null) {
            for (int i = 0; i < furnace.size(); i++) {
                String resultEntry = getFurnace().getString(String.format("furnace.r%d.result.id", i + 1));
                String[] resultIdData = resultEntry.contains(":") ? resultEntry.split(":") : new String[]{resultEntry, "0"};
                int resultId = Integer.parseInt(resultIdData[0]);
                byte resultData = Byte.parseByte(resultIdData[1]);
                int resultAmount = getFurnace().getInt(String.format("furnace.r%d.result.amount", i + 1), 1);
                ItemStack result = new ItemStack(resultId, resultAmount, resultData);

                String sourceEntry = getFurnace().getString(String.format("furnace.r%d.source", i + 1));
                String[] sourceIdData = sourceEntry.contains(":") ? sourceEntry.split(":") : new String[]{sourceEntry, "0"};
                int sourceId = Integer.parseInt(sourceIdData[0]);
                byte sourceData = Byte.parseByte(sourceIdData[1]);

                FurnaceRecipe furnaceRecipe = new FurnaceRecipe(result, new MaterialData(sourceId, sourceData));

                Bukkit.getServer().addRecipe(furnaceRecipe);
            }
        }
        logRecipeCount(pdf.getName(), "Furnace", furnace);
    }

    private static void logRecipeCount(String pluginName, String recipeType, List<String> recipes) {
        int count = recipes == null ? 0 : recipes.size();
        logInfo(String.format("[%s] Loaded %d %s recipe(s).", pluginName, count, recipeType));
    }
}