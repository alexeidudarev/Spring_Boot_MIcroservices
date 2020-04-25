package io.moviecatalog.movieinfoservice.resources;

import io.moviecatalog.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/movies")
public class MovieResource {
    @RequestMapping("/{movieId}")
    public Movie getMovies(@PathVariable String movieId){
        return new Movie(movieId,"Tom and Jerry");
    }
}
