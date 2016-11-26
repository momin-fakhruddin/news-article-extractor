package ca.uwaterloo.article_extractor.utils;

import ca.uwaterloo.article_extractor.beans.Article;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class ArticleParseHelper
{
    private static final String SEARCH_URL_STRING = "http://www.google.com/search?q=__KEYWORDS_PLACEHOLDER__&btnI";
    private static final String KEYWORD_PLACEHOLDER = "__KEYWORDS_PLACEHOLDER__";
    private static Logger log = LogManager.getLogger(ArticleParseHelper.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String getArticleText(URL url)
        throws BoilerpipeProcessingException
    {
        return ArticleExtractor.INSTANCE.getText(url);
    }

    @Nullable
    public static URL getURLForArticleHeadline(String headline)
    {
        String errorMsg = "Failed to get article text for " + headline;

        Response response;
        try
        {
            response = Jsoup
                .connect(SEARCH_URL_STRING.replace(KEYWORD_PLACEHOLDER, URLEncoder.encode(headline, "UTF-8")))
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")
                .followRedirects(true)
                .execute();

            return response.url();
        }
        catch (Exception e)
        {
            log.error("Exception: " + errorMsg, e);
        }

        return null;
    }

    public static List<Article> getArticlesFromFile(String inputArticlesFilePath)
        throws IOException
    {
        return MAPPER.readValue(
            new File(inputArticlesFilePath),
            new TypeReference<List<Article>>(){}
        );
    }
}
