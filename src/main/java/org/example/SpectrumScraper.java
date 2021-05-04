package org.example;

import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public class SpectrumScraper {

    public static List<Movie> getFilms() {
        List<Movie> movies = new ArrayList<>();

        // GET request to obtain HTML content from the web server.
        String baseUrl = "https://www.landmarktheatres.com/albany-ny/spectrum-8-theatres";
        WebClient client = new WebClient();
        client.setCssErrorHandler(new SilentCssErrorHandler());
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            HtmlPage page = client.getPage(baseUrl);

            List<HtmlElement> itemList = page.getByXPath("//ul[@class='gridRow films-2' and @data-film='nowshowing']//li[@class='gridCol-s-12 gridCol-m-4 gridCol-l-4']");


            if (itemList.isEmpty()) {
                System.out.println("No item found.");
            } else {
                for (HtmlElement htmlItem : itemList) {
                    String dataFilmSession = htmlItem.getAttribute("data-film-session");

                    if (dataFilmSession.equals(DomElement.ATTRIBUTE_NOT_DEFINED) || dataFilmSession.equals(DomElement.ATTRIBUTE_VALUE_EMPTY)) {
                        continue;
                    }
                    String title = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='filmItemCopy']")).asText();
                    String titleOnly = title.substring(0, title.indexOf("\n"));

                    String synopsis = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='filmItemSynopsis']")).asText();

                    HtmlElement moreInfo = htmlItem.getFirstByXPath(".//a[@class='btn-1 right']");
                    page = moreInfo.click();

                    String longSynopsis = ((HtmlElement) page.getFirstByXPath("//p")).asText();

                    String trailerLink = ((HtmlElement) htmlItem.getFirstByXPath(".//a[@class='btn-1 left']")).getAttribute("href");

                    String buyTickets = ((HtmlElement) htmlItem.getFirstByXPath(".//a[@class='btn-1 right']")).getAttribute("href");

                    Movie movie = new Movie(titleOnly, synopsis, longSynopsis, trailerLink, buyTickets);
                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}


