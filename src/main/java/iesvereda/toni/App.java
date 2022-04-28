package iesvereda.toni;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */

import java.io.IOException;

public class App 
{
    public static void main(String[] args) throws IOException {
        String url = "https://www.recetasderechupete.com/";
        ParserEngine parser = new ParserEngine(url);

        parser.listAllLinks(parser.getBaseUrl());
        System.out.println("Crawler finished. Total URLs: " + parser.getUrlList().size());
    }

}
