package net.bigbadcraft.userrewards;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	String msg = "Usage: /urewards add <player> <points>";
	String msg2 = "Usage: /urewards take <player> points>";
	String msg3 = "Please add/take points from player";
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args){
		if (cmd.getName().equalsIgnoreCase("urewards")){
			return command(sender, args);
		}
		return true;
	}
	
	private boolean command(CommandSender sender, String[] args){
		
		if (args.length == 0){
			StringBuilder sb = new StringBuilder();
			sb.append("/urewards - List Commands\n")
			.append("/urewards add <player> <points> - Adds points\n")
			.append("/urewards take <player> <points> - Takes points\n");
			sender.sendMessage(sb.toString());
			return true;
		}
		
		if (args.length == 1){
			if (args[0].equalsIgnoreCase("add")){
				sender.sendMessage(msg);
			}
			else if (args[0].equalsIgnoreCase("take")){
				sender.sendMessage(msg2);
			}
		}
		
		if (args.length == 2){
			sender.sendMessage(msg3);
		}
		
		if (args.length == 3){
			Player player = Bukkit.getPlayer(args[1]);
			if (player != null){
				if (args[0].equalsIgnoreCase("add")){
					
				}
				else if (args[0].equalsIgnoreCase("take")){
					
				}
			}
		}
		return true;
	}
}
