package me.dailyreward.Configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

import java.io.File;
import java.io.IOException;

public class Yaml {

	private final File file;
	private final YamlConfiguration yaml = new YamlConfiguration();

	public Yaml(File file) {
		this.file = file;

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.load();
	}

	public Yaml(String path) {
		this.file = new File(path);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.load();
	}

	public void remove() {
		try {
			this.yaml.load(this.file);
			this.file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void load() {
		try {
			this.yaml.load(this.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save the Yaml data to the file passed in the constructor.
	 **/
	public void save()
	{
		try {
			this.yaml.save(this.file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void delete()
	{
		try {
			this.file.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** Get an Integer from the given path. **/
	public int getInteger(String s) {
		return this.yaml.getInt(s);
	}

	/** Reload Yaml file. **Warning** Very Unstable. **/
	public void reload() {
		this.save();
		this.load();
	}

	/** Get a String from the path defined. **/
	public String getString(String s) {
		return this.yaml.getString(s);
	}

	/** Gets an Object at the given path. **/
	public Object get(String s) {
		return this.yaml.get(s);
	}

	/** Gets a boolean at the given path. **/
	public boolean getBoolean(String s) {
		return this.yaml.getBoolean(s);
	}

	/** If the given path has no variable, it will be given a variable. **/
	public void add(String s, Object o) {
		if(!this.contains(s)) {
			this.set(s, o);
		}
	}

	/** Adds a String to a List of Strings. **/
	public void addToStringList(String s, String o) {
		this.yaml.getStringList(s).add(o);
	}

	/** Removes a String to a List of Strings. **/
	public void removeFromStringList(String s, String o) {
		this.yaml.getStringList(s).remove(o);
	}

	/** Looks for a String List at given path. **/
	public java.util.List<String> getStringList(String s) {
		return this.yaml.getStringList(s);
	}

	/** Adds an Integer to a List of Integers. **/
	public void addToIntegerList(String s, int o) {
		this.yaml.getIntegerList(s).add(o);
	}

	/** Removes an Integer to a List of Integers. **/
	public void removeFromIntegerList(String s, int o) {
		this.yaml.getIntegerList(s).remove(o);
	}

	/** Looks for a Integer List at given path. **/
	public java.util.List<Integer> getIntegerList(String s) {
		return this.yaml.getIntegerList(s);
	}

	/** Creates a new String List at given path. **/
	public void createStringList(String s, java.util.List<String> list) {
		this.yaml.set(s, list);
	}

	/** Creates a new Integer List at given path. **/
	public void createIntegerList(String s, java.util.List<Integer> list) {
		this.yaml.set(s, list);
	}

	/** Attempts to remove a variable at the given path. **/
	public void remove(String s) {
		this.set(s, null);
	}

	/** Returns true if the given path has a value. **/
	public boolean contains(String s) {
		return this.yaml.contains(s);
	}

	/** Gets a double at the given path. **/
	public double getDouble(String s) {
		return this.yaml.getDouble(s);
	}

	/** Gets a long at the given path. **/
	public Long getLong(String s) {
		return this.yaml.getLong(s);
	}

	/** Sets a Object to the given path. **/
	public void set(String s, Object o) {
		this.yaml.set(s, o);
	}

	/** Increases an Integer by 1. **/
	public void increment(String s) {
		this.yaml.set(s, this.getInteger(s) + 1);
	}

	/** Decreases an Integer by 1. **/
	public void decrement(String s) {
		this.yaml.set(s, this.getInteger(s) - 1);
	}

	/** Increases an Integer by i. **/
	public void increment(String s, int i) {
		this.yaml.set(s, this.getInteger(s) + i);
	}

	/** Decreases an Integer by i. **/
	public void decrement(String s, int i) {
		this.yaml.set(s, this.getInteger(s) - i);
	}

	/** Returns the YamlConfiguration's Options. **/
	public YamlConfigurationOptions options() {
		return this.yaml.options();
	}
}