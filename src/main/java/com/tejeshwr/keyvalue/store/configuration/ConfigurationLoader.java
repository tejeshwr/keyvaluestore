package com.tejeshwr.keyvalue.store.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Load configuration properties
 *
 * @author tejeshwr
 */
public class ConfigurationLoader
{
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    private ConfigurationLoader()
    {
    }
    /**
     * Load properties from configuration file
     * @param configClass
     * @param file
     * @return
     */
    public static Map<String, Object> load(Class<?> configClass, String file)
    {
        Map<String, Object> propertyMap = new HashMap<>();
        try
        {
            Properties props = new Properties();
            try (FileInputStream propStream = new FileInputStream(file))
            {
                props.load(propStream);
            }
            for (Field field : configClass.getDeclaredFields())
            {
                if (Modifier.isStatic(field.getModifiers()))
                {
                    field.set(null, getValue(props, field.getName(), field.getType()));
                    propertyMap.put(field.getName(), getValue(props, field.getName(), field.getType()));
                }
            }
        }
        catch (FileNotFoundException fnfe)
        {
            logger.info(file + ", Property file does not exist. Please Check.");
            System.out.println(file + ", Property file does not exist. Please Check.");
        }
        catch (IOException ioe)
        {
            logger.info(file + ", Property file does not exist or It is being used by another file. Please Check.");
            System.out.println(file + ", Property file does not exist or It is being used by another file. Please Check.");
        }
        catch (IllegalAccessException iae)
        {
            logger.info("An attempt is made to access a method or member that visibility qualifiers do not allow. Please Check the config class.");
            System.out.println("An attempt is made to access a method or member that visibility qualifiers do not allow. Please Check the config class.");
        }
        catch (IllegalArgumentException iae)
        {
            System.exit(1);
        }
        return propertyMap;
    }

    /**
     * @param props
     * @param name
     * @param type
     * @return
     */
    private static Object getValue(Properties props, String name, Class<?> type) throws IllegalArgumentException
    {
        String value = props.getProperty(name);
        if (value == null)
        {
            logger.info("IllegalArgumentException");
            logger.info("Missing configuration value: " + name);
            System.out.println("Missing configuration value: " + name);
            throw new IllegalArgumentException();
        }
        if (type == String.class)
        {
            return value;
        }
        if (type == boolean.class)
        {
            return Boolean.parseBoolean(value);
        }
        if (type == int.class)
        {
            return Integer.parseInt(value);
        }
        if (type == long.class)
        {
            return Long.parseLong(value);
        }
        if (type == float.class)
        {
            return Float.parseFloat(value);
        }
        logger.info("IllegalArgumentException");
        logger.info("Unknown configuration value type: " + type.getName());
        throw new IllegalArgumentException();
    }
}