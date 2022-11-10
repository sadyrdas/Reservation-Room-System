package cz.cvut.kbss.ear.mroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication()
@RestController
public class MeetingRoomReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomReservationApplication.class, args);
    }

    @GetMapping("/hello")
    public List<String> hello() {
        return List.of("Hello", "World");
    }

}
