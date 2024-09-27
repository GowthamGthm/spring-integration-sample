package com.gthm.sample.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gthm.sample.model.Movies;
import com.gthm.sample.utils.RandomData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableIntegration
@Slf4j
public class IntegrationConfig {


//    @InboundChannelAdapter( channel = "movie-receiver" , poller=@Poller(fixedRate = "5000"))
//    @InboundChannelAdapter( channel = "movie-receiver")
    public List<Movies> moviesInbound() {
        log.info("======================= FIRST STEP ====================");
        return RandomData.getMovies(10);
    }

//    @Transformer(inputChannel = "movie-receiver" , outputChannel = "movie-transformer")
    public List<Movies> receiverChannel(List<Movies> movies) {

        log.info("======================= SECOND STEP ====================");

        return Optional.ofNullable(movies).orElse(Collections.emptyList())
                .stream()
                .peek(m -> {
                    if (m.getId() % 2 == 0) {
                        m.setMovieRecord(HitOrFLop.HIT);
                    } else {
                        m.setMovieRecord(HitOrFLop.FLOP);
                    }
                }).toList();
    }


//    @ServiceActivator(inputChannel = "movie-transformer" , outputChannel = "movie-transformer-3")
    public List<Movies> movieSaver(List<Movies> movies) {

        try {
            log.info("===================== THIRD STEP =========================");
            String s = new ObjectMapper().writeValueAsString(movies);
            log.info(s);
            log.info("==========================    =============================");
            return movies;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @ServiceActivator(inputChannel = "movie-transformer-3")
    public void movieSaverNext(List<Movies> movies) {

        try {
            log.info("===================== FOURTH STEP =========================");
            String s = new ObjectMapper().writeValueAsString(movies);
            log.info(s);
            log.info("==========================    =============================");


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}