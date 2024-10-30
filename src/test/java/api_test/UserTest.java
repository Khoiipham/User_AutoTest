package api_test;

import api_endpoints.UserEndPoints;
import api_endpoints.UserEndPoints2;
import api_payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTest {
    public Logger logger;
    public Faker faker;
    public User userPayload;
    @BeforeTest
    public void setup() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test (priority =1)
    public void testPostUser() {
        logger.info("----------Creating User----------");
        Response response = (Response) UserEndPoints2.CreateUser(userPayload);
        response.then().log().all();
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("----------User is created----------");
    }
    @Test (priority =2)
    public void testGetUserByName() {
        logger.info("----------Reading User info----------");
        Response response = (Response) UserEndPoints2.ReadUser(this.userPayload.getUsername());
        response.then().log().all();
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("----------User info is displayed----------");
    }
    @Test (priority =3)
    public void testUpdatetUserByName() {
        logger.info("----------Updating User----------");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = (Response) UserEndPoints2.UpdateUser(userPayload,this.userPayload.getUsername());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("----------Use is Updated----------");
        //check data after update
        Response responseAfter = (Response) UserEndPoints2.ReadUser(this.userPayload.getUsername());
        responseAfter.then().log().all();
        System.out.println("Response Body: " + responseAfter.getBody().asString());
        Assert.assertEquals(responseAfter.getStatusCode(), 200);
    }


    @Test (priority =4)
    public void testDeleteUserByName() {
        logger.info("----------Deleting User----------");
        Response response = (Response) UserEndPoints2.DeleteUser(this.userPayload.getUsername());
        response.then().log().all();
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("----------User Deleted----------");
    }
}
