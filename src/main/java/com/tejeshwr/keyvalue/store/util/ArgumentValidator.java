package com.tejeshwr.keyvalue.store.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tejeshwr.keyvalue.store.main.StoreMain;

public class ArgumentValidator {

	private static final Logger logger = LoggerFactory.getLogger(ArgumentValidator.class);

	public static File getFile(String path) {
		File file;
		if (path.isEmpty() || path == null) {
			logger.info("Keyvalue file store path is empty. Proceeding with default location "
					+ GlobalConstants.DEFAULT_KEY_VALUE_STORE_PATH);
			file = new File(GlobalConstants.DEFAULT_KEY_VALUE_STORE_PATH);
		} else {
			file = new File(path);
		}
		return file;

	}

	public static boolean isKeyValidLength(String key) {
		if(key.length()<=GlobalConstants.SET_KEY_MAX_CHARS)
			return true;
		else
			return false;
	}
}
