package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.DarkskyHomePage;
import org.testng.Assert;

public class DarkskySD {

    private DarkskyHomePage darkskyHomePage = new DarkskyHomePage();

    @Given("^i am on Darksky page$")
    public void iAMOnDarkSkyHomePage(){
        Assert.assertEquals(SharedSD.getDriver().getCurrentUrl(),"https://darksky.net/forecast/40.751,-73.989/us12/en","invalid home page");
    }

    @When("^I am on the darksky Register page$")
    public void darkSkyRegisterPAge()
    { darkskyHomePage.clickOnDarkSkyAPi();
        darkskyHomePage.clickOnSignupButton(); }


        @When("^I click on Register button$")
        public void clickOnRegisterButton(){
        darkskyHomePage.clickOnRegisterButton();
        }
        @Then("^I verify register message$")
    public void registerMessage(){
        Assert.assertEquals(SharedSD.getDriver().getCurrentUrl(),"https://darksky.net/dev/register","invalid registerpage");

        }
        @Then ("^I verify current temp is not greater or less then temps from daily timeline$")
        public void tempVerification(){
        darkskyHomePage.tempComparison();
        }

        @When("^I expand todays timeline$")
        public void verifyExpand() throws InterruptedException {
        darkskyHomePage.todayScroll();
        darkskyHomePage.todayExpand();
        }
        @Then("^I verify lowest and highest temp is displayed correctly$")
        public void verifyTemp(){
        darkskyHomePage.matchTemperature();
        }

        @Then("^I verify timeline is displayed with two hours incremented$")
        public void incrementTime(){
        darkskyHomePage.timeDifference();
        }





}
