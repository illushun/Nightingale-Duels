public class CreateMatch {
    public static Map<String, Long> requestDuel = Maps.newHashMap();
    public static Map<String, Double> duelBet = Maps.newHashMap();
    public static Map<String, String> requestDuelName = Maps.newHashMap();

    public static boolean UserHasInvitation(String name) {
       return requestDuelName.containsValue(name);
    }

    public static boolean AlreadyRequestedDuel(Player ply) {
        return requestDuel.containsKey(ply.getName());
    }

    public static Long GetTimeRequestedDuelMS(Player ply) {
        return requestDuel.get(ply.getName());
    }

    public static String GetTimeRequestedDuel(Player ply) {
        long previousTime = System.currentTimeMillis() - requestDuel.get(ply.getName());
        return HandleTime.convertMS(previousTime);
    }

    public static boolean DuelRequestExpired(Long time) {
        Long calcTime = 600 * 1000L;
        long cooldownTime = time + calcTime;
        long retrieveTime = cooldownTime - System.currentTimeMillis();
        return retrieveTime <= 0;
    }

    public static void RemoveOldDuelRequest(Player ply) {
        if (!AlreadyRequestedDuel(ply)) {
            return;
        }

        requestDuel.remove(ply.getName());
        requestDuelName.remove(ply.getName());
        duelBet.remove(ply.getName());
    }

    public static boolean AddDuelRequest(Player ply, String name, double bet) {
        if (AlreadyRequestedDuel(ply)) {
            RemoveOldDuelRequest(ply);
            return false;
        }

        requestDuel.put(ply.getName(), System.currentTimeMillis());
        requestDuelName.put(ply.getName(), name);
        duelBet.put(ply.getName(), bet);
        return true;
    }
}
