package org.example.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressBookApiIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    ObjectMapper om = new ObjectMapper();

    private String url(String path) { return "http://localhost:" + port + path; }

    @Test
    void createBook_addBuddy_fetchBook_roundtrip() throws Exception {
        ResponseEntity<String> createdBook = http.postForEntity(url("/addressbook/newbook"), null, String.class);
        assertTrue(createdBook.getStatusCode().is2xxSuccessful(), "create book should succeed");
        JsonNode created = om.readTree(createdBook.getBody());
        long bookId = created.has("id") ? created.get("id").asLong() : // JSON if your controller returns it
                Long.parseLong(createdBook.getBody().replaceAll("\\D+","")); // fallback if plain text id
        assertTrue(bookId > 0);

        String buddyJson = """
                { "name": "Nina", "phone": "+15559990100", "address": "123 Maple St" }""";
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> addResp = http.postForEntity(
                url("/addressbook/" + bookId + "/add"),
                new HttpEntity<>(buddyJson, h),
                String.class);
        assertTrue(addResp.getStatusCode().is2xxSuccessful());

        ResponseEntity<String> getResp = http.getForEntity(url("/addressbook/" + bookId), String.class);
        assertTrue(getResp.getStatusCode().is2xxSuccessful(), "get book should succeed");
        JsonNode book = om.readTree(getResp.getBody());
        assertTrue(book.has("buddies") && book.get("buddies").isArray(), "book should contain buddies array");

        boolean found = false;
        for (JsonNode b : book.get("buddies")) {
            if ("Nina".equals(b.get("name").asText())) {
                found = true;
                assertEquals("+15559990100", b.get("phone").asText());
                if (b.has("address")) {
                    assertEquals("123 Maple St", b.get("address").asText());
                }
            }
        }
        assertTrue(found, "Nina should be in the buddies list");
    }
}
