package ca.uwaterloo.article_extractor.threads;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.net.URL;

public class ArticleContentExtractor
{
    public static String getArticleText(URL articleURL)
        throws BoilerpipeProcessingException
    {
        return ArticleExtractor.INSTANCE.getText(articleURL);
    }
}
