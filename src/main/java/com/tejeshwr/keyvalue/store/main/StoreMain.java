package com.tejeshwr.keyvalue.store.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tejeshwr.keyvalue.store.util.ArgumentValidator;
import com.tejeshwr.keyvalue.store.util.GlobalConstants;

public class StoreMain {
	private static final Logger logger = LoggerFactory.getLogger(StoreMain.class);
	public static ArgumentValidator argumentValidator = new ArgumentValidator();

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage : java -jar keyvaluestore-0.0.1-SNAPSHOT.jar <filePath> [<operation>]");
			System.err.println("Operation can be -c or -d or -g: -c is Create,-d is Delete, -g is GetValue");
		}
		KVStore instance = KVStore.getInstance();
		Object obj;

		File keyValueStore = argumentValidator.getFile(args[0]);
		// Create a key
		if (args[1] != null && args[1].equals("-c")) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the key to store : ");
			String key = scanner.next();
			if (!ArgumentValidator.isKeyValidLength(key)) {
				logger.error("Please enter a key within valid length (32) chars");
			} else {
				System.out.println("Enter the value for the key to store : ");
				String json = scanner.next();
				try {
					obj = new JSONParser().parse(json);
					JSONObject value = (JSONObject) obj;
					instance.createData(keyValueStore.getAbsolutePath(), key, value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		// Get value using a key
		else if (args[1] != null && args[1].equals("-g")) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the key to search : ");
			String key = scanner.next();
			if (!ArgumentValidator.isKeyValidLength(key)) {
				logger.error("Please enter a key within valid length (32) chars");
			} else {
				instance.getData(key);
			}
		}
		// Delete a key
		else if (args[1] != null && args[1].equals("-d")) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the key to delete : ");
			String key = scanner.next();
			if (!ArgumentValidator.isKeyValidLength(key)) {
				logger.error("Please enter a key within valid length (32) chars");
			} else {
				instance.deleteData(key);
			}
		}

		else {
			System.err.println("Usage : java -jar keyvaluestore-0.0.1-SNAPSHOT.jar <filePath> [<operation>]");
			System.err.println("Operation can be -c or -d or -g: -c is Create,-d is Delete, -g is GetValue");
		}

	}
}
