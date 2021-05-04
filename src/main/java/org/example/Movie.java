package org.example;

public class Movie {

    private String title;
    private String synopsis;
    private String longSynopsis;
    private String trailerLink;
    private String buyTickets;

    public Movie(String title, String synopsis, String longSynopsis, String trailerLink, String buyTickets) {
        this.title = title;
        this.synopsis = synopsis;
        this.longSynopsis = longSynopsis;
        this.trailerLink = trailerLink;
        this.buyTickets = buyTickets;

    }

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLongSynopsis() { return longSynopsis; }

    public void setLongSynopsis(String longSynopsis) { this.longSynopsis = longSynopsis; }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getBuyTickets() {
        return buyTickets;
    }

    public void setBuyTickets(String buyTickets) {
        this.buyTickets = buyTickets;
    }

    public String toString() {
        return String.format("\nTitle: %s\nBrief Synopsis: %s\nExpanded Synopsis: %s\nTrailer: %s\nBuy Tickets: %s\n", title, synopsis, longSynopsis, trailerLink, buyTickets);
    }
}






