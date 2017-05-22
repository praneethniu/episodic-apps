package com.example.viewings;

import com.example.shows.Episode;
import com.example.shows.EpisodeRepository;
import com.example.users.User;
import com.example.users.UserRepository;
import com.example.shows.Show;
import com.example.shows.ShowRepository;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ViewingsControllerTest {

    @Autowired
    private ViewingsRepository viewingsRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    MockMvc mvc;

    @Before
    public void setup() {
        viewingsRepository.deleteAll();
    }

    @Test
    @Rollback
    @Transactional
    public void createViewing() throws Exception {
        User user = new User();
        user.setEmail("random@email.com");
        userRepository.save(user);

        List<User> users = new ArrayList();

        userRepository.findAll()
                .forEach(users::add);


        Show show = new Show();
        show.setName("friends show");
        showRepository.save(show);

        List<Show> shows = new ArrayList();

        showRepository.findAll()
                .forEach(shows::add);

        Episode episode = new Episode();
        episode.setEpisodeNumber(3);
        episode.setSeasonNumber(4);
        episode.setShow_id(shows.get(0).getId());
        episodeRepository.save(episode);

        List<Episode> episodes = new ArrayList();
        episodeRepository.findAll()
                .forEach(episodes::add);


        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("episodeId", episodes.get(0).getId());
                put("updatedAt", "2017-05-04T11:45:34.9182");
                put("timecode", 79);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = patch("/users/"+users.get(0).getId()+"/viewings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(viewingsRepository.count(), equalTo(1L));
    }

    @Test
    @Rollback
    @Transactional
    public void getViewings() throws Exception {
        User user = new User();
        user.setEmail("random@email.com");
        userRepository.save(user);

        List<User> users = new ArrayList();

        userRepository.findAll()
                .forEach(users::add);


        Show show = new Show();
        show.setName("friends show");
        showRepository.save(show);

        List<Show> shows = new ArrayList();

        showRepository.findAll()
                .forEach(shows::add);

        Episode episode = new Episode();
        episode.setEpisodeNumber(3);
        episode.setSeasonNumber(4);
        episode.setShow_id(shows.get(0).getId());
        episodeRepository.save(episode);

        List<Episode> episodes = new ArrayList();
        episodeRepository.findAll()
                .forEach(episodes::add);


        Viewings viewings = new Viewings();
        viewings.setEpisodeId(episodes.get(0).getId());
        viewings.setUpdatedAt(new Timestamp(12));
        viewings.setTimecode(10);
        viewings.setShowId(shows.get(0).getId());
        viewings.setUserId(users.get(0).getId());
        viewingsRepository.save(viewings);


        MockHttpServletRequestBuilder request = get("/users/"+users.get(0).getId()+"/recently-watched")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$[0].show.id", notNullValue()))
                .andExpect(jsonPath("$[0].show.name", equalTo("friends show")))
                .andExpect(jsonPath("$[0].episode.id", notNullValue()))
                .andExpect(jsonPath("$[0].episode.seasonNumber", equalTo(4)))
                .andExpect(jsonPath("$[0].episode.episodeNumber", equalTo(3)))
                .andExpect(jsonPath("$[0].episode.title",  equalTo("S4 E3")))
                .andExpect(jsonPath("$[0].updatedAt",  equalTo(12)))
                .andExpect(jsonPath("$[0].timecode",  equalTo(10)));

        assertThat(viewingsRepository.count(), equalTo(1L));
    }
}