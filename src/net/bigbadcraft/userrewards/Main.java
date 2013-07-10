package net.bigbadcraft.userrewards;

import java.io.File;
import java.util.Map.Entry;
import java.util.logging.Logger;

import net.bigbadcraft.userrewards.resources.Storage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static File file;
	
	/** Adds logger for console messages */
	public final Logger logger = logger.getlogger("UserRewards");
	
	public void onEnable(){
		
		this.saveDefaultConfig();
		
		/*
		 *start console messages
		 */
		this.logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() + "Has been Enabled!");
		this.logger.info("Thank you for choosing UserRewards by HenzQ!");
		this.logger.info("Check for updates @ http://dev.bukkit.org/bukkit-plugins/userrewards/");
		
		/*
		 * File resetting, problem could be here
		 */
		file = new File(this.getDataFolder().getAbsolutePath(), "player-points.yml");
		new Storage(this, file);
		Storage.loadFile();
		
		for (String s : this.getConfig().getStringList("points.console-commands")){
			if (Storage.line != null){
				Storage.replaceVariable(s);
			}
		}
		
		dispatchCommands();
		
		getCommand("urewards").setExecutor(new Commands());
		
	}
	
	public void dispatchCommands(){
		/*
		 * Get the actual line that's being read
		 */
		if (Storage.line != null){
			for (Entry<String, Integer> entry : Storage.rewardedUsers.entrySet()){
				if (entry.getValue() == this.getConfig().getInt("points.max-points")){
					if (this.getConfig().getBoolean("points.enable-commands") == true){
						for (String value : this.getConfig().getStringList("points.console-commands")){
							Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
						}
					}
				}
			}
		}
	}
	
	/*
	 * Have a scheduler to automate commands, make time configurable
	 */
	
	public void onDisable(){
		
		/*
		 *start console messages
		 */
		this.logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() + "Has been Disabled!!");
	}
}
