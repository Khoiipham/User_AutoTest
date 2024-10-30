package api_test;

import api_endpoints.UserEndPoints;
import api_payloads.User;
import api_utilities.DataProviders;
import api_utilities.XLUtilities;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DPTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String phone) throws Exception {

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response = (Response) UserEndPoints.CreateUser(userPayload);
        response.then().log().all();
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) throws Exception {
        Response response = (Response) UserEndPoints.DeleteUser(userName);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}