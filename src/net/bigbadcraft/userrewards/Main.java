package net.bigbadcraft.userrewards;

import net.bigbadcraft.userrewards.resources.Storage;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public void onEnable(){
		new Storage(this);
		getCommand("urewards").setExecutor(new Commands());
		Storage.createFile();
		this.saveDefaultConfig();
	}
	
	public void onDisable(){
	}
}
