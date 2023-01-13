package dev.florinchristian.universitybackend.repository;


import dev.florinchristian.universitybackend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
