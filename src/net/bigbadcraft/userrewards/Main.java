package net.bigbadcraft.userrewards;

import java.io.File;

import net.bigbadcraft.userrewards.resources.Storage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	File file;
	
	public void onEnable(){
		
		this.saveDefaultConfig();
		
		file = new File(this.getDataFolder().getAbsolutePath(), "player-points.yml");
		new Storage(this, this.file);
		
		getCommand("urewards").setExecutor(new Commands());
		
	}
	
	public void dispatchCommands(){
		//check if player points >= than max-points, do this
		if (this.getConfig().getBoolean("points.run-commands") == true){
			for (String value : this.getConfig().getStringList("points.run-commands.")){
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
			}
		}
	}
	
	public void onDisable(){
		
	}
}
