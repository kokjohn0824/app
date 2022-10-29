package com.finalpretty.app.Response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {
    private Integer article_id;
    private String title;
    private String text;
    private Date create_date;
}
