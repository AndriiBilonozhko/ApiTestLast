package Tests;

import framework.model.ErrorModel;
import framework.model.UserModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static framework.model.endpoint.UserEndPoint.CREATE_USER;
import static framework.model.endpoint.UserEndPoint.GET_USER_BY_NAME;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTests extends BaseTest {

@Test
    public void createAndGetUser(){

    UserModel userModel = UserModel.builder()
            .id(12312)
            .username("user1")
            .firstName("art")
            .lastName("qwe")
            .email("asdsad@sad")
            .password("12345")
            .phone("23112312")
            .userStatus(1)
            .build();

    given()
            .body(userModel)
            .when()
            .post(CREATE_USER)
            .then()
            .statusCode(200);


UserModel usersModel =given()
        .pathParam("username", "user1")
         .when()
            .get(GET_USER_BY_NAME )
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(UserModel.class);
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(usersModel.getUsername()).as("Wrong name").isEqualTo("user1");
    softly.assertAll();
}

@Test
    public void getUserName(){

    ErrorModel actualErrorModel =given()
            .pathParam("username", "user8877")
            .when()
            .get(GET_USER_BY_NAME )
            .then()
            .statusCode(404)
            .extract()
            .body()
            .as(ErrorModel.class);

    ErrorModel expectedErrorModel = ErrorModel.builder()
            .code(1)
            .type("error")
            .message("User not found").build();

    assertThat(actualErrorModel).isEqualTo(expectedErrorModel);

}
}
