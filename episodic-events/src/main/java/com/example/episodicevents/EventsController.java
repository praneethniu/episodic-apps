package com.example.episodicevents;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class EventsController {

    private EventsRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public EventsController(EventsRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/")
    public void postEvent(@RequestBody Event event) {

        repository.save(event);

        if (event instanceof ProgressEvent) {
            EpisodeProgress episodeProgress = new EpisodeProgress(event.getUserId(),
                    event.getEpisodeId(),
                    event.getCreatedAt(),
                    ((ProgressEvent) event).getData().getOffset().intValue());

            rabbitTemplate.convertAndSend("my-exchange",
                    "my-routing-key",
                    episodeProgress);
        }

    }

    @GetMapping("/recent")
    public List<Event> getEvents() {

        List<Event> events = repository.findAll();

        return events;
    }
}
