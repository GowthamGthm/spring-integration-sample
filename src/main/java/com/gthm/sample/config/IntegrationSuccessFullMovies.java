package com.gthm.sample.config;


import com.gthm.sample.model.Movies;
import com.gthm.sample.utils.RandomData;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class IntegrationSuccessFullMovies {


    @InboundChannelAdapter(channel = "movie-success-filter")
    public List<Movies> movieSuccessInbound() {
        System.out.println("======================= movieSuccessInbound ====================");
        return RandomData.getMovies(100);
    }

    @ServiceActivator(inputChannel = "movie-success-filter", outputChannel = "movie-success-processor")
    public List<Movies> movieSuccessFilter(List<Movies> movies) {
        System.out.println("======================= movieSuccessFilter ====================");

        return movies.stream()
                     .peek(m -> {
                         HitOrFLop hitOrFLop = m.getYear() % 2 == 0 ? HitOrFLop.HIT : HitOrFLop.FLOP;
                         m.setMovieRecord(hitOrFLop);
                     })
                     .filter(m -> m.getMovieRecord()
                                   .equals(HitOrFLop.HIT))
                     .collect(Collectors.toList());

    }

    @ServiceActivator(inputChannel = "movie-success-processor")
    public void movieSuccessProcessor(List<Movies> movies) {

        System.out.println("======================= movieSuccessProcessor ====================");
        System.out.println("Length of the HIT movies: " + movies.size());

    }


}