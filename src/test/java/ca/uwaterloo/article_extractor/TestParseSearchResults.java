package ca.uwaterloo.article_extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;

public class TestParseSearchResults
{
    @Test
    public void testGetTopResult()
    {

        String headline =  "EasyJet attracts more passengers in June but still lags Ryanair";

        Document doc;
        try
        {
            doc =
                Jsoup
                    .connect("https://www.google.ca/search?q=" + URLEncoder.encode(headline, "UTF-8"))
                    .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/54.0.2840.71 Safari/537.36"
                    )
                    .followRedirects(true)
                    .timeout(5000)
                    .get();

            System.out.println(doc);
            Elements links = doc.select("#rso > div > div:nth-child(1) > div > h3 > a");
            System.out.println(links.size());

            for (Element link : links) {
//                Elements titles = link.select("h3[class=r]");
//                String title = titles.text();
//
//                Elements bodies = link.select("span[class=st]");
//                String body = bodies.text();
//
//                System.out.println("Title: "+title);
//                System.out.println("Body: "+body+"\n");

                System.out.println(link);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
