package com.tejeshwr.keyvalue.store.main;

import org.json.simple.JSONObject;

public interface KVStoreModel {
	
	void createPair(String path,String key, JSONObject value) ;
	void getValue(String key);
	void deleteValue(String key);
	void deleteValueTTL(int seconds);
	
}
