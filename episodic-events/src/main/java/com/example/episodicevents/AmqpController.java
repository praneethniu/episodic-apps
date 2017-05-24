package com.example.episodicevents;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amqp")
public class AmqpController {

    private final RabbitTemplate rabbitTemplate;

    public AmqpController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public void publishEvent(@RequestBody EpisodeProgress body){
        //rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", new EpisodeProgress(body));
    }
}