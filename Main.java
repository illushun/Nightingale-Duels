public final class Main extends JavaPlugin {
    private Economy eco;
    public static Main instance = null;
    public static CreateConfig config = null;

    @Override
    public void onEnable() {
        instance = this;
        Class.loadClasses();
       if (!setupEconomy()) {
            this.getLogger().severe("Plugin disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
       }
       config = new CreateConfig("plugins/Duels", "config.yml", instance);
        getCommand("nightarena").setExecutor(new DuelCommands());
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    public Economy getEconomy() {
        return eco;
    }
}
