package ca.uwaterloo.article_extractor.processors;

import ca.uwaterloo.article_extractor.beans.Article;
import ca.uwaterloo.article_extractor.threads.ArticleContentExtractor;
import ca.uwaterloo.article_extractor.utils.ArticleParseHelper;
import ca.uwaterloo.article_extractor.utils.ArticleSearchHelper;
import ca.uwaterloo.article_extractor.utils.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArticleExtractProcessor extends Processor
{
    private static Logger log = LogManager.getLogger(ArticleExtractProcessor.class);
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void process()
        throws Exception
    {
        log.info("Begun processing");
        log.info("Reading input file " + Options.getInstance().getInputArticlesFilePath());

        List<Article> articles =
            ArticleParseHelper.getArticlesFromFile(Options.getInstance().getInputArticlesFilePath());
        log.debug("Read "+ articles.size() + " articles");

        ArticleSearchHelper articleSearchHelper = new ArticleSearchHelper();
        List<Article> articlesWithText = Collections.synchronizedList(new ArrayList<>());

        for (Article article : articles)
        {
            URL articleURL = articleSearchHelper.getURLForArticleHeadline(article.getTitle());
            executor.execute(new ArticleContentExtractor(article, articleURL, articlesWithText));
            Thread.sleep(500);
        }
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        articleSearchHelper.quitDriver();

        log.info("There are " + articlesWithText.size() + " articles.");
        if (articlesWithText.size() > 0)
        {
            ArticleParseHelper.persistArticlesToFile(
                Options.getInstance().getOutputArticlesFilePath(),
                articlesWithText
            );
        }

        log.info("Completed processing");
    }
}
