package ca.uwaterloo.article_extractor.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article
{
    private String id;
    private String company;
    private String title;
    private String articleText;
    private Double sentiment;
}
