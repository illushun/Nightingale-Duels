public class DuelCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player ply = (Player) sender;
            String commandName = args[0];

            if (commandName.equalsIgnoreCase("create")) {
                if (args.length > 2 || args.length == 0) {
                    ply.sendMessage(ChatColor.RED + "Incorrect command usage! /nightarena create [arena name]");
                    return true;
                }

                String arenaName = args[1];

                new ArenaCreation(arenaName).CreateArena(ply);
                ply.sendMessage(ChatColor.GREEN + "Arena " + ChatColor.BOLD + arenaName + ChatColor.GREEN + " has been created.");
            }
            else if (commandName.equalsIgnoreCase("set")) {
                if (args.length > 3 || args.length == 0) {
                    ply.sendMessage(ChatColor.RED + "Incorrect command usage! /nightarena set [arena name] [firstPos|secondPos]");
                    return true;
                }

                String arenaName = args[1];
                String position = args[2];

                if (!position.equalsIgnoreCase("firstPos") || !position.equalsIgnoreCase("secondPos")) {
                    return true;
                }

                if (new ArenaCreation(arenaName).SetArenaLocation(position, ply.getLocation())) {
                    ply.sendMessage(ChatColor.GREEN + "Arena " + arenaName + " " + position + " successfully set!");
                    return true;
                }
                ply.sendMessage(ChatColor.RED + "Unable to set Arena location for " + arenaName + ". (Check console)");
            }
            else if (commandName.equalsIgnoreCase("duel")) {
                if (args.length > 3 || args.length == 0) {
                    ply.sendMessage(ChatColor.RED + "Incorrect command usage! /nightarena duel [player name] [bet amount]");
                    return true;
                }

                if (!args[1].equalsIgnoreCase("accept") || !args[1].equalsIgnoreCase("deny") || !args[1].equalsIgnoreCase("cancel")) {
                    String playerName = args[1];
                    double betAmount = Double.parseDouble(args[2]);

                    if (!UserEconomy.userHasEnoughMoney(ply, betAmount)) {
                        ply.sendMessage(ChatColor.RED + "You don't have enough money to make this bet!");
                        return true;
                    }

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (onlinePlayer.getName().equalsIgnoreCase(playerName)) {
                            if (CreateMatch.AlreadyRequestedDuel(ply) && !CreateMatch.DuelRequestExpired(CreateMatch.GetTimeRequestedDuelMS(ply))) {
                                ply.sendMessage(ChatColor.RED + "You last requested " + CreateMatch.GetTimeRequestedDuel(ply) + " ago. You can request every 10 minutes.");
                                return true;
                            }

                            if (!CreateMatch.AddDuelRequest(ply, playerName, betAmount)) {
                                ply.sendMessage(ChatColor.RED + "Unable to send " + playerName + " a duel request!");
                                return true;
                            }
                            ply.sendMessage(ChatColor.GREEN + "Successfully sent " + playerName + " a duel request. You have bet ($" + betAmount + ").");
                            return true;
                        }
                        ply.sendMessage(ChatColor.RED + playerName + " isn't online!");
                        return true;
                    }
                }

                switch (args[1])
                {
                    case "accept": /* Doing this for now until the functions are made */
                    case "deny":
                        if (!CreateMatch.UserHasInvitation(ply.getName())) {
                            ply.sendMessage(ChatColor.RED + "You don't have a pending duel invite.");
                            return true;
                        }
                        break;
                    case "cancel":
                        if (!CreateMatch.AlreadyRequestedDuel(ply)) {
                            ply.sendMessage(ChatColor.RED + "You haven't created a duel invite.");
                            return true;
                        }
                        break;
                }
            }
            else if (commandName.equalsIgnoreCase("leaderboard")) {

            }
            else if (commandName.equalsIgnoreCase("kit")) {
                if (args.length > 4 || args.length == 0) {
                    ply.sendMessage(ChatColor.RED + "Incorrect command usage! /nightarena kit [create|delete|additem|removeitem] [arena name]");
                    return true;
                }

                String commandAction = args[1];
                String arenaName = args[2];

                switch (commandAction)
                {
                    case "create":
                        if (new CreateKit(arenaName).AddKit()) {
                            ply.sendMessage(ChatColor.GREEN + "The kit " + arenaName + " has successfully been created.");
                            return true;
                        }
                        ply.sendMessage(ChatColor.RED + "Unable to create kit " + arenaName + "! (Check console)");
                        break;
                    case "delete":
                        if (new CreateKit(arenaName).RemoveKit()) {
                            ply.sendMessage(ChatColor.GREEN + "The kit " + arenaName + " has successfully been removed.");
                            return true;
                        }
                        ply.sendMessage(ChatColor.RED + "Unable to remove kit " + arenaName + "! (Check console)");
                        break;
                    case "additem":
                        if (new KitItems(ply, arenaName).AddItem()) {
                            ply.sendMessage(ChatColor.GREEN + "Added the item in your hand to kit " + arenaName + ".");
                            return true;
                        }
                        ply.sendMessage(ChatColor.RED + "Unable to add the item to kit " + arenaName + "! (Check console)");
                        break;
                    case "removeitem":
                        if (new KitItems(ply, arenaName).RemoveItem()) {
                            ply.sendMessage(ChatColor.GREEN + "Removed the item in your hand from kit " + arenaName + ".");
                            return true;
                        }
                        ply.sendMessage(ChatColor.RED + "Unable to remove the item from kit " + arenaName + "! (Check console)");
                        break;
                }
            }
            else {
                // gui
            }
        }
        return false;
    }
}
