package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
	
	public static Properties getProp(){
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("src/main/java/core/config.properties");
			prop.load(input);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
	}

}
