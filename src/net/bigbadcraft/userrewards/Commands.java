package net.bigbadcraft.userrewards;

import net.bigbadcraft.userrewards.resources.Storage;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	String msg;
	
	public Commands(){
		msg = "§cUsage: /urewards set <player> <points>";
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args){
		
		if (sender instanceof Player == false){
			sender.sendMessage("Please use this command in game.");
		}
		
		if (cmd.getName().equalsIgnoreCase("urewards")){
			return command(sender, args);
		}
		return true;
	}
	
	private boolean command(CommandSender sender, String[] args){
		
		if (args.length == 0){
			StringBuilder sb = new StringBuilder();
			sb.append("§7--------------- [§aCommand Info§7] ---------------\n")
			.append("§7- /urewards | List Commands\n")
			.append("§7- /urewards set <player> <points> | Sets the player's points\n")
			.append("§7--------------------------------------------");
			sender.sendMessage(sb.toString());
			return true;
		}
		
		if (args.length < 3){
			if (args[0].equalsIgnoreCase("set")){
				sender.sendMessage(msg);
			}
		}
		
		if (args.length == 3){
			Player player = Bukkit.getPlayer(args[1]);
			if (player != null){
				try{
					
					if (args[0].equalsIgnoreCase("set")){
						Storage.addValues(player.getName(), Integer.parseInt(args[2]));
						Storage.saveFile();
						sender.sendMessage("§a" + player.getName() + "'s points has been set to " + args[2]);
					}
					
				}catch (NumberFormatException e){
					sender.sendMessage("§cPlease use whole numbers for fourth argument!");
				}
			}else{
				sender.sendMessage("§c" + args[1] + " is offline.");
			}
		}
		return true;
	}
}
