package edu.matc.entjava.persistence;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entjava.org.themoviedb.MediaPage;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * The type Test service client.
 */
public class TestServiceClient {

    /**
     * Testswapi json.
     *
     * @throws Exception the exception
     */
    @Test
    public void testswapiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/trending/tv/week")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //Mapping objects from the api
        ObjectMapper mapper = new ObjectMapper();
        MediaPage<TVItem> mediaPage = mapper.readValue(
                response,
                mapper.getTypeFactory().constructParametricType(MediaPage.class, TVItem.class)
        );
        TVItem item = mediaPage.getResults().get(0);
        String expectedMediaName = "JoJo's Bizarre Adventure";
        String expectedMediaType = "tv";
        String expectedMediaLanguage = "ja";
        //assertEquals(expectedMediaName, item.getName());
        //assertEquals(expectedMediaLanguage, item.getOriginalLanguage());
        //assertEquals(expectedMediaType, item.getMediaType());

        //assertEquals("???", response);
       //assertTrue(response.contains("results"));
    }

    @Test
    public void testmediaDetailJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/343611")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //Mapping objects from the api
        ObjectMapper mapper = new ObjectMapper();
        MovieItem movie = mapper.readValue(response, MovieItem.class);
        String expectedMediaName = "JoJo's Bizarre Adventure";
        String expectedMediaType = "tv";
        String expectedMediaLanguage = "ja";
        //assertEquals(expectedMediaName, item.getName());
        //assertEquals(expectedMediaLanguage, item.getOriginalLanguage());
        //assertEquals(expectedMediaType, item.getMediaType());

        //assertEquals("???", movie);
        //assertTrue(response.contains("results"));
    }


}