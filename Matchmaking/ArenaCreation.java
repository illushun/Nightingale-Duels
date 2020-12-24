public class ArenaCreation {
    public static Map<String, String> arenas = Maps.newHashMap();
    public static Map<String, Location> arenaLocation = Maps.newHashMap();

    public static String getArenaCreator(String name) {
        if (!arenas.containsKey(name)) {
            return null;
        }

        return arenas.get(name);
    }

    public static String getArenaLocation(String name) {
        if (!arenas.containsKey(name)) {
            return null;
        }

        return "firstPos: " + arenaLocation.get(name + "_firstPos") + " secondPos: " + arenaLocation.get(name + "_secondPos");
    }

    public static Location getArenaLocation(String name, String position) {
        if (!arenas.containsKey(name)) {
            return null;
        }

        switch (position) {
            case "_firstPos":
                return arenaLocation.get(name + "_firstPos");
            case "_secondPos":
                return arenaLocation.get(name + "_secondPos");
            default:
        }
        return null;
    }

    public static void createArena(Player ply, String name) {
        if (arenas.containsKey(name)) {
            return;
        }

        arenas.put(name, ply.getName());
    }

    public static boolean setArenaLocation(String name, String position, Location location) {
        if (!arenas.containsKey(name)) {
            System.out.println("There is no arena named " + name + ".");
            return false;
        }

        switch (position) {
            case "firstPos":
                arenaLocation.put(name + "_firstPos", location);
                return true;
            case "secondPos":
                arenaLocation.put(name + "_secondPos", location);
                return true;
        }

        System.out.println("Position needs to be either 'firstPos' or 'secondPos'.");
        return false;
    }
}
