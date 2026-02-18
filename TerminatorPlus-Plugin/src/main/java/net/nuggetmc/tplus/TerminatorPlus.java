package net.nuggetmc.tplus;

import net.nuggetmc.tplus.api.TerminatorPlusAPI;
import net.nuggetmc.tplus.bot.BotManagerImpl;
import net.nuggetmc.tplus.bridge.InternalBridgeImpl;
import net.nuggetmc.tplus.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class TerminatorPlus extends JavaPlugin {

    public static final String COMPILED_VERSION = "1.21.1";

    private static TerminatorPlus instance;
    private static String version;
    private static String mcVersion;

    private static boolean compatibleVersion;

    private BotManagerImpl manager;
    private CommandHandler handler;

    public static TerminatorPlus getInstance() {
        return instance;
    }

    public static String getVersion() {
        return version;
    }

    public static boolean isCompatibleVersion() {
        return compatibleVersion;
    }

    public static String getMcVersion() {
        return mcVersion;
    }

    public BotManagerImpl getManager() {
        return manager;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    @Override
    public void onEnable() {
        instance = this;
        version = getDescription().getVersion();

        mcVersion = Bukkit.getServer().getMinecraftVersion();
        compatibleVersion = checkVersionCompatibility(mcVersion);
        getLogger().info("Running on version: " + mcVersion + ", compiled for: " + COMPILED_VERSION + ", compatible: " + compatibleVersion);

        // Create Instances
        this.manager = new BotManagerImpl();
        this.handler = new CommandHandler(this);

        TerminatorPlusAPI.setBotManager(manager);
        TerminatorPlusAPI.setInternalBridge(new InternalBridgeImpl());

        // Register event listeners
        this.registerEvents(manager);

        if (!compatibleVersion) {
            getLogger().warning("----------------------------------------");
            getLogger().warning("TerminatorPlus was compiled for " + COMPILED_VERSION + " but is running on " + mcVersion + ".");
            getLogger().warning("Some features may not work correctly. For best results, use a matching server version.");
            getLogger().warning("The plugin will still attempt to load and function.");
            getLogger().warning("----------------------------------------");
        }
    }

    @Override
    public void onDisable() {
        manager.reset();
    }

    private void registerEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(li -> this.getServer().getPluginManager().registerEvents(li, this));
    }

    /**
     * Check if the running server version is compatible with this plugin build.
     * Accepts exact match or same major.minor version (e.g., 1.21.x).
     * Pre-release identifiers (e.g., 1.21-pre1) are stripped before comparison.
     */
    private static boolean checkVersionCompatibility(String serverVersion) {
        if (serverVersion.equals(COMPILED_VERSION)) return true;

        // Strip pre-release identifiers (e.g., "1.21.1-pre1" -> "1.21.1")
        String cleanServer = serverVersion.split("-")[0];
        String cleanCompiled = COMPILED_VERSION.split("-")[0];

        String[] serverParts = cleanServer.split("\\.");
        String[] compiledParts = cleanCompiled.split("\\.");

        if (serverParts.length >= 2 && compiledParts.length >= 2) {
            return serverParts[0].equals(compiledParts[0]) && serverParts[1].equals(compiledParts[1]);
        }

        return false;
    }
}
