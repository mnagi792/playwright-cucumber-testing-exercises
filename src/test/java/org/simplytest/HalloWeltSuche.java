package org.simplytest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HalloWeltSuche {

    @Test
    public void searchHelloWorld() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions() .setHeadless(false) .setSlowMo(2000)
        );
        Page page = browser.newPage();

        String request_URL = "https://www.wikipedia.de/";
        String search_term = "Hallo Welt!";

        page.navigate(request_URL);
        assertEquals(request_URL, page.url());

        page.fill("input#txtSearch", search_term);
        page.keyboard().press("Enter");

        // alternative
        // page.click("#cmdSearch");

        String article_heading = page.textContent("#firstHeading");
        assertEquals(search_term, article_heading);
        playwright.close();
    }
}
