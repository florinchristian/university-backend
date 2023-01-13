package dev.florinchristian.universitybackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "roomId")
    private Integer roomId;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;
}
