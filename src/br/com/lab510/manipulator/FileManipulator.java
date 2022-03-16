package br.com.lab510.manipulator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileManipulator {
	
	public String readFile(String name) {
		StringBuilder content = new StringBuilder();
				
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(name), "UTF-8"));
			
			String line = br.readLine();

			while (line != null) {
				content.append(line).append("\n");
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
	
	public static Properties getProperties(String path) {
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		return props;
	}
}