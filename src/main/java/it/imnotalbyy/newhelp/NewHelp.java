package it.imnotalbyy.newhelp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class NewHelp extends JavaPlugin implements Listener {

    private FileConfiguration helpConfig;
    private File helpFile;
    private String prefix;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("help").setExecutor(this);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RESET + "[NewHelp] " + ChatColor.GREEN + "By ImNotAlbyy");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RESET + "[NewHelp] " + ChatColor.GREEN + "Plugin Enabled!");
        helpFile = new File(getDataFolder(), "help.yml");
        helpConfig = YamlConfiguration.loadConfiguration(helpFile);

        if (!helpFile.exists()) {
            saveResource("help.yml", false);
        }
        int pluginId = 22255;
        Metrics metrics = new Metrics(this, pluginId);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("help")) {
            showHelp(sender);
            return true;
        }
        return false;
    }

    private String formatMessage(String message) {
        message = message.replace("&", "§");
        return message;
    }
    private void showHelp(CommandSender sender) {
        // Carica e invia i messaggi di aiuto
        if (helpConfig.contains("messages")) {
            List<String> messages = helpConfig.getStringList("messages");
            for (String message : messages) {
                message = ChatColor.translateAlternateColorCodes('&', message);
                sender.sendMessage(message);
            }
        }
    }
}