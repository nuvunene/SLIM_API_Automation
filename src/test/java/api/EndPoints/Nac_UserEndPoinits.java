package api.EndPoints;
import static io.restassured.RestAssured.*;

import api.PayLoad.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class Nac_UserEndPoinits {

	
	
	public static Response createUser(User payload) {
	      Response response=given()
	      .contentType(ContentType.JSON)
	      .accept(ContentType.JSON)
	      .body(payload)
	      .when()
	       .post(Nac_Routes.postuUrl);
	     return response;
	}
	public static Response getUser(String userName) {
		
		Response response = given()
		  .pathParam("username", userName)
		  .when()
		  .get(Nac_Routes.getUrl);
		return response;
		
	}
	public static Response updateUser(String userName,User payload) {
	      Response response=given()
	      .contentType(ContentType.JSON)
	      .accept(ContentType.JSON)
	      .pathParam("username", userName)
	      .body(payload)
	      .when()
	       .put(Nac_Routes.updateUrl);
	     return response;
	}
	public static Response deleteUser(String userName) {
		
		Response response = given()
		  .pathParam("username", userName)
		  .when()
		  .delete(Nac_Routes.deleteUrl);
		return response;
		
	}
}
