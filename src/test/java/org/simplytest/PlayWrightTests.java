package org.simplytest;

import com.microsoft.playwright.*;
import org.junit.Test;

public class PlayWrightTests {

    @Test
    public void startBrowser(){
        Playwright pw = Playwright.create();
        Browser browser = pw.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();
        page.navigate("https://playwright.dev");
        System.out.println("Page title: " + page.title());
    }

    @Test
    public void startBrowserIncognito(){
        Playwright pw = Playwright.create();
        Browser browser = pw.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        // Browser in isolierter Session starten
        // um sicher zu stellen, das jeder Test einen eignen
        // Context hat (Cookies, Sessions, Cache usw..)
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
    }

}
