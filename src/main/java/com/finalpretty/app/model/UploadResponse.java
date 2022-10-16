package com.finalpretty.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UploadResponse{
    private String url;
    private Error error;
}
