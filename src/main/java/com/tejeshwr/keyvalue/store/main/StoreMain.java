package com.tejeshwr.keyvalue.store.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tejeshwr.keyvalue.store.util.GlobalConstants;

public class StoreMain {
	private static final Logger logger = LoggerFactory.getLogger(StoreMain.class);
	public static void main(String[] args) {
		KVStore instance = KVStore.getInstance();
		String key = "Teja5";
		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader("Sample.json"));
			JSONObject value = (JSONObject) obj; 
			//if(args[0].isEmpty()||args[0]==null)
			//{
				logger.info("Keyvalue file store path is empty. Proceeding with default location "+GlobalConstants.DEFAULT_KEY_VALUE_STORE_PATH);
				//instance.createData(GlobalConstants.DEFAULT_KEY_VALUE_STORE_PATH, key, value);
		//	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
					
			
		instance.deleteData(key);
		//instance.getData(key);
		 
	}
	
}
