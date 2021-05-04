package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Movie> myListOfMovies = SpectrumScraper.getFilms();
        SendHTMLEmail.sendEmail(myListOfMovies);
    }
}
