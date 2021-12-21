public class MatchSearch {
    public static ArrayList<String> playersQueued = new ArrayList<>();
    public static Map<Player, Player> playersInMatch = Maps.newHashMap();
    public static Map<String, Boolean> mapTaken = Maps.newHashMap();

    private Player ply;

    public MatchSearch() {}

    public MatchSearch(Player ply) {
        this.ply = ply;
    }

    public static boolean PlayerInQueue(String name) {
        return playersQueued.contains(name);
    }

    public static void LoadMaps() {
        if (ArenaCreation.arenas.isEmpty()) {
            return;
        }

        for (String maps : ArenaCreation.arenas.keySet()) {
            mapTaken.put(maps, false);
        }
    }

    public static boolean MapTaken(String name) {
        if (!mapTaken.containsKey(name)) {
            return true;
        }

        return mapTaken.get(name);
    }

    public boolean SearchForMatch() {
        if (PlayerInQueue(ply.getName())) {
            return false;
        }

        playersQueued.add(ply.getName());
        return true;
    }

    public boolean StopSearch() {
        if (!PlayerInQueue(ply.getName())) {
            return false;
        }

        playersQueued.remove(ply.getName());
        return true;
    }

    public boolean MatchPlayers() {
        boolean canFindPlayers;

        while (true) {
            canFindPlayers = playersQueued.size() >= 2;

            if (canFindPlayers) {
                Random rand = new Random();
                int getPlayer = rand.nextInt((playersQueued.size() - 1) + 1);

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (playersQueued.get(getPlayer) != null) {
                        String getPlayerName = playersQueued.get(getPlayer);
                        if (onlinePlayer.getName().equalsIgnoreCase(getPlayerName) && !getPlayerName.equalsIgnoreCase(ply.getName())) {
                            playersInMatch.put(ply, onlinePlayer);
                            playersQueued.remove(ply.getName());
                            playersQueued.remove(onlinePlayer.getName());
                            return true;
                        }
                    }
                }
            }
        }
    }

    public boolean TeleportPlayers(String arenaName) {
        LoadMaps();

        if (MapTaken(arenaName)) {
            return false;
        }

        if (!playersInMatch.containsKey(ply) || !playersInMatch.containsValue(ply)) {
            return false;
        }

        Player getOpponent = playersInMatch.get(ply);
        new ArenaCreation("");
        Location firstPos = new ArenaCreation(arenaName).GetArenaLocation("_firstPos");
        Location secondPos = new ArenaCreation(arenaName).GetArenaLocation("_secondPos");
        if (firstPos != null && secondPos != null) {
            ply.teleport(firstPos);
            getOpponent.teleport(secondPos);
            // need to clear and save old inv
            if (new KitItems(ply, arenaName).GiveKitItems()) {
                ply.sendMessage(ChatColor.GREEN + "Added kit " + arenaName + " to your inventory.");
            }
            else {
                ply.sendMessage(ChatColor.RED + "Unable to give you kit " + arenaName + "! (Check console)");
            }
        }
        return true;
    }
}
