package com.tejeshwr.keyvalue.store.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.tejeshwr.keyvalue.store.util.KVStoreHelper;


/**
 * 
 * @author tejeshwr
 *
 */
public class KVStoreImpl implements KVStoreModel {
	KVStoreHelper helper = new KVStoreHelper();
	public void createPair(String path, String newKey, JSONObject newValue) {
		try {
			
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			helper.storeData(file, newKey, newValue);
		}

		catch (IOException ioe) {
			System.out.println(ioe);
		} catch (NumberFormatException nef) {
			System.out.println(nef);
		}
	}

	@Override
	public void getValue(String key) {
		helper.fetchData(key);
	}

	@Override
	public void deleteValue(String key) {
		helper.deleteData(key);
	}

	@Override
	public void deleteValueTTL(int seconds) {
		helper.deleteWithTTL(seconds);
	}

}
