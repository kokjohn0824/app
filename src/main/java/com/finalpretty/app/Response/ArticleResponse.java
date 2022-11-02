package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleResponse {
    private Integer article_id;
    private String title;
    private String text;
}
