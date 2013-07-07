package net.bigbadcraft.userrewards.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import net.bigbadcraft.userrewards.Main;

public class Storage {
	
	public static File file;
	private Main plugin;
	public static HashMap<String, Integer> rewardedUsers;
	String pluginFolder = plugin.getDataFolder().getAbsolutePath();
	
	public Storage(Main plugin){
		this.plugin = plugin;
		file = new File(pluginFolder, "player-points.yml");
		rewardedUsers = new HashMap<String, Integer>();
	}
	
	public static void loadFile(){
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while ( (line = reader.readLine()) != null){
				if (rewardedUsers.containsKey(line) == false){
					rewardedUsers.put(line, 0);
				}
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFile(){
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file, true))){
			
			for (Entry<String, Integer> entry : rewardedUsers.entrySet()){
				out.append(entry.getKey());
				out.newLine();
			}
			
			out.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
