package com.example.episodicevents;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private EventsRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void postEvent_play() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "play");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset", 0);
                    }
                });
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());


    }

    @Test
    public void postEvent_pause() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "pause");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset", 0);
                    }
                });
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());


    }

    @Test
    public void postEvent_fastForward() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "fastForward");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("startOffset", 4);
                        put("endOffset", 408);
                        put("speed", 2.5);
                    }
                });
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());


    }


    @Test
    public void postEvent_rewind() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "rewind");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("startOffset", 4);
                        put("endOffset", 408);
                        put("speed", 2.5);
                    }
                });
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());


    }

    @Test
    public void postEvent_progress() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "progress");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset", 4);
                    }
                });
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());


    }

    @Test
    public void getEvents() throws Exception {
        Event event = new PlayEvent(
                1L,
                2L,
                3L,
                "created-at",
                new Data(10L)
        );
        repository.save(event);

        event = new PauseEvent(
                1L,
                2L,
                3L,
                "created-at",
                new Data(10L)
        );
        repository.save(event);

        event = new ProgressEvent(
                1L,
                2L,
                3L,
                "created-at",
                new Data(10L)
        );
        repository.save(event);

        event = new FastForwardEvent(
                1L,
                2L,
                3L,
                "created-at",
                new OffsetData(10L, 20L, 10L)
        );
        repository.save(event);

        event = new RewindEvent(
                1L,
                2L,
                3L,
                "created-at",
                new OffsetData(10L, 20L, 10L)
        );
        repository.save(event);

        event = new ScrubEvent(

                1L,
                2L,
                3L,
                "created-at",
                new OffsetData(10L, 20L, 10L)
        );
        repository.save(event);

        MockHttpServletRequestBuilder request = get("/recent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("" +
                        "[{\"type\":\"play\",\"userId\":1,\"showId\":2,\"episodeId\":3,\"createdAt\":\"created-at\",\"data\":{\"offset\":10},\"type\":\"play\"},{\"type\":\"pause\",\"userId\":1,\"showId\":2,\"episodeId\":3,\"createdAt\":\"created-at\",\"data\":{\"offset\":10}},{\"type\":\"progress\",\"userId\":1,\"showId\":2,\"episodeId\":3,\"createdAt\":\"created-at\",\"data\":{\"offset\":10}},{\"type\":\"fastForward\",\"userId\":1,\"showId\":2,\"episodeId\":3,\"createdAt\":\"created-at\",\"type\":\"fastForward\"},{\"type\":\"rewind\",\"userId\":1,\"showId\":2,\"episodeId\":3,\"createdAt\":\"created-at\",\"data\":{\"startOffset\":10,\"endOffset\":20,\"speed\":10}},{\"type\":\"scrub\",\"userId\":1,\"showId\":2,\"episodeId\":3,\"createdAt\":\"created-at\",\"data\":{\"startOffset\":10,\"endOffset\":20,\"speed\":10}}]"));

    }
}