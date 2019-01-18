package modbus.rest.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;

public class ConfigManager {

	
	private static String readFromResource(Class<?> currentClass, String name) throws FileNotFoundException {

		URL url = currentClass.getClassLoader().getResource(name);

		File f = new File(url.getPath());

		BufferedReader br = new BufferedReader(new FileReader(f));

		String st;
		StringBuilder sb = new StringBuilder();
		try {
			while ((st = br.readLine()) != null) {
				sb.append(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	private static void writeFile(String filePath, String content) throws IOException {
		FileWriter writer = new FileWriter(filePath);

		writer.write(content);

		writer.close();
	}

	private static String readFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String sCurrentLine;
		StringBuilder sb = new StringBuilder();

		while ((sCurrentLine = br.readLine()) != null) {
			sb.append(sCurrentLine);
		}

		br.close();

		return sb.toString();
	}

	public static void writeConfig(String filePath, String content) throws IOException {
		// File settings
		if (filePath == null || filePath.isEmpty()) {
			throw new InvalidParameterException("File path cannot be null or empty");
		}

		if (content == null || content.isEmpty()) {
			throw new InvalidParameterException("File content cannot be null or empty");
		}

		writeFile(filePath, content);
	}

	public static String readConfig(String filePath, Class<?> currentClass) throws IOException {
		// File settings
		if (filePath == null || filePath.isEmpty()) {
			throw new InvalidParameterException("File path cannot be null or empty");
		}

		File configFile = new File(filePath);

		if (!configFile.getParentFile().exists()) {
			configFile.getParentFile().mkdirs();
		}

		String configValue = "";
		if (!configFile.exists()) {
			configValue = readFromResource(currentClass, "settings.json");
			writeFile(filePath, configValue);
		} else {
			configValue = readFile(filePath);
		}

		return configValue;
	}
}
