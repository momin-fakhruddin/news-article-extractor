package ca.uwaterloo.article_extractor.threads;

import ca.uwaterloo.article_extractor.beans.Article;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.List;

@AllArgsConstructor
public class ArticleContentExtractor implements Runnable
{
    private static Logger log = LogManager.getLogger(ArticleContentExtractor.class);

    private Article article;
    private URL articleURL;
    private List<Article> articles;

    @Override
    public void run()
    {
        try
        {
            String articleText = null;

            if(null != articleURL)
            {
                articleText = getArticleText(articleURL);
            }

            Article articleWithText =
                new Article(
                    article.getId(), article.getCompany(), article.getTitle(),
                    articleURL, articleText, article.getSentiment()
                );
            articles.add(articleWithText);
        }
        catch (Exception e)
        {
            log.error("Exception while processing content extractor for article " + article.getId());
            log.error(e);
        }
    }

    public static String getArticleText(URL articleURL)
        throws BoilerpipeProcessingException
    {
        return ArticleExtractor.INSTANCE.getText(articleURL);
    }
}
