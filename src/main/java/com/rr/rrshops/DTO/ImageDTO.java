package com.rr.rrshops.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageDTO {
    private final Long imageId;
    private final String imageName;
    private final String downloadUrl;
}