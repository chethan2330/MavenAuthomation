package books;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericUtilitya.BaseClass;
import objectRepository.BooksPage;
import objectRepository.CheckoutPage;
import objectRepository.HomePage;
import objectRepository.OrderDetailsPAge;
import objectRepository.ShoppingCart;

public class Dws_TC_01Test extends BaseClass {
  @Test(priority = 1)
  public void clickOnBooksLink() throws IOException, InterruptedException {
	  
	HomePage hp=new HomePage(driver);
	hp.getBOOKSLink().click();
	
	BooksPage bp=new BooksPage(driver);
	bp.getSortBy().click();
	
	wutil.selectDropdown(futil.getDataFromProperty("lowToHigh"),bp.getSortBy());
	
	List<WebElement> pro = wutil.multi(driver,"//div[@class='product-item']");
	 
	double lowest = 10.0;
	for(WebElement op:pro) {
		String price = wutil.single(driver,"//span[@class='price actual-price']").getText().trim();
		double pri =Double.parseDouble(price);

				if(lowest<=pri) {
					lowest=pri;
					bp.getAddToCart().click();
				}
}
  }
  @Test(priority = 2)
  public void shoppingCsrt() throws IOException {
		
		HomePage hp=new HomePage(driver);
	hp.getShoppingCart().click();
		
		List<WebElement> opt = wutil.multi(driver,"//td[@class='product-picture']/..//input[@type='checkbox']");
		for(WebElement options:opt) {
			options.click();
		}
		ShoppingCart scr=new ShoppingCart(driver);
		wutil.selectDropdown(futil.getDataFromProperty("country"),scr.getCountrySelection());
		wutil.selectDropdown(futil.getDataFromProperty("state"),scr.getStateSelection());
		scr.getZipcode().sendKeys("zipcode");
		scr.getEstimationShipping().click();
		scr.getiAgreeChekbox().click();
		scr.getCheckout().click();
  }
		
		
		
	@Test(priority = 3)
	public void checkout() {
	
		CheckoutPage cp=new CheckoutPage(driver);
		cp.getBillingAddresContinue().click();
		
		cp.getShippingAddresscheckbox().click();
		cp.getShippingAddressContinue().click();
		
		cp.getPaymentMethodContinue().click();
		
		cp.getPaymentInformationContinue().click();
		
		cp.getConfirmOrderconfirmButton().click();
  }	
		@Test(priority = 4)
		public void orderDetails() throws IOException, InterruptedException {

		OrderDetailsPAge op=new OrderDetailsPAge(driver);
		op.getOrderDetails().click();
		
		
		WebElement order = wutil.single(driver,"//h3[text()='Information']");
		
		wutil.ScrollToElement(driver, order);
		Thread.sleep(5000);
		wutil.getPhoto(driver);
		
	}
  
}
