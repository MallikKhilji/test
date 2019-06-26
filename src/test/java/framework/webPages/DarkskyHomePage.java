package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.*;

public class DarkskyHomePage extends BasePage{
    private By darkSkyAPi     = By.linkText("Dark Sky API");
    private By signupButton   = By.xpath("//a[@class='button filled']");
    private By registerButton = By.xpath("//button[contains(text(),'Register')]");
    private By registerText   = By.xpath("//h1[@class='stand-alone title']");
    private By currentTemp    = By.xpath("//span[@class='summary swap']");
    private By expandToday    = By.xpath("//a[1]//span[3]//span[1]");
    private By minTemp        = By.xpath("//*[@class='day revealed'][@data-day='0']//*[@class='minTemp']");
    private By minTemp2       = By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']//span[@class='temp']");
    private By highTemp       = By.xpath("//*[@class='day revealed'][@data-day='0']//*[@class='maxTemp']");
    private By highTemp2      = By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']//span[@class='temp']");
    private By tempList       = By.xpath("//div[@id='timeline']//div[@class='temps']//*//span");
    private By hourOfWeb      = By.xpath("//div[@class='hours']//*[contains(text(),'m' )]");





    public void clickOnDarkSkyAPi(){clickOn(darkSkyAPi);}

    public void clickOnSignupButton(){clickOn(signupButton);}

    public void clickOnRegisterButton(){clickOn(registerButton);}

    public void getRegisterText(){getTextFromElement(registerText);}

    public void tempComparison(){
        String stringCurrTemp = getTextFromElement(currentTemp);
        int currTemp = Integer.parseInt(stringCurrTemp.substring(0,2));
        List<WebElement> listOfElements = SharedSD.getDriver().findElements(tempList);
        List<Integer> result = new ArrayList<>();
        for (WebElement verify : listOfElements ){
            String strTemp = verify.getText().substring(0,verify.getText().length()-1);
            result.add(Integer.valueOf(strTemp));
        }Collections.sort(result);
        int lowTemp =result.get(0);
        int highTemp =result.get(result.size()-1);
        boolean valid = true;
        if (currTemp < lowTemp || currTemp > highTemp){
            valid = false;
        }Assert.assertTrue(valid);
    }
    public void todayScroll() throws InterruptedException {
        scrollPage("0","650");
        Thread.sleep(5000);
    }

    public void todayExpand(){clickOn(expandToday);
    }

    public void matchTemperature(){
        String minTempText =getTextFromElement(minTemp);
        String minTemp2Text =getTextFromElement(minTemp2);
        String highTempText =getTextFromElement(highTemp);
        String highTemp2Text =getTextFromElement(highTemp2);
        Assert.assertTrue(minTempText.contains(minTemp2Text));
        Assert.assertTrue(highTempText.contains(highTemp2Text));
    }

    public void timeDifference() {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat simpleDateF = new SimpleDateFormat("ha");
        List<String> sTime = new ArrayList<>();
        List<String> sHours = new ArrayList<>();
        List<WebElement> webTime = SharedSD.getDriver().findElements(hourOfWeb);
        for (WebElement result : webTime) {
            String textOfResult = result.getText().toUpperCase();
            sTime.add(textOfResult);
        }
        for (int i = 0; i < 11; i++) {
            calender.add(Calendar.HOUR, 2);
            String systemStr = simpleDateF.format(calender.getTime());
            sHours.add(systemStr);
        }
        Assert.assertEquals(sHours, sTime);
    }

}
