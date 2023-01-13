package dev.florinchristian.universitybackend.repository;

import dev.florinchristian.universitybackend.model.booking.Booking;
import dev.florinchristian.universitybackend.model.booking.BookingID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BookingID> {

}
