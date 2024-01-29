package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String , Movie> movDb;
    HashMap<String , Director> dirDb;
    HashMap<String,List<String>> mdDb;

    public void addMovie(Movie movie){
        movDb.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        dirDb.put(director.getName(),director);
        mdDb.put(director.getName(),new ArrayList<>());
    }

    public void addMovieDirectorPair(String movieName,String directorName){
        mdDb.get(directorName).add(movieName);
        Director director = dirDb.get(directorName);
        director.setNumberOfMovies(director.getNumberOfMovies()+1);
    }

    public Movie getMovieByName(String movieName){
        return movDb.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return dirDb.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return mdDb.get(directorName);
    }

    public List<String> findAllMovies(){
        List<String> movies = new ArrayList<>();
        for (String name : movDb.keySet()){
            movies.add(name);
        }
        return movies;
    }

    public void deleteDirectorByName(String directorName){
        List<String> mov = mdDb.get(directorName);
        for(String mName : mov){
            movDb.remove(mName);
        }
        dirDb.remove(directorName);
        mdDb.remove(directorName);
    }

    public void deleteAllDirectors(){
        for(String dName : mdDb.keySet()){
            deleteDirectorByName(dName);
        }
    }
}
