public class UserEconomy {
    public static boolean userHasEnoughMoney(Player ply, double bet) {
        double getBalance = Main.instance.getEconomy().bankBalance(ply.getName()).balance;
        return bet < getBalance;
    }
}
