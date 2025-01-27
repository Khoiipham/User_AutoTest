package api_endpoints;

import api_payloads.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;



public class UserEndPoints {
    public static ResponseBody<Response> CreateUser(User payload){
       Response response = given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(payload)
               .when()
               .post(Routes.post_url);
       return response;
    }

    public static ResponseBody<Response> ReadUser(String userName){
        Response response = given()
                .pathParam("username",userName)
                .when()
                .get(Routes.get_url);
        return response;

    }

    public static ResponseBody<Response> UpdateUser(User payload, String userName){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)

                .when()
                .put(Routes.update_url);

        return response;

    }

    public static ResponseBody<Response> DeleteUser(String userName){
        Response response = given()
                .pathParam("username",userName)
                .when()
                .delete(Routes.delete_url);
        return response;
    }
}
