package com.example.shows;

import com.example.users.User;
import com.example.shows.ShowRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class ShowsController {

    private ShowRepository
    showRespository;

    public ShowsController(ShowRepository
                           showRespository) {
        this.
                showRespository=
        showRespository;
    }

    @GetMapping("/shows")
    public List<Show> getShows() {
        List<Show> shows = new ArrayList();


        return shows;
    }

    @PostMapping("/shows")
    public Show createShow(@RequestBody Show show) {

        showRespository.save(show);

        return show;
    }
}
