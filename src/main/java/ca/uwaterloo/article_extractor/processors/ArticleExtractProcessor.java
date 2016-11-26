package ca.uwaterloo.article_extractor.processors;

import ca.uwaterloo.article_extractor.beans.Article;
import ca.uwaterloo.article_extractor.exceptions.InternalAppException;
import ca.uwaterloo.article_extractor.threads.ArticleContentExtractor;
import ca.uwaterloo.article_extractor.utils.ArticleParseHelper;
import ca.uwaterloo.article_extractor.utils.ArticleSearchHelper;
import ca.uwaterloo.article_extractor.utils.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ArticleExtractProcessor extends Processor
{
    private static Logger log = LogManager.getLogger(ArticleExtractProcessor.class);

    @Override
    public void process()
        throws InternalAppException, IOException, InterruptedException
    {
        log.info("Begun processing");
        log.info("Reading input file " + Options.getInstance().getInputArticlesFilePath());

        List<Article> articles =
            ArticleParseHelper.getArticlesFromFile(Options.getInstance().getInputArticlesFilePath());
        log.debug("Read "+ articles.size() + " articles");

        ArticleSearchHelper articleSearchHelper = new ArticleSearchHelper();

        for (Article article : articles)
        {
            log.info("Processing article ID " + article.getId());
            String articleText = null;
            URL articleURL = null;
            List<URL> articleURLs = articleSearchHelper.getURLForArticleHeadline(article.getTitle());

            for (URL url : articleURLs)
            {
                try
                {
                    articleText = ArticleContentExtractor.getArticleText(url);
                    articleURL = url;
                    break;
                }
                catch (Exception e)
                {
                    log.error("Exception while processing content extractor for " + url);
                    log.error(e);
                }
            }

            article.setArticleURL(articleURL);
            article.setArticleText(articleText);
        }
        articleSearchHelper.quitDriver();

        ArticleParseHelper.persistArticlesToFile(
            Options.getInstance().getOutputArticlesFilePath(),
            articles
        );

        log.info("Completed processing");
    }
}
