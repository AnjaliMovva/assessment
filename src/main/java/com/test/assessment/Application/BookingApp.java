package com.test.assessment.Application;
import io.muserver.*;
import com.google.gson.Gson;
import com.test.assessment.model.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * The task is to create a simple restaurant booking application in Java and implement the two user stories below.

 User Story 1: As a customer I want to be able to request a booking at this restaurant.
 User Story 2: As the restaurant owner I want to be able to see all bookings for a particular day.

Assume each booking has a customer name, table size, date and time. Assume time slots are for 2 hours.
The only technical requirement is to use https://muserver.io/ to implement a RESTful API. (You may not be familiar with this library, but that is part of the challenge).
We would like you to also think about how you can check the correctness of your code.
Feel free to use any additional preferred libraries as required, for example for JSON parsing.
Also feel free to make any other assumptions and document them in the code. The focus is on simplicity!
Expected effort is 1 - 2 hours. With that in mind, we’re not expecting the solution to be perfect or even complete, but it would be good if we can run it and see some output. We’re not testing ability to code, but how a problem is approached and how you adapt to unfamiliar tools.
Please upload the code to a publicly accessible Github repo and send us the link.*/

public class BookingApp {
    private static final Gson gson = new Gson();
    private static final Map<String, List<Booking>> bookingsByDate = new HashMap<>();

    public static void main(String[] args) {
        MuServer server = MuServerBuilder.httpServer().withHttpPort(8080)
            .addHandler(Method.POST, "/bookings", (request, response, pathParams) -> {
                Booking booking = gson.fromJson(request.readBodyAsString().toString(), Booking.class);
                requestBooking(booking);
                response.write("Booking successful");
            })
            .addHandler(Method.GET, "/bookings", (request, response, pathParams) -> {
                String date = (String) request.query().get("date", "date");
                List<Booking> bookings = getBookingsForDay(date);
                response.write(gson.toJson(bookings));
            })
            .start();
        System.out.println("Started server at " + server.uri());
    }

    private static void requestBooking(Booking booking) {
        String date = booking.getDate();
        if (!bookingsByDate.containsKey(date)) {
            bookingsByDate.put(date, new ArrayList<>());
        }
        bookingsByDate.get(date).add(booking);
    }

    private static List<Booking> getBookingsForDay(String date) {
        return bookingsByDate.getOrDefault(date, new ArrayList<>());
    }
}
