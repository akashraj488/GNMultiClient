package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.Page;

public class ScreenShot extends Page
{
	public  String takeScreenShot(String srcname) throws Exception
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		// take ss and store in tmp location
		File tmplocation=ts.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"//src//test//java//screenshot//"+srcname+".jpg";
		
		FileUtils.copyFile(tmplocation, new File(path));
		return path;
	}
	

}
