package com.example.viewings;


import com.example.shows.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class ViewingsController {

    private ViewingsRepository viewingsRepository;
    private EpisodeRepository episodeRepository;
    private ShowRepository showRepository;

    public ViewingsController(ViewingsRepository viewingsRepository, EpisodeRepository episodeRepository, ShowRepository showRepository)
    {
        this.viewingsRepository = viewingsRepository;
        this.episodeRepository = episodeRepository;
        this.showRepository = showRepository;
    }

    @GetMapping("/users/{id}/recently-watched")
    public List<ViewingResponse> getViewings(@PathVariable Long id) {



        List<Viewings> viewings = fetchViewings();


      return  viewings.stream()
                .map(viewing -> {
                    ViewingResponse response = new ViewingResponse();

                    response.setTimecode(viewing.getTimecode());
                    response.setUpdatedAt(viewing.getUpdatedAt());
                    Show show = fetchShow(viewing.getShowId());
                    response.setShow(show);

                    Episode episode = fetchEpisode(viewing.getEpisodeId());

                    EpisodeWithTitle episodeWithTitle = new EpisodeWithTitle();
                    episodeWithTitle.setEpisode(episode);

                    response.setEpisode(episodeWithTitle);


                    return response;
                })
                .collect(toList());

    }



    @PatchMapping("/users/{id}/viewings")
    public HttpStatus createViewing(@RequestBody ViewingRequest request,
                                    @PathVariable Long id) {

        Viewings viewings = new Viewings();
        viewings.setUserId(id);
        viewings.setEpisodeId(request.getEpisodeId());
        viewings.setShowId(fetchEpisode(request.getEpisodeId()).getShow_id());
        viewings.setUpdatedAt(request.getUpdatedAt());
        viewings.setTimecode(request.getTimecode());
        viewingsRepository.save(viewings);

       return HttpStatus.OK;
    }

    private Episode fetchEpisode(Long episodeId) {
        return episodeRepository.findOne(episodeId);
    }

    private List<Viewings> fetchViewings() {
        List<Viewings> viewings = new ArrayList();

        viewingsRepository.findAll()
                .forEach(viewings::add);

        return viewings;
    }

    private Show fetchShow(Long show_id) {
        return showRepository.findOne(show_id);

    }
}
