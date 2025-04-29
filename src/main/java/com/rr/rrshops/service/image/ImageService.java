package com.rr.rrshops.service.image;

import com.rr.rrshops.DTO.ImageDTO;
import com.rr.rrshops.expection.ResourceNotFoundExeption;
import com.rr.rrshops.model.Image;
import com.rr.rrshops.model.Product;
import com.rr.rrshops.repository.ImageRepository;
import com.rr.rrshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public void deleteImagesById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Image not found: " + id));
        imageRepository.delete(image);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Image not found: " + id));
    }

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDTOs = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileType(file.getContentType());
                image.setFileName(file.getOriginalFilename());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                Image savedImage = imageRepository.save(image);
                String downloadUrl = "/api/v1/images/image/download" + savedImage.getId();
                savedImage.setDownloadUrl(downloadUrl);
                imageRepository.save(savedImage); // optional: see next phase for a cleaner way

                savedImageDTOs.add(toDTO(savedImage));
            } catch (IOException | SQLException e) {
                log.error("Failed to save image", e);
                throw new RuntimeException("Unable to save image: " + e.getMessage());
            }
        }

        return savedImageDTOs;
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image image = getImageById(id);
        try {
            image.setFileType(file.getContentType());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            log.error("Failed to update image with id {}", id, e);
            throw new RuntimeException("Unable to update image: " + e.getMessage());
        }
    }

    private ImageDTO toDTO(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setImageId(image.getId());
        dto.setImageName(image.getFileName());
        dto.setDownloadUrl(image.getDownloadUrl());
        return dto;
    }
}