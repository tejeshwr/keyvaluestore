package com.tejeshwr.keyvalue.store.main;

import org.json.simple.JSONObject;

public class KVStore {
	private static KVStore instance = null;
	KVStoreModel store = new KVStoreImpl();
	private KVStore() {
		super();
	}
	public void createData(String path, String key, JSONObject value) {
		store.createPair(path, key, value);
	}
	public synchronized void deleteData(String key) {
		store.deleteValue(key);
	}
	public synchronized void getData(String key) {
		store.getValue(key);
	}
	public static KVStore getInstance() {
		if (instance == null) 
        { 
			instance =  new KVStore(); 
        } 
		return instance;
	}
}
