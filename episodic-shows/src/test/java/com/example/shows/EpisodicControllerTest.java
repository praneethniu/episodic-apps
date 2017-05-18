package com.example.shows;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EpisodicControllerTest {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    MockMvc mvc;

    @Before
    public void setup() {
        episodeRepository.deleteAll();
    }

    @Test
    @Rollback
    @Transactional
    public void postEpisode() throws Exception {
        Show show = new Show();
        show.setName("friends show");
        showRepository.save(show);

        List<Show> shows = new ArrayList();

        showRepository.findAll()
                .forEach(shows::add);

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("seasonNumber", "1");
                put("episodeNumber", "2");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = post("/shows/"+shows.get(0).getId()+"/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.seasonNumber", equalTo(1)))
                .andExpect(jsonPath("$.episodeNumber", equalTo(2)))
                .andExpect(jsonPath("$.title",  equalTo("S1 E2")));

        assertThat(episodeRepository.count(), equalTo(1L));
    }

    @Test
    @Rollback
    @Transactional
    public void getEpisodes() throws Exception {

        Show show = new Show();
        show.setName("friends show");
        showRepository.save(show);

        List<Show> shows = new ArrayList();

        showRepository.findAll()
                .forEach(shows::add);

        Episode episode = new Episode();
        episode.setEpisodeNumber(2);
        episode.setSeasonNumber(3);
        episode.setShow_id(shows.get(0).getId());
        episodeRepository.save(episode);



        MockHttpServletRequestBuilder request = get("/shows/"+shows.get(0).getId()+"/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].seasonNumber", equalTo(3)))
                .andExpect(jsonPath("$[0].episodeNumber", equalTo(2)))
                .andExpect(jsonPath("$[0].title",  equalTo("S3 E2")));

        assertThat(episodeRepository.count(), equalTo(1L));
    }
}