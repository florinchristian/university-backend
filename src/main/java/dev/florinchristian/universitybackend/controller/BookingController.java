package dev.florinchristian.universitybackend.controller;

import dev.florinchristian.universitybackend.model.booking.Booking;
import dev.florinchristian.universitybackend.model.booking.BookingID;
import dev.florinchristian.universitybackend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/bookings/available/v1")
    public boolean roomAvailable(
            @RequestParam(name = "roomId") Integer roomId,
            @RequestParam(name = "startTime") String _startTime,
            @RequestParam(name = "endTime") String _endTime
    ) {
        BookingID bid = new BookingID();

        bid.setStartTime(_startTime);
        bid.setEndTime(_endTime);

        List<BookingID> ids = new ArrayList<>() {
            {
                add(bid);
            }
        };

        List<Booking> bookings = bookingRepository.findAllById(ids);

        LocalDateTime targetStartTime = LocalDateTime.parse(_startTime);
        LocalDateTime targetEndTime = LocalDateTime.parse(_endTime);

        for (Booking booking: bookings) {
            if (!booking.getId().equals(roomId))
                continue;

            LocalDateTime startTime = LocalDateTime.parse(booking.getStartTime().replace(' ', 'T'));
            LocalDateTime endTime = LocalDateTime.parse(booking.getEndTime().replace(' ', 'T'));

            int left = targetStartTime.compareTo(startTime);
            int right = targetStartTime.compareTo(endTime);

            if (left > 0 && right < 0)
                return false;

            left = targetEndTime.compareTo(startTime);
            right = targetEndTime.compareTo(endTime);

            if (left > 0 && right < 0)
                return false;
        }

        return true;
    }

    @PostMapping("/bookings")
    public Booking registerBooking(@RequestBody @NonNull Booking booking) {
        bookingRepository.saveAndFlush(booking);
        return booking;
    }
}
