package ca.uwaterloo.article_extractor;

import ca.uwaterloo.article_extractor.threads.ArticleContentExtractor;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class TestGetArticleText
{
    @Test
    public void testGetArticle()
        throws IOException, BoilerpipeProcessingException
    {
        URL url = new URL("http://www.dailyrecord.co.uk/business/company-results-forecasts/morrisons-book-second-consecutive-quarter-7900095");
        String articleText = ArticleContentExtractor.getArticleText(url);
        System.out.println(articleText);
    }
}
