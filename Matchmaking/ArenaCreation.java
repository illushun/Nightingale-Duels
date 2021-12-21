public class ArenaCreation {
    public static Map<String, String> arenas = Maps.newHashMap();
    public static Map<String, Location> arenaLocation = Maps.newHashMap();

    private String arenaName;

    public ArenaCreation() {}

    public ArenaCreation(String arenaName) {
        this.arenaName = arenaName;
    }

    public void CreateArena(Player ply) {
        if (arenas.containsKey(arenaName)) {
            return;
        }

        GetArenas().put(arenaName, ply.getName());
    }

    public boolean SetArenaLocation(String position, Location location) {
        if (!GetArenas().containsKey(arenaName)) {
            System.out.println("There is no arena named " + arenaName + ".");
            return false;
        }

        switch (position) {
            case "firstPos":
                GetArenaLocations().put(arenaName + "_firstPos", location);
                return true;
            case "secondPos":
                GetArenaLocations().put(arenaName + "_secondPos", location);
                return true;
        }

        System.out.println("Position needs to be either 'firstPos' or 'secondPos'.");
        return false;
    }

    public Map<String, String> GetArenas() {
        return arenas;
    }

    public Map<String, Location> GetArenaLocations() {
        return arenaLocation;
    }

    public String GetArenaCreator() {
        if (!GetArenas().containsKey(arenaName)) {
            return null;
        }

        return GetArenas().get(arenaName);
    }

    public String GetArenaLocation() {
        if (!GetArenas().containsKey(arenaName)) {
            return null;
        }

        return "firstPos: " + GetArenaLocations().get(arenaName + "_firstPos") + " secondPos: " + arenaLocation.get(arenaName + "_secondPos");
    }

    public Location GetArenaLocation(String position) {
        if (!GetArenas().containsKey(arenaName)) {
            return null;
        }

        switch (position) {
            case "_firstPos":
                return GetArenaLocations().get(arenaName + "_firstPos");
            case "_secondPos":
                return GetArenaLocations().get(arenaName + "_secondPos");
            default:
        }
        return null;
    }
}
