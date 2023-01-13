package dev.florinchristian.universitybackend.controller;

import dev.florinchristian.universitybackend.model.Booking;
import dev.florinchristian.universitybackend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/bookings/byRoom")
    public List<Booking> getBookingsByRoom(@RequestParam(name = "roomId") Integer roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    @GetMapping("/bookings/available/v1")
    public boolean roomAvailable(
            @RequestParam(name = "roomId") Integer roomId,
            @RequestParam(name = "startTime") String bookingStartTime,
            @RequestParam(name = "endTime") String bookingEndTime
    ) {
        List<Booking> roomBookings = bookingRepository.findByRoomId(roomId);

        LocalDateTime bookingStartDate = LocalDateTime.parse(bookingStartTime);
        LocalDateTime bookingEndDate = LocalDateTime.parse(bookingEndTime);

        return roomBookings.stream().noneMatch(booking -> {
            LocalDateTime startTime = LocalDateTime.parse(booking.getStartTime().replace(' ', 'T'));
            LocalDateTime endTime = LocalDateTime.parse(booking.getEndTime().replace(' ', 'T'));

            if(bookingStartDate.equals(startTime) && bookingEndDate.equals(endTime))
                return true;

            if (bookingStartDate.equals(startTime) || bookingEndDate.equals(endTime))
                return true;

            return (
                    (bookingStartDate.isAfter(startTime) && bookingStartDate.isBefore(endTime))
                    ||
                    (bookingEndDate.isAfter(startTime) && bookingEndDate.isBefore(endTime))
            );
        });
    }

    @PostMapping("/bookings/v1")
    public Booking registerBooking(@RequestBody @NonNull Booking booking) {
        bookingRepository.saveAndFlush(booking);
        return booking;
    }
}
