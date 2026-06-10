import org.testng.Assert;
import org.testng.annotations.Test;
import pomp_pages.AgentPage;
import pomp_pages.CSVUploadPage;
import pomp_pages.DashboardPage;
import pomp_pages.LoginPage;

public class FullSetupTest extends BaseClass {
    @Test(description = "Login, add an agent, and upload CSV through Power Import")
    public void completeCalleyTeamsSetup() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        AgentPage agentPage = new AgentPage(driver);
        CSVUploadPage csvUploadPage = new CSVUploadPage(driver);

        loginPage.open(getData("loginUrl"));
        loginPage.login(getData("userEmail"), getData("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful.");
        Assert.assertTrue(dashboardPage.isLoaded(), "Dashboard did not load.");

        dashboardPage.openAgentsPage();
        agentPage.addAgent(
                getData("agentName"),
                uniqueEmail(getData("agentEmail")),
                getData("agentPhone"),
                getData("agentPassword")
        );
        Assert.assertTrue(agentPage.isAgentAdded(), "Agent was not added successfully.");

        dashboardPage.openPowerImportPage();
        csvUploadPage.uploadList(getData("listName"), getAbsolutePath(getData("csvPath")));
        Assert.assertTrue(csvUploadPage.isUploadSuccessful(), "CSV upload/import was not successful.");
    }
}
