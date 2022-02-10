package automatation.testcases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import automation.pages.CovidSafePunePage;

public class CovidSafePuneTest extends BaseClass {

	@BeforeTest
	public void Setup() throws InterruptedException {
		launchurl();

	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void SearchOption() throws InterruptedException {
		CovidSafePunePage CPune = new CovidSafePunePage();
		CPune.searchOptions();

	}

	@Test(priority = 2)
	public void PageLoad() throws InterruptedException {
		CovidSafePunePage CPune = new CovidSafePunePage();
		CPune.PageLoad();
	}

	@Test(priority = 3)
	public void getData() throws InterruptedException, IOException {
		CovidSafePunePage CPune = new CovidSafePunePage();
		CPune.getData();

	}

	@Test(priority = 4)
	public void TrialLoop() throws InterruptedException, IOException {
		CovidSafePunePage CPune = new CovidSafePunePage();
		CPune.TrialLoop();

	}

}
