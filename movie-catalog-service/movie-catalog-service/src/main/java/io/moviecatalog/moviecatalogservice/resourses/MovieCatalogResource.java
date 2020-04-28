package io.moviecatalog.moviecatalogservice.resourses;

import com.sun.webkit.WebPageClient;
import io.moviecatalog.moviecatalogservice.models.CatalogItem;
import io.moviecatalog.moviecatalogservice.models.Movie;
import io.moviecatalog.moviecatalogservice.models.Rating;
import io.moviecatalog.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    //@Autowired
    //WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){



        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
            //this method response to res client and getting each movie id ,call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);

            //next line response to new framework web client that will replace rest template in the future
            /*
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

             */
            return new CatalogItem(movie.getMovieId(),"Action",rating.getRating());
        }).collect(Collectors.toList());
        /*
        return Collections.singletonList(
                new CatalogItem("Rembo-5","Action",1)
        );
        */

    }
}
