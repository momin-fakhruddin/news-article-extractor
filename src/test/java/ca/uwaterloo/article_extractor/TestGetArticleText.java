package ca.uwaterloo.article_extractor;

import ca.uwaterloo.article_extractor.utils.ArticleParseHelper;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class TestGetArticleText
{
    @Test
    public void testGetArticle()
        throws IOException, BoilerpipeProcessingException
    {
        String urlString =
            "http://www.google.com/search?q=__KEYWORDS_PLACEHOLDER__&btnI";

        urlString =
            urlString.replace(
                "__KEYWORDS_PLACEHOLDER__",
                "EasyJet attracts more passengers in June but still lags Ryanair"
            );

        Response response =
            Jsoup
            .connect(urlString)
            .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")
            .followRedirects(true)
            .execute();

        System.out.println();

        String articleText = ArticleParseHelper.getArticleText(response.url());

        System.out.println(articleText);
    }
}
