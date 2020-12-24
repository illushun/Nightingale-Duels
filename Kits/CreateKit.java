public class CreateKit {
    public static ArrayList<String> arenaKits = new ArrayList<>();

    public static boolean createKit(String arenaName) {
        if (arenaKits.contains(arenaName) || !ArenaCreation.arenas.containsKey(arenaName)) {
            System.out.println("There is either already a kit called " + arenaName + ", or there is no arena named " + arenaName + ".");
            return false;
        }

        arenaKits.add(arenaName);
        return true;
    }

    public static boolean removeKit(String arenaName) {
        if (!arenaKits.contains(arenaName)) {
            System.out.println("There is no kit named " + arenaName + ".");
            return false;
        }

        arenaKits.remove(arenaName);
        KitItems.kitItems.removeAll(arenaName);
        return true;
    }
}
