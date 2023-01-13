package dev.florinchristian.universitybackend.model.booking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@IdClass(BookingID.class)
public class Booking {

    @Getter
    @Setter
    @Column(name = "id")
    private Integer id;

    @Getter
    @Setter
    @Id
    @Column(name = "start_time")
    private String startTime;

    @Getter
    @Setter
    @Id
    @Column(name = "end_time")
    private String endTime;
}
