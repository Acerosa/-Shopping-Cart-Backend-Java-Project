package com.rr.rrshops.repository;

import com.rr.rrshops.model.Category;
import com.rr.rrshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
