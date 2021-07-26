package pl.mateusztester.tests;

import org.testng.annotations.Test;
import pl.mateusztester.pages.GmailSearchMassagePage;

public class GmailTest extends BaseTest{
    @Test
    public void GmailTestFindPerson(){
        GmailSearchMassagePage gmailSearchMassagePage = new GmailSearchMassagePage(driver);
        gmailSearchMassagePage.setAcceptCookies();
        gmailSearchMassagePage.clickAndSandKeys("poczta wp");
        gmailSearchMassagePage.loginAndPassword("Mateuszrusinowicz@wp.pl", "aisxukkms7");
        gmailSearchMassagePage.selectAllCheckbox();

    }
}
