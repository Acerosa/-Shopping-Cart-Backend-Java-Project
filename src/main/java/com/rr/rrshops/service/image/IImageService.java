package com.rr.rrshops.service.image;

import com.rr.rrshops.DTO.ImageDTO;
import com.rr.rrshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    /**
     * Deletes an image by its ID.
     * @param id The ID of the image to delete.
     */
    void deleteImagesById(Long id);

    /**
     * Retrieves a full image entity by its ID.
     * @param id The ID of the image.
     * @return The image entity.
     */
    Image getImageById(Long id);

    /**
     * Saves one or more images for a given product.
     * @param files The uploaded image files.
     * @param productId The ID of the product to associate images with.
     * @return A list of image DTOs with metadata.
     */
    List<ImageDTO> saveImages(List<MultipartFile> files, Long productId);

    /**
     * Updates an existing image by its ID.
     * @param file The new image file.
     * @param id The ID of the image to update.
     */
    void updateImage(MultipartFile file, Long id);
}