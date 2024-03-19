package com.intuit.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping implements Serializable {

    private String newUrl;
    private String oldUrl;
    private String email;
}