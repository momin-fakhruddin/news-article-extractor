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
        URL url = new URL("http://www.proactiveinvestors.com/companies/news/67396/kingfisher-takeover-of-mr-bricolage-could-hit-a-brick-wall-78595.html");
        String articleText = ArticleContentExtractor.getArticleText(url);
        System.out.println(articleText);
    }
}
