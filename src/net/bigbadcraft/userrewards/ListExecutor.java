package net.bigbadcraft.userrewards;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {
	
	private Main plugin;
	public ListExecutor(Main plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args){
		if (!(sender instanceof Player)){
			sender.sendMessage("Use this command in game.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("llist")){
			return command(sender, args);
		}
		
		return true;
	}
	
	private boolean command(CommandSender sender, String[] args){
		if (args.length <= 0){
			try{
				Scanner scan = new Scanner(plugin.file);
			
				while (scan.hasNextLine()){
					sender.sendMessage(scan.nextLine());
				}
			
				scan.close();
			}catch (FileNotFoundException ex){
				sender.sendMessage("§c" + plugin.file.toString() + " could not be found!");
			}
		}
		return true;
	}
}
