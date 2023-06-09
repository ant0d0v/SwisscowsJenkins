package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TestData;
import pages.top_menu.EmailPage;
import pages.top_menu.VpnPage;
import utils.ProjectConstants;

import java.util.List;

public class EmailTest extends BaseTest {
    @Test
    public void testH2TextsEmailPage() {
        final List<String> expectedH2Texts = List.of(
                "Advantages",
                "What we guarantee with Swisscows.email:",
                "Prices",
                "Install Swisscows.email",
                "FAQ and Support"

        );
        final List<String> actualH2Texts = openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .getH2Texts();
        Assert.assertTrue(actualH2Texts.size() > 0);
        Assert.assertEquals(actualH2Texts, expectedH2Texts);
    }

    @Test
    public void testEmailLogoNavigatesToBaseURL() {
        final String expectedURL = ProjectConstants.DOMAIN + "/en";
        final String expectedTitle = "Your private and anonymous search engine Swisscows";

        MainPage mainPage = openBaseURL();
        final String actualURL = mainPage
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .clickLogo()
                .getCurrentURL();

        final String actualTitle = mainPage.getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dataProvider = "EmailLinksData", dataProviderClass = TestData.class)
    public void testPriceLinksNavigateToCorrespondingPages(
            int index, String expectedTittle, String expectedUrl) {
        EmailPage emailPage = new EmailPage(getDriver());

        MainPage mainPage = openBaseURL();
        mainPage
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage();

        final String oldURL = mainPage.getCurrentURL();
        final String oldTittle = mainPage.getTitle();
        emailPage
                .clickAllLinksOnEmailPage(index);

        final String actualURL = mainPage.getFormattedURL();
        final String actualTittle = mainPage.getTitle();

        Assert.assertNotEquals(oldURL, actualURL);
        Assert.assertNotEquals(oldTittle, actualTittle);
        Assert.assertEquals(actualURL, expectedUrl);
        Assert.assertEquals(actualTittle, expectedTittle);
    }

    @Test
    public void testLinksColorsEmailPage() {
        List<String> expectedLinksColors = List.of(
                "rgba(255, 255, 255, 1)",
                "rgba(255, 255, 255, 1)",
                "rgba(223, 93, 93, 1)",
                "rgba(223, 93, 93, 1)",
                "rgba(223, 93, 93, 1)",
                "rgba(255, 255, 255, 1)",
                "rgba(255, 255, 255, 1)"
        );
        List<String> actualLinksColors = openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .getColorLinks();

        Assert.assertEquals(actualLinksColors.size(), 7);
        Assert.assertEquals(actualLinksColors, expectedLinksColors);
    }

    @Test
    public void testTextsFontSizesEmailPage() {
        final List<String> expectedH1FontSizes = List.of(
                "40px",
                "40px",
                "40px",
                "40px",
                "40px"
        );
        final List<String> actualH2FontSizes = openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .getH2FontSizes();

        Assert.assertTrue(actualH2FontSizes.size() > 0);
        Assert.assertEquals(actualH2FontSizes, expectedH1FontSizes);
    }

    @Test
    public void testStartForFreeLinkLinkNavigateToCorrespondingPage() {
        final String expectedTitle = "Register - Swisscows Accounts";
        openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .clickStartForFreeLink()
                .switchToExternalPage();

        final String actualUrl = getExternalPageURL();
        final String actualTitle = new MainPage(getDriver()).getTitle();

        Assert.assertTrue(actualUrl.contains("https://accounts.swisscows.com/register"));
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void testLoginLinkLinkNavigateToCorrespondingPage() {
        final String expectedTitle = "Login - Swisscows Accounts";
        openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .clickLoginLink()
                .switchToExternalPage();

        final String actualUrl = getExternalPageURL();
        final String actualTitle = new MainPage(getDriver()).getTitle();

        Assert.assertTrue(actualUrl.contains("https://accounts.swisscows.com/login?ReturnUrl"));
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void testSupportLinkNavigateToCorrespondingPage() {
        final String expectedTitle = "Swisscows Support (Page 1)";
        openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .clickSupportButton()
                .switchToExternalPage();

        final String actualUrl = getExternalPageURL();
        final String actualTitle = new MainPage(getDriver()).getTitle();

        Assert.assertEquals(actualUrl,"https://support.swisscows.com/swisscows-email/");
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testInstallWebLinkNavigateToCorrespondingPage() {
        final String actualUrl = openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .scrollToWhereToInstallEmail()
                .clickInstallWebLink()
                .getCurrentURL();
        final String actualTitle = getExternalPageTitle();

        Assert.assertTrue(actualUrl.contains("https://accounts.swisscows.com/login?ReturnUrl"));
        Assert.assertTrue(actualTitle.contains("Login"));
    }

    @Test
    public void testAllImageExist()  {
        final int expectedCountImages = 54;
        EmailPage emailPage = openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage();

        Assert.assertEquals(emailPage.getCountImagesOnPage(),expectedCountImages);
        Assert.assertTrue(emailPage.isLogoIconDisplayed());
        Assert.assertTrue(emailPage.allElementsDisplayed());
    }
    @Test
    public void testStartLoginAndInstallAndSupportButtonsColorsWhenHover_EmailPage() throws InterruptedException {
        EmailPage emailPage = new EmailPage(getDriver());
        final List<String> oldButtonColorsWhenHover = openBaseURL()
                .clickEmailTopMenu()
                .closeWindow()
                .switchToEmailPage()
                .getButtonStartAndInstallColors();

        final List<String> newButtonColorsWhenHover   = emailPage
                .getButtonsStartAndInstallColorsWhenHover();

        Assert.assertNotEquals(newButtonColorsWhenHover,oldButtonColorsWhenHover);
    }
   @Test
    public void testOrderByButtonsColorsWhenHover_EmailPage() throws InterruptedException {
       EmailPage emailPage = new EmailPage(getDriver());
       final List<String> oldButtonColorsWhenHover = openBaseURL()
               .clickEmailTopMenu()
               .closeWindow()
               .switchToEmailPage()
               .getButtonColors();

       final List<String> newButtonColorsWhenHover = emailPage
               .getButtonColorsWhenHover();

       Assert.assertNotEquals(newButtonColorsWhenHover, oldButtonColorsWhenHover);

   }


}
