
package net.bigbadcraft.userrewards.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import net.bigbadcraft.userrewards.Main;

public class Storage{
	
	private static Main plugin;
	public static File file;
	public static HashMap<String, Integer> rewardedUsers;
	
	public Storage(Main plugin){
		Storage.plugin = plugin;
		file = new File(plugin.getDataFolder().getAbsolutePath(), "player-points.yml");
		rewardedUsers = new HashMap<String, Integer>();
	}
	
	public static boolean contains(String value){
		return rewardedUsers.containsKey(value);
	}
	
	public static void add(String value, int points){
		if (Storage.contains(value) == false){
			rewardedUsers.put(value, points);
		}
	}
	
	public static void createFile(){
		if (file.exists() == false){
			plugin.getLogger().info(file.toString() + " doesn't exist, creating..");
			try{
				file.createNewFile();
				plugin.getLogger().info(file.toString() + " has been created.");
			}catch (IOException e){
				plugin.getLogger().log(Level.SEVERE, file.toString() + " could not be created!");
				e.printStackTrace();
			}
		}
	}
	
	public void loadFile(){
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			
			String line;
			
			while ( (line = reader.readLine()) != null){
				if (rewardedUsers.containsKey(line) == false){
					rewardedUsers.put(line, 0);
				}
			}
			
			reader.close();
		}catch (FileNotFoundException e){
			plugin.getLogger().log(Level.SEVERE, file.toString() + " not found!");
			e.printStackTrace();
		}catch (IOException e){
			plugin.getLogger().log(Level.SEVERE, file.toString() + " is corrupted!");
			e.printStackTrace();
		}
	}
	
	public static void saveFile(){
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file, true))){
			
			for (Entry<String, Integer> entry : rewardedUsers.entrySet()){
				out.append(entry.getKey() + ": " + entry.getValue());
				out.newLine();
			}
			
			out.close();
		}catch (IOException e){
			plugin.getLogger().log(Level.SEVERE, file.toString() +  " cannot be written to!");
			e.printStackTrace();
		}
	}
}