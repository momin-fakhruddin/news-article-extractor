package ca.uwaterloo.article_extractor;

import ca.uwaterloo.article_extractor.processors.ArticleExtractProcessor;
import ca.uwaterloo.article_extractor.processors.Processor;
import ca.uwaterloo.article_extractor.utils.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewsArticleExtractor
{
    private static Logger log = LogManager.getLogger(NewsArticleExtractor.class);

	public static void main(String[] args)
	{
        try
        {
            Options.initializeInstance(args);

            Processor processor = new ArticleExtractProcessor();
            processor.process();
        }
        catch (Exception e)
        {
            log.error("Application Exception ", e);
            System.exit(1);
        }
	}
}
