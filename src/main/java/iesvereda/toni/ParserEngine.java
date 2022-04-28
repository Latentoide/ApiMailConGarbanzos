package iesvereda.toni;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ParserEngine {

    private String baseUrl;
    private ArrayList<String> urlList;

    public ParserEngine(String baseUrl){
        this.baseUrl = baseUrl;
        this.urlList = new ArrayList<String>();
    }


    public void listAllLinks(String url) throws IOException {
        System.out.println("Parsing page " + url + "...");

        Document doc = Jsoup.connect(url).get();
        //Elements links = doc.select("a[href]");
        Elements links = doc.select("link");
        Elements media = doc.select("[src]").select("img");
        Elements divs = doc.select("div").select("ul").select("li");


        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if(divs.text().contains("garbanzos")){
                if (src.tagName().equals("img"))
                    print(" * %s: <%s> %sx%s (%s)",
                            src.tagName(), src.attr("src"), src.attr("width"), src.attr("height"),
                            src.attr("alt"));
                else
                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }
        }



        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            Document document = Jsoup.connect(link.attr("href")).get();
            Element element = document.select("div#ingredients").first();
            if(element != null)
                try{
                    if(element.text().toUpperCase().contains("garbanzos")){
                        print(" * a: <%s>  (%s)", link.attr("href"), link.text());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            /*if(divs.text().contains("garbanzos")){
                if(link.tagName().equals("ingredients") && link.text().equals("garbanzos")) {

                }
            }*/
        }

    }
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public void setBaseUrl(String url){
        baseUrl = url;
    }

    public ArrayList<String> getUrlList(){
        return urlList;
    }

}
