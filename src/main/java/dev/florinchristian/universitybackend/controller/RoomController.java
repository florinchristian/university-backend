package dev.florinchristian.universitybackend.controller;

import dev.florinchristian.universitybackend.model.Room;
import dev.florinchristian.universitybackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/rooms")
    public Room createRoom(@RequestBody @NonNull Room room) {
        roomRepository.saveAndFlush(room);
        return room;
    }
}
