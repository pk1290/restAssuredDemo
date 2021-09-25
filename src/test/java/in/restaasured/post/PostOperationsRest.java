package in.restaasured.post;

import in.restaasured.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PostOperationsRest {

    @Test
    public void postDetails(){
        RestAssured.baseURI = Constants.GETURL;
        RequestSpecification httpRequest = RestAssured.given();

        String params = "title='Python'&content='Its the scripting language.It is widely used for the AI,ML techs'";

        EncoderConfig encoderconfig = new EncoderConfig();

        Response response=httpRequest.body(params)
                .config(RestAssured.config().encoderConfig(encoderconfig
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post();
        System.out.println(response.getStatusCode());
        Assert.assertTrue(Arrays.stream(Constants.RESPONSESUCCESSSTATUS)
                .anyMatch(status -> status==response.getStatusCode()));
    }
}
