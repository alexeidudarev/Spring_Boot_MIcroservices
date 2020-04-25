package io.moviecatalog.movieratingdataservice.resources;

import io.moviecatalog.movieratingdataservice.models.Rating;
import io.moviecatalog.movieratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResources {
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,5);
    }
    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
         List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("1235",3),
                new Rating("1236",2),
                 new Rating("1237",1),
                 new Rating("1238",8)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
