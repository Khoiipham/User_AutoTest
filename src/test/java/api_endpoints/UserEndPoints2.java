package api_endpoints;

import api_payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;


public class UserEndPoints2 {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
   return routes;
    }

    public static ResponseBody<Response> CreateUser(User payload){
       String post_url =getURL().getString("post_url");
        Response response = given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(payload)
               .when()
               .post(post_url);
       return response;
    }

    public static ResponseBody<Response> ReadUser(String userName){
        String get_url =getURL().getString("get_url");

        Response response = given()
                .pathParam("username",userName)
                .when()
                .get(get_url);
        return response;

    }

    public static ResponseBody<Response> UpdateUser(User payload, String userName){
        String update_url =getURL().getString("update_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)

                .when()
                .put(update_url);

        return response;

    }

    public static ResponseBody<Response> DeleteUser(String userName){
        String delete_url =getURL().getString("delete_url");

        Response response = given()
                .pathParam("username",userName)
                .when()
                .delete(delete_url);
        return response;
    }
}
