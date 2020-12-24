public class HandleTime {
    public static String convertMS(long time) {
        long ms = time % 1000;
        long sec = (time / 1000) % 60;
        long min = (time / (1000 * 60)) % 60;

        if (min >= 60) {
            long hour = (time / (1000 * 60 * 60)) % 24;

            if (hour >= 24) {
                long days = (time / (1000 * 60 * 60 * 24));
                return String.format("%02dd %02dh %02dm %02ds", days, hour, min, sec);
            }
            return String.format("%02dh %02dm %02ds", hour, min, sec);
        }
        else if (min >= 1) {
            return String.format("%02dm %02ds", min, sec);
        }
        else {
            return String.format("%02ds", sec);
        }
    }
}
