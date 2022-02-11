package automation.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;
import automatation.utilities.ExcelUtils;

public class CovidSafePunePage extends BaseClass {

	String path = ".//Data//HospitalDetails.xlsx";

	@FindBy(xpath = "//button[contains(text(),'Clear Filters')]")
	WebElement ClearFilter;

	@FindBy(xpath = "//button[contains(text(),'Available Only')]")
	WebElement AvailableOnly;

	@FindBy(xpath = "//button[contains(text(),'With Oxygen')]")
	WebElement WithOxygen;

	int counter = 0;

	public CovidSafePunePage() {
		PageFactory.initElements(driver, this);
	}

	public void searchOptions() throws InterruptedException {
		ClearFilter.click();
		WithOxygen.click();

	}

	public void getData() throws InterruptedException, IOException {

		ExcelUtils ExcelUtil = new ExcelUtils(path);
		ExcelUtil.setCellData("Sheet1", 0, 0, "Description");
		ExcelUtil.setCellData("Sheet1", 0, 1, "BedWithOxygen");
		ExcelUtil.setCellData("Sheet1", 0, 2, "State");
		ExcelUtil.setCellData("Sheet1", 0, 3, "Address");
		ExcelUtil.setCellData("Sheet1", 0, 4, "PhoneNumber");
		ExcelUtil.setCellData("Sheet1", 0, 5, "AddedOn");
		ExcelUtil.setCellData("Sheet1", 0, 6, "ModifiedOn");

		// Scroll to the Home
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.HOME).build().perform();

		// To find the No.of row
		int rownum = driver.findElements(By.xpath("//tbody//tr")).size();

		int colnum = driver.findElements(By.xpath("//tbody//tr[1]//td")).size();// To find the no of column in odd row

		int k = 1;
		for (int i = 1; i <= rownum; i++) {

			WebElement element = driver.findElement(
					By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/table/tbody/tr[" + i
							+ "]/td[1]/div/button"));

			// To scroll vertically to element
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
			element.click();

			String Description = (driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[1]")).getText());
			// String woutOxy = (driver.findElement(By.xpath("//tbody//tr[" + i +
			// "]//td[2]")).getText());
			String withOxy = (driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[3]")).getText());
			// String woutVenti = (driver.findElement(By.xpath("//tbody//tr[" + i +
			// "]//td[4]")).getText());
			// String withVenti = (driver.findElement(By.xpath("//tbody//tr[" + i +
			// "]//td[5]")).getText());
			System.out.println(Description + withOxy);

			// To read data from tr+1
			int a = i + 1;

			String ModifiedOn = (driver.findElement(By.xpath("//tbody/tr[" + a + "]/td/p[1]/span[1]")).getText());
			String Phone = (driver.findElement(By.xpath("//tbody/tr[" + a + "]/td/p[2]/span[1]")).getText());
			String Pincode = (driver.findElement(By.xpath("//tbody/tr[" + a + "]/td/p[3]/span[1]")).getText());
			String Address = (driver.findElement(By.xpath("//tbody/tr[" + a + "]/td/p[4]/span[1]")).getText());

			System.out.println("ModifiedOn " + ModifiedOn);
			System.out.println("Phone " + Phone);
			System.out.println("Pincode " + Pincode);
			System.out.println("Address " + Address);

			// To write data in excel file
			ExcelUtil.setCellData("Sheet1", k, 0, Description);
			ExcelUtil.setCellData("Sheet1", k, 1, withOxy);
			ExcelUtil.setCellData("Sheet1", k, 2, "Maharashtra");
			ExcelUtil.setCellData("Sheet1", k, 3, Address);
			ExcelUtil.setCellData("Sheet1", k, 4, Phone);
			ExcelUtil.setCellData("Sheet1", k, 6, ModifiedOn);

			System.out.println("value of k " + k);

			// To close expand button
			element.click();

			k++;

		}

	}

	public void PageLoad() throws InterruptedException {

		while (true) {
			Thread.sleep(2000);
			try {
				WebElement element = driver
						.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/button[1]"));

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
				Thread.sleep(1000);
				element.click();
				counter++;

			} catch (NoSuchElementException e) {
				System.out.println(counter);
				break;

			}

		}

	}

}
