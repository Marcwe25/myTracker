package core;

import java.util.Properties;

public class Links {
		
	private static Properties prop;
	
	private Links(){}
	
	static{
			prop = Utility.getProp();
	}
	
	public static String get(String property){
		String output = "";
		try {
			output = prop.getProperty(property);
		} catch (Exception e) {
			System.err.println("could not get property " + property);
			e.printStackTrace();
		}
		return output;
	}

}
