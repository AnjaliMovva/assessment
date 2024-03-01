package com.test.assessment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingAppTest {

    @Test
    public void testBooking() throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/bookings");
        StringEntity entity = new StringEntity("{\"customerName\":\"John Doe\",\"tableSize\":4,\"date\":\"2024-02-29\",\"time\":\"12:00\"}");
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testGetBookingsForDay() throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:8080/bookings?date=2024-02-29");
        HttpResponse response = client.execute(get);
        assertEquals(200, response.getStatusLine().getStatusCode());
        // Parse the response JSON and verify the content
    }
}
