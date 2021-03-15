package com.util;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class OTPHandle {
	
	public static final String ACCOUNT_SID="ACfff5a158356d6d748689bf3689678787";
	public static final String AUTH_TOKEN = "f9ae613b483cfceac07cf2d4e7dd6bd5";
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Swamyshiva\\swamyshiva\\chromedriver\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.amazon.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Actions action= new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("#nav-link-accountList"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();
		
		driver.findElement(By.id("ap_customer_name")).sendKeys("swamyshiva");
		driver.findElement(By.id("ap_email")).sendKeys("swamyakula2@gmail.com");
		driver.findElement(By.id("ap_password")).sendKeys("lavi8978953782");
		driver.findElement(By.id("ap_password_check")).sendKeys("lavi8978953782");
		driver.findElement(By.id("continue")).click();
		
		//get the otp using twilio api
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		System.out.println(smsBody);
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		System.out.println(OTPNumber);
		
		//driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);
		
	}
	
	public static String getMessage() {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+13343734019")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}

}
