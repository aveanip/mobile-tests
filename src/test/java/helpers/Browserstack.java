package helpers;


import static io.restassured.RestAssured.given;

public class Browserstack {


    public static String videoUrl(String sessionId){
        String url = String.format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .auth().basic("mimimurmur_j6r0lN", "y3exSpxS3Qf8e7pz53Jv")
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");

  }
}
