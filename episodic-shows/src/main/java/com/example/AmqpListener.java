package com.example;

import com.example.shows.Episode;
import com.example.shows.EpisodeRepository;
import com.example.viewings.Viewings;
import com.example.viewings.ViewingsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class AmqpListener implements RabbitListenerConfigurer {

    @Autowired
    ViewingsRepository viewingsRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @RabbitListener(queues = "episodic-progress")
    public void receiveMessage(final EpisodeProgress message) {
        System.out.println("************************************************");

        Viewings viewing = new Viewings();

        viewing.setEpisodeId(message.getEpisodeId());
        viewing.setTimecode(message.getOffset());
        viewing.setUpdatedAt(message.getCreatedAt());
        viewing.setUserId(message.getUserId());
        Episode episode = episodeRepository.findOne(message.getEpisodeId());
        if(episode != null) {
            viewing.setShowId(episode.getShow_id());
            viewingsRepository.save(viewing);
            System.out.println(message.toString());
            System.out.println("************************************************");
        }
         else {
            System.out.println("Episode id:"+message.getEpisodeId()+" not found in database");
            System.out.println("************************************************");
        }

    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    public static class EpisodeProgress {
        private Long userId;
        private Long episodeId;
        private Date createdAt;
        private Integer offset;

        public Long getUserId() {
            return userId;
        }

        public Long getEpisodeId() {
            return episodeId;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public void setEpisodeId(Long episodeId) {
            this.episodeId = episodeId;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EpisodeProgress that = (EpisodeProgress) o;

            if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
            if (episodeId != null ? !episodeId.equals(that.episodeId) : that.episodeId != null) return false;
            if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
            return offset != null ? offset.equals(that.offset) : that.offset == null;
        }

        @Override
        public int hashCode() {
            int result = userId != null ? userId.hashCode() : 0;
            result = 31 * result + (episodeId != null ? episodeId.hashCode() : 0);
            result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
            result = 31 * result + (offset != null ? offset.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "EpisodeProgress{" +
                    "userId=" + userId +
                    ", episodeId=" + episodeId +
                    ", createdAt='" + createdAt + '\'' +
                    ", offset=" + offset +
                    '}';
        }
    }

}