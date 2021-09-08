package com.alfred.moviesapi.Model;

import java.util.ArrayList;

public class SearchRespuesta {
    private ArrayList<Search> results;

    public ArrayList<Search> getResults() {
        return results;
    }

    public void setResults(ArrayList<Search> results) {
        this.results = results;
    }
}
