
package com.example.user.movies;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailers {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("results")
    @Expose
    private List<TrailerResult> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Trailers() {
    }

    /**
     * 
     * @param id
     * @param results
     */
    public Trailers(long id, List<TrailerResult> results) {
        super();
        this.id = id;
        this.results = results;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TrailerResult> getResults() {
        return results;
    }

    public void setResults(List<TrailerResult> results) {
        this.results = results;
    }

}
