package Tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured
                .filters(new RequestLoggingFilter(),
                        new ResponseLoggingFilter());

        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri("https://petstore.swagger.io")
                        .setBasePath("/v2/")
                        .setAccept(ContentType.JSON)
                        .setContentType(ContentType.JSON)
                        .build();
    }

}
