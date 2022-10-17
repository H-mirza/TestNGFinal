package page;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryListPage {
	
	WebDriver driver; 
	
	public CategoryListPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	//Web Element 
	@FindBy(how = How.CSS, using = "categorydata")WebElement CATEGORY_BOX_ELEMENT;
	@FindBy(how = How.CSS, using = "input[type='submit'][value='Add category']")WebElement SUBMIT_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using ="//*[contains(text(),'The category you want to add already exists:')]")WebElement DUPLICATE_CATEGORY_ELEMENT;
	@FindBy(how = How.NAME, using = "due_month")WebElement MONTH_DROPDOWN_ELEMENT;
	
	//Methods
	
	
	public List<String> getlistof() {
		List<String> list = new ArrayList<String>();
		
		List<WebElement> categoryDataElements = driver.findElements(By.xpath("//a[@title='Remove this category']")); 
	
		for (int i = 0; i < categoryDataElements.size(); i++) {
			list.add(i, categoryDataElements.get(i).getText());
			
		}
		
		return list;
	}

	public void addCategory(String category) {
		CATEGORY_BOX_ELEMENT.sendKeys(category);
		SUBMIT_BUTTON_ELEMENT.click();
		
	}
	
	
	public boolean duplicateMessageDisplayStatus() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(DUPLICATE_CATEGORY_ELEMENT));
			return true;
			}catch(Exception e) {
				
			}
		
		
		return false;
		
	}
	
	public List<String> getMonthList() {
		
		List<String> actualDropDownValues = new ArrayList<String>();
		
		Select dropDown = new Select(MONTH_DROPDOWN_ELEMENT);
		List<WebElement> dropDownValues = dropDown.getOptions();
		
		for (int i=0; i<dropDownValues.size(); i++) {
			actualDropDownValues.add(i, dropDownValues.get(i).getText());
			
		}
		
		return actualDropDownValues;
	}

	
	
}