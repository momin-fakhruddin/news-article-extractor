package ca.uwaterloo.article_extractor.processors;

import ca.uwaterloo.article_extractor.beans.Article;
import ca.uwaterloo.article_extractor.utils.ArticleParseHelper;
import ca.uwaterloo.article_extractor.utils.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.List;

public class ArticleExtractProcessor extends Processor
{
    private static Logger log = LogManager.getLogger(ArticleExtractProcessor.class);

    @Override
    public void process()
        throws Exception
    {
        log.info("Begun processing");
        log.info("Reading input file " + Options.getInstance().getInputArticlesFilePath());

        List<Article> articles =
            ArticleParseHelper.getArticlesFromFile(Options.getInstance().getInputArticlesFilePath());
        log.debug(articles);

        for (Article article : articles)
        {
            URL articleURL = ArticleParseHelper.getURLForArticleHeadline(article.getTitle());
            log.debug(articleURL);
            Thread.sleep(1000);
        }

        log.info("Completed processing");
    }
}
