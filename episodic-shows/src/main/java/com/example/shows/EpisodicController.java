package com.example.shows;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class EpisodicController {

    private EpisodeRepository episodeRespository;

    public EpisodicController(EpisodeRepository episodeRespository) {
        this.episodeRespository = episodeRespository;
    }

    @GetMapping("/shows/{id}/episodes")
    public List<EpisodeWithTitle> getEpisodes(@PathVariable Long id) {
        List<Episode> episodes = new ArrayList();

        episodeRespository.findAll()
                .forEach(episodes::add);

        return episodes.stream()
                .filter(episode -> episode.getShow_id() == id)
                .map(episode -> {
                    EpisodeWithTitle title = new EpisodeWithTitle();
                    title.setEpisode(episode);
                    return title;
                })
                .collect(toList());

    }

    @PostMapping("/shows/{id}/episodes")
    public EpisodeWithTitle createEpisode(@RequestBody Episode episode, @PathVariable Long id) {

        episode.setShow_id(id);
        episodeRespository.save(episode);

        EpisodeWithTitle episodeWithTitle = new EpisodeWithTitle();
        episodeWithTitle.setEpisode(episode);
        return episodeWithTitle;
    }
}
