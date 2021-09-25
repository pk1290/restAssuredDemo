package in.restaasured.delete;

import in.restaasured.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class deleteOperationRest {

    @Test
    public void deleteOp(){
        RestAssured.baseURI = Constants.GETURL;

        RequestSpecification httpGet = RestAssured.given();

        JsonPath jsonPath = httpGet.request(Method.GET,"/")
                .getBody()
                .jsonPath();

        RequestSpecification httpDelete = RestAssured.given();

        RestAssured.baseURI = Constants.DELETEURL;

        Response response=httpDelete
                .queryParam("title", jsonPath.getList("title").get(0))
                .delete().andReturn();

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertTrue(Arrays.stream(Constants.RESPONSESUCCESSSTATUS)
                .anyMatch(status -> status.equals(response.getStatusCode())));
    }
}
