package com.example.episodicevents;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventsController {

    private EventsRepository repository;

    public EventsController(EventsRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public void postEvent(@RequestBody Event event) {

        repository.save(event);
    }

    @GetMapping("/recent")
    public List<Event> getEvents() {

        List<Event> events = repository.findAll();

        return events;
    }
}
