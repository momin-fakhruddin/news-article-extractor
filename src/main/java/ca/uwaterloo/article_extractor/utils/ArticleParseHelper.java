package ca.uwaterloo.article_extractor.utils;

import ca.uwaterloo.article_extractor.beans.Article;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArticleParseHelper
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<Article> getArticlesFromFile(String inputArticlesFilePath)
        throws IOException
    {
        return MAPPER.readValue(
            new File(inputArticlesFilePath),
            new TypeReference<List<Article>>(){}
        );
    }

    public static void persistArticlesToFile(String outputArticlesFilePath, List<Article> articlesWithText)
        throws IOException
    {
        MAPPER.writeValue(new File(outputArticlesFilePath), articlesWithText);
    }
}
