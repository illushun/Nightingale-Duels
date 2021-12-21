public class KitItems {
    public static Multimap<String, ItemStack> kitItems = ArrayListMultimap.create();

    private Player ply;
    private String arenaName;

    public KitItems() {}

    public KitItems(Player ply, String arenaName) {
        this.ply = ply;
        this.arenaName = arenaName;
    }

    public boolean GiveKitItems() {
        if (!CreateKit.arenaKits.contains(arenaName)) {
            System.out.println("There is no kit with the name " + arenaName + ".");
            return false;
        }

        if (GetKitItems().isEmpty()) {
            System.out.println("Kit is empty!");
            return false;
        }

        try {
            GetKitItems().forEach((name, items) -> {
                if (name.matches(arenaName)) {
                    ply.getInventory().addItem(items);
                }
            });
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean AddItem() {
        if (!new CreateKit().GetKits().contains(arenaName)) {
            System.out.println("There is no kit with the name " + arenaName + ".");
            return false;
        }

        ItemStack addItem = ply.getInventory().getItemInMainHand();
        if (addItem.getType().isAir()) {
            System.out.println("Unable to add air to kit.");
            return false;
        }

        GetKitItems().put(arenaName, addItem);
        return true;
    }

    public boolean RemoveItem() {
        if (!new CreateKit().GetKits().contains(arenaName)) {
            System.out.println("There is no kit with the name " + arenaName + ".");
            return false;
        }

        ItemStack addItem = ply.getInventory().getItemInMainHand();
        if (addItem.getType().isAir() || !GetKitItems().containsValue(addItem)) {
            System.out.println("That item doesn't exist for the kit " + arenaName + ".");
            return false;
        }

        GetKitItems().remove(arenaName, addItem);
        return true;
    }

    public Multimap<String, ItemStack> GetKitItems() {
        return kitItems;
    }
}
