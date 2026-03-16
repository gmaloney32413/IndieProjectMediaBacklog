package edu.matc.entjava.persistence;


import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestServiceClient {

    @Test
    public void testswapiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/trending/all/day")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        //assertEquals("???", response);
        assertTrue(response.contains("results"));
    }
}