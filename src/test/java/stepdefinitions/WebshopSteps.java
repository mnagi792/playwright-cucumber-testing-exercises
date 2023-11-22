package stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebshopSteps {

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

    @Given("der Benutzer ist mit gültigen Anmeldedaten angemeldet")
    public void der_benutzer_ist_mit_den_anmeldedaten_und_angemeldet(DataTable dataTable) {
        // Benutzer ist auf der Sauce-Labs Seite
        page.navigate(request_URL);

        // Benutzer gültige Anmeldedaten eingibt
        System.out.println("Credentials Entered");
        List<List<String>> signUpForm = dataTable.asLists(String.class);
        List<String> credentials = signUpForm.get(0);
        String userName = credentials.get(0);
        String passWord = credentials.get(1);
        page.fill("#user-name", userName);
        page.fill("#password ", passWord);
        page.click("#login-button");

        //sollte sich der Benutzer erfolgreich anmelden können und eine neue Seite öffnen
        assertEquals(page.url(), request_URL + "inventory.html");
    }

    @Given("der Nutzer betrachtet ein Produkt {string}")
    public void der_nutzer_betrachtet_ein_produkt(String string) {
        Locator locator = page.locator(".inventory_item_name").filter(new Locator.FilterOptions().setHasText("Sauce Labs " + string));
        locator.click();
    }

    @When("der Benutzer auf die Schaltfläche Add to Cart für das Produkt {string} klickt")
    public void der_benutzer_auf_die_schaltfläche_für_das_produkt_klickt(String string) {
        page.click("#add-to-cart-sauce-labs-" + string);
    }
    @Then("Dann sollte das Produkt {string} zum Warenkorb des Benutzers hinzugefügt werden")
    public void dann_sollte_das_produkt_zum_warenkorb_des_benutzers_hinzugefügt_werden(String product) {
        page.click(".shopping_cart_link");

        Locator locator = page.locator(".inventory_item_name").filter(new Locator.FilterOptions().setHasText("Sauce Labs " + product));
        assertTrue(locator.isVisible());
    }


    @After
    public void teardown(){
        browser.close();
        playwright.close();
    }

}
