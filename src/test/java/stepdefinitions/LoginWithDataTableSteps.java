package stepdefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoginWithDataTableSteps{

    private Playwright playwright;
    private Page page;
    private Browser browser;
    private String request_URL = "https://www.saucedemo.com/";

    @Before
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions() .setHeadless(false) .setSlowMo(2000)
        );
        page = browser.newPage();
    }

    @Given("Benutzer ist auf der Sauce-Labs Seite")
    public void userOnHomePage() {
        page.navigate(request_URL);
    }

    @When("der Benutzer gültige Anmeldedaten eingibt")
    public void entersValidCredential(DataTable dataTable) throws InterruptedException{
        System.out.println("Credentials Entered");
        List<List<String>> signUpForm = dataTable.asLists(String.class);
        List<String> credentials = signUpForm.get(0);
        String userName = credentials.get(0);
        String passWord = credentials.get(1);
        page.fill("#user-name", userName);
        page.fill("#password ", passWord);
        page.click("#login-button");
    }

    @Then("sollte sich der Benutzer erfolgreich anmelden können und eine neue Seite öffnen")
    public void successfulLogin() throws InterruptedException {
        assertEquals(page.url(), request_URL + "inventory.html");
    }

    @After
    public void teardown(){
        browser.close();
        playwright.close();
    }

}