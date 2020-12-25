public class KitItems {
    public static Multimap<String, ItemStack> kitItems = ArrayListMultimap.create();

    public static boolean giveKitItems(Player ply, String arenaName) {
        if (!CreateKit.arenaKits.contains(arenaName)) {
            System.out.println("There is no kit with the name " + arenaName + ".");
            return false;
        }

        if (kitItems.isEmpty()) {
            System.out.println("Kit is empty!");
            return false;
        }

        try {
            kitItems.forEach((name, items) -> {
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

    public static boolean addItemToKit(Player ply, String arenaName) {
        if (!CreateKit.arenaKits.contains(arenaName)) {
            System.out.println("There is no kit with the name " + arenaName + ".");
            return false;
        }

        ItemStack addItem = ply.getInventory().getItemInMainHand();
        if (addItem.getType().isAir()) {
            System.out.println("Unable to add air to kit.");
            return false;
        }

        kitItems.put(arenaName, addItem);
        return true;
    }

    public static boolean removeItemFromKit(Player ply, String arenaName) {
        if (!CreateKit.arenaKits.contains(arenaName)) {
            System.out.println("There is no kit with the name " + arenaName + ".");
            return false;
        }

        ItemStack addItem = ply.getInventory().getItemInMainHand();
        if (addItem.getType().isAir() || !kitItems.containsValue(addItem)) {
            System.out.println("That item doesn't exist for the kit " + arenaName + ".");
            return false;
        }

        kitItems.remove(arenaName, addItem);
        return true;
    }
}
