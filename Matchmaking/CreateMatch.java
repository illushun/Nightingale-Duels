public class CreateMatch {
    public static Map<String, Long> requestDuel = Maps.newHashMap();
    public static Map<String, Double> duelBet = Maps.newHashMap();
    public static Map<String, String> requestDuelName = Maps.newHashMap();

    public static boolean userHasInvitation(String name) {
       return requestDuelName.containsValue(name);
    }

    public static boolean alreadyRequested(Player ply) {
        return requestDuel.containsKey(ply.getName());
    }

    public static String getTimeRequested(Player ply) {
        Long previousTime = System.currentTimeMillis() - requestDuel.get(ply.getName());
        return HandleTime.convertMS(previousTime);
    }

    public static Long getTimeRequestedMS(Player ply) {
        return requestDuel.get(ply.getName());
    }

    public static boolean requestExpired(Long time) {
        Long calcTime = 600 * 1000L;
        Long cooldownTime = time + calcTime;
        Long retrieveTime = cooldownTime - System.currentTimeMillis();
        return retrieveTime <= 0;
    }

    public static void removeOldRequest(Player ply) {
        if (!alreadyRequested(ply)) {
            return;
        }

        requestDuel.remove(ply.getName());
        requestDuelName.remove(ply.getName());
        duelBet.remove(ply.getName());
    }

    public static boolean addRequest(Player ply, String name, double bet) {
        if (alreadyRequested(ply)) {
            removeOldRequest(ply);
            return false;
        }

        requestDuel.put(ply.getName(), System.currentTimeMillis());
        requestDuelName.put(ply.getName(), name);
        duelBet.put(ply.getName(), bet);
        return true;
    }
}
