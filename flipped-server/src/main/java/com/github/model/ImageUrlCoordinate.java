package com.github.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUrlCoordinate {
    private Integer row;
    private Integer cell;
    private String url;
}
