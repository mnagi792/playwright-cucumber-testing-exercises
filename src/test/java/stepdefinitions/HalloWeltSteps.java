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

import static org.junit.Assert.assertEquals;

public class HalloWeltSteps {

    private String request_URL = "https://www.wikipedia.de/";
    private String search_term = "Hallo Welt!";

    private Playwright playwright;
    private Page page;
    private Browser browser;

    @Before
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions() .setHeadless(false) .setSlowMo(2000)
        );
        page = browser.newPage();
    }

    @Given("der Benutzer ist auf der Wikipedia-Startseite")
    public void der_benutzer_ist_auf_der_wikipedia_startseite() {
        page.navigate(request_URL);
        assertEquals(request_URL, page.url());
    }

    @When("der Benutzer nach {string} sucht")
    public void der_benutzer_nach_sucht(String string) {
        page.fill("input#txtSearch", search_term);
        page.keyboard().press("Enter");
    }
    @Then("sollte der Artikelheader {string} angezeigt werden")
    public void sollte_der_artikelheader_angezeigt_werden(String string) {
        String article_heading = page.textContent("#firstHeading");
        assertEquals(search_term, article_heading);
    }

    @After
    public void teardown(){
        browser.close();
        playwright.close();
    }
}
