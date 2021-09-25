package in.restaasured.get;

import in.restaasured.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Objects;

public class GetOperationRest {
    private Response response;
    @Test
    public void getDetails(){
        RestAssured.baseURI = Constants.GETURL;
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/");
//        System.out.println("Response String: "+ response.getBody().asString());
        System.out.println("Response Status: "+ response.getStatusLine());
        System.out.println("Content Type: "+ response.getContentType());
        System.out.println("Session Id: "+ response.getSessionId());
        Assert.assertTrue(Arrays.stream(Constants.RESPONSESUCCESSSTATUS)
                .anyMatch(status -> status==response.getStatusCode()));
        /**Headers allHeaders = response.getHeaders();
        for(Header header : allHeaders){
            System.out.println("keys:"+ header.getName()+", value:"+ header.getValue());
        }

        System.out.println(response.getHeader(Constants.GETCONTENTTYPEHEADER));**/

        JsonPath jsonPath = response.jsonPath();
//        System.out.println("response title: "+jsonPath.get("title")+", response Content:"+ jsonPath.get("content"));
        for(Object val: jsonPath.getList("title")){
            Assert.assertTrue(Arrays.stream(Constants.RESPONSETITLES).
                    anyMatch(title -> Objects.equals(title, val.toString())));
        }
    }
}
