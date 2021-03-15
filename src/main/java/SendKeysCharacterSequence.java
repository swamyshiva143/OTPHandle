import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SendKeysCharacterSequence {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement email=driver.findElement(By.id("email"));
		//1.String:
		//email.sendKeys("swamyshiva@gmail.com");
		
		//2. StringBuilder:
//		StringBuilder username= new StringBuilder()
//				.append("swamy")
//				.append(" ")
//				.append("akula")
//				.append(" ")
//				.append("patel");
//		
//		email.sendKeys(username);
	
	//3. StringBuffer:
//		StringBuffer userInfo= new StringBuffer()
//				.append("jannu")
//				.append(" ")
//				.append("babbu")
//				.append(" ")
//				.append("reddy");
//	
//		email.sendKeys(userInfo);
		
		
		//4.StringBuilder, StringBuffer, String, Keys:
		StringBuilder username= new StringBuilder()
				.append("swamy")
				.append(" ")
				.append("akula")
				.append(" ")
				.append("patel");
		
		String space=" ";
		
		StringBuffer userInfo= new StringBuffer()
				.append("jannu")
				.append(" ")
				.append("babbu")
				.append(" ")
				.append("reddy");
			
		email.sendKeys(username, space,userInfo, space, Keys.TAB);
		
	}
	

}
