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

public class Storage {
	
	private static Main plugin;
	public static File file;
	public static HashMap<String, Integer> rewardedUsers;
	public static String line;
	
	public Storage(Main plugin, File file){
		Storage.plugin = plugin;
		rewardedUsers = new HashMap<String, Integer>();
		Storage.file = file;
		
		if (file.exists() == false){
			try{
				plugin.getLogger().log(Level.INFO, file.toString() + " doesn't exist, creating one..");
				file.createNewFile();
				plugin.getLogger().log(Level.INFO, file.toString() + " has been created.");
			}catch (IOException ex){
				plugin.getLogger().log(Level.SEVERE, file.toString() + " could not be created!");
			}
		}
		
	}
	
	public static void loadFile(){
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			
			while ( (line = reader.readLine()) != null){
				try{
					if (containsValues(line, Integer.parseInt(line)) == false){
						rewardedUsers.put(line, Integer.parseInt(line));
					}
				}catch (NumberFormatException e){
					
				}
			}
			
			reader.close();
		}catch (FileNotFoundException e){
			plugin.getLogger().log(Level.SEVERE, "Unable to locate: " + file.toString());
			e.printStackTrace();
		}catch (IOException e){
			plugin.getLogger().log(Level.SEVERE, "Unable to read: " + file.toString());
			e.printStackTrace();
		}
	}
	
	public static void saveFile(){
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file))){
			
			for (Entry<String, Integer> entry : rewardedUsers.entrySet()){
				out.write(entry.getKey() + ": " + entry.getValue());
				out.newLine();
			}
			
			out.close();
		}catch (IOException e){
			plugin.getLogger().log(Level.SEVERE, "Unable to write to: " + file.toString());
			e.printStackTrace();
		}
	}
	
	public static boolean containsValues(String value, int points){
		return rewardedUsers.containsKey(value) && rewardedUsers.containsKey(points);
	}
	
	public static void addValues(String value, int points){
		if (containsValues(value, points) == false){
			rewardedUsers.put(value, points);
		}
	}
	
	/*
	 * For later use
	 */
	public void removeValues(String value, int points){
		rewardedUsers.remove(value);
		rewardedUsers.remove(points);
	}
	
	public static HashMap<String, Integer> getValues(){
		return rewardedUsers;
	}
	
	public static String replaceVariable(String stringList){
		return stringList.replaceAll("%players%", line);
	}
	
}
