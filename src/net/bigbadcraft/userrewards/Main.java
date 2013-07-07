package net.bigbadcraft.userrewards;

import net.bigbadcraft.userrewards.utils.Storage;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public void onEnable(){
		new Storage(this);
	}
	
	public void onDisable(){
	}
}
