import org.testng.Assert;
import org.testng.annotations.Test;
import pomp_pages.RegistrationPage;

public class RegistrationTest extends BaseClass {
    @Test(description = "Register a new user and select Calley Teams plan")
    public void registerNewCalleyTeamsUser() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String email = uniqueEmail(getData("userEmail"));

        registrationPage.open(getData("registrationUrl"));
        registrationPage.register(
                getData("userName"),
                email,
                getData("userPhone"),
                getData("userPassword"),
                getData("companyName")
        );

        Assert.assertTrue(registrationPage.isRegistrationSuccessful(), "User registration was not successful.");
    }
}
