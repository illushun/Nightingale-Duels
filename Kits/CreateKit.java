public class CreateKit {
    public static ArrayList<String> arenaKits = new ArrayList<>();

    private String arenaName;

    public CreateKit() {}

    public CreateKit(String arenaName) {
        this.arenaName = arenaName;
    }

    public boolean AddKit() {
        if (GetKits().contains(arenaName) || !new ArenaCreation().GetArenas().containsKey(arenaName)) {
            System.out.println("There is either already a kit called " + arenaName + ", or there is no arena named " + arenaName + ".");
            return false;
        }

        GetKits().add(arenaName);
        return true;
    }

    public boolean RemoveKit() {
        if (!GetKits().contains(arenaName)) {
            System.out.println("There is no kit named " + arenaName + ".");
            return false;
        }

        GetKits().remove(arenaName);
        KitItems.kitItems.removeAll(arenaName);
        return true;
    }

    public ArrayList<String> GetKits() {
        return arenaKits;
    }
}
