package com.tejeshwr.keyvalue.store.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * @author tejeshwr
 *
 */


public class KVStoreHelper {
	LocalDateTime currentTime = LocalDateTime.now().withNano(0);
	public void getLock() {};
	public void storeData(File file, String newKey, JSONObject newValue) throws JsonSyntaxException, IOException {
		int keyStartIndex;
		int valueStartIndex;
		String key;
		JsonObject value;
		String keyValueString;
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		boolean found = false;
		 FileChannel channel = raf.getChannel();
		 FileLock lock = null;
		 try {
		      lock = channel.lock();
		    } catch (final OverlappingFileLockException e) {
		      raf.close();
		      channel.close();
		    }
		while (raf.getFilePointer() < raf.length()) {
			keyValueString = raf.readLine();
			keyStartIndex = keyValueString.indexOf('|');
			valueStartIndex=keyValueString.indexOf('=');
			key = keyValueString.substring(keyStartIndex+1, valueStartIndex);
			value = new JsonParser().parse(keyValueString.substring(valueStartIndex + 1)).getAsJsonObject();
			if (key.equals(newKey)) {
				found = true;
				break;
			}
		} 	
		
		if (found == false) {
			keyValueString = currentTime + "|" + newKey + "=" + String.valueOf(newValue);
			raf.writeBytes(keyValueString);
			raf.writeBytes(System.lineSeparator());
			System.out.println("Record added successfully.");
			lock.release();
			raf.close();
			channel.close();
		} else {
			lock.release();
			raf.close();
			channel.close();
			System.out.println("Input key " + newKey + " exists already. Please provide different key.");
		}
	}

	
	
	public void fetchData(String searchKey) {
		String keyValueString;
		int keyStartIndex;
		int valueStartIndex;
		JsonObject value = null;
		String key = null;
		boolean found = false;
		File file = new File("keyValueStore.txt");
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "rw");
			
