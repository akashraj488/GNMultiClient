package utility;

import java.io.FileReader;
import java.util.Properties;

public class ReadPropertyFile 
{

	public static void main(String[] args) throws Exception 
	{
		FileReader fr=new FileReader("C:\\Users\\rjpta\\eclipse-workspace\\GNMultiClient\\src\\test\\resources\\configFile\\config.properties");
		Properties pr=new Properties();
		pr.load(fr);
		//System.out.println(pr.getProperty("GNUrl"));
		
	}

}