			try {
				 FileChannel channel = raf.getChannel();
				 FileLock lock = null;
				 try {
				      lock = channel.lock();
				    } catch (final OverlappingFileLockException e) {
				      raf.close();
				      channel.close();
				    }
				while (raf.getFilePointer() < raf.length()) {
					keyValueString = raf.readLine();
					keyStartIndex = keyValueString.indexOf('|');
					valueStartIndex=keyValueString.indexOf('=');
					key = keyValueString.substring(keyStartIndex+1, valueStartIndex);
					value = new JsonParser().parse(keyValueString.substring(valueStartIndex + 1)).getAsJsonObject();
					if (key.equals(searchKey)) {
						found = true;
						break;
					}
				
				}
				if(found)
				{
					System.out.println("Key: " + key + "\n" + "Value: " + value + "\n");
				}
				else {
					System.out.println("Input key does not exist!");
				}
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/*public void deleteData(String deleteKey) {
		try {
			String keyValueString;
			int keyStartIndex;
			int valueStartIndex;
			JsonObject value = null;
			boolean found = false;
			String key;
			File file = new File("keyValueStore.txt");
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			 FileChannel channel = raf.getChannel();
			 FileLock lock = null;
			 try {
			      lock = channel.lock();
			    } catch (final OverlappingFileLockException e) {
			      raf.close();
			      channel.close();
			    }
			while (raf.getFilePointer() < raf.length()) {
				keyValueString = raf.readLine();
				keyStartIndex = keyValueString.indexOf('|');
				valueStartIndex=keyValueString.indexOf('=');
				key = keyValueString.substring(keyStartIndex+1, valueStartIndex);
				value = new JsonParser().parse(keyValueString.substring(valueStartIndex + 1)).getAsJsonObject();
				if (key.equals(deleteKey)) {
					found = true;
					break;
				}
			}

			if (found == true) {
				File tmpFile = new File("temp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
				raf.seek(0);
				while (raf.getFilePointer() < raf.length()) {
					keyValueString = raf.readLine();
					keyStartIndex = keyValueString.indexOf('|');
					valueStartIndex=keyValueString.indexOf('=');
					key = keyValueString.substring(keyStartIndex+1, valueStartIndex);
					keyValueString = raf.readLine();
					if (key.equals(deleteKey)) {
						continue;
					}
					System.out.println("Key Value to be written to file after deletion : "+key);
					tmpraf.writeBytes(keyValueString);
					tmpraf.writeBytes(System.lineSeparator());
				}
				raf.seek(0);
				tmpraf.seek(0);
				while (tmpraf.getFilePointer() < tmpraf.length()) {
					raf.writeBytes(tmpraf.readLine());
					raf.writeBytes(System.lineSeparator());
				}
				raf.setLength(tmpraf.length());
				tmpraf.close();
				raf.close();
				tmpFile.delete();

				System.out.println("Entry deleted. ");
			} else {
				raf.close();
				System.out.println("Input key " + deleteKey + " does not exists. ");
			}
		}

		catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	*/
	
	public void deleteData(String deleteKey) {
		
			  
	        try { 
	            String newName = "Teja1"; 
	            String keyValueString;
	            String key; 
	            int keyStartIndex;
				int valueStartIndex;
	            File file = new File("keyValueStore.txt"); 
	  
	            if (!file.exists()) { 
	  
	                file.createNewFile(); 
	            } 
	  
	            RandomAccessFile raf 
	                = new RandomAccessFile(file, "rw"); 
	            boolean found = false; 
	  
	            while (raf.getFilePointer() < raf.length()) { 
	            	keyValueString = raf.readLine(); 
	            	keyStartIndex = keyValueString.indexOf('|');
					valueStartIndex=keyValueString.indexOf('=');
					key = keyValueString.substring(keyStartIndex+1, valueStartIndex);
	                System.out.println("Name : "+key );
	                System.out.println("New Name : "+newName );
	               
	                if (key.equals(deleteKey)) { 
	                	System.out.println("Here ...");
	                    found = true; 
	                    break; 
	                } 
	            } 
	            if (found == true) { 
	                File tmpFile = new File("temp.txt"); 
	                RandomAccessFile tmpraf 
	                    = new RandomAccessFile(tmpFile, "rw"); 
	                raf.seek(0); 
	                while (raf.getFilePointer() < raf.length()) { 
	                	keyValueString = raf.readLine(); 
	                	keyStartIndex = keyValueString.indexOf('|');
	    				valueStartIndex=keyValueString.indexOf('=');
	    				key = keyValueString.substring(keyStartIndex+1, valueStartIndex);
	                    if (key.equals(deleteKey)) { 
	                        continue; 
	                    } 
	                    tmpraf.writeBytes(keyValueString); 
	                    tmpraf.writeBytes(System.lineSeparator()); 
	                } 
	                raf.seek(0); 
	                tmpraf.seek(0); 
	                while (tmpraf.getFilePointer() < tmpraf.length()) { 
	                    raf.writeBytes(tmpraf.readLine()); 
	                    raf.writeBytes(System.lineSeparator()); 
	                } 
	                raf.setLength(tmpraf.length()); 
	                tmpraf.close(); 
	                raf.close(); 
	                tmpFile.delete(); 
	                System.out.println(" Friend deleted. "); 
	            } 
	  
	            else { 
	                raf.close(); 
	                System.out.println(" Input name"
	                                   + " does not exists. "); 
	            } 
	        } 
	  
	        catch (IOException ioe) { 
	            System.out.println(ioe); 
	        } 
	    } 
		
		
	public void deleteWithTTL(int seconds) {
		File inputFile = new File("keyValueStore.txt");
		File outputFile = new File("output.txt");
		PrintWriter pw;

		LocalDateTime currentTime = LocalDateTime.now().withNano(0);
		String time;
		int index;
		BufferedReader br1;
		try {
			br1 = new BufferedReader(new FileReader(inputFile));
			pw = new PrintWriter(new FileWriter(outputFile));
			String line1 = br1.readLine();
			while (line1 != null) {
				index = line1.indexOf('|');
				time = line1.substring(0, index);
				System.out.println(time);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(time, formatter).plusSeconds(seconds);
				if (!currentTime.isAfter(dateTime)) {
					System.out.println("Current Time : " + currentTime);
					System.out.println("Date Time : " + dateTime);
					pw.println(line1);
				}
				line1 = br1.readLine();
			}

			pw.flush();
			br1.close();
			pw.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!inputFile.delete()) {
			System.out.println("Could not delete file");
			return;
		}
		if (!outputFile.renameTo(inputFile))
			System.out.println("Could not rename file");
		System.out.println("File operation performed successfully");
	}
}
