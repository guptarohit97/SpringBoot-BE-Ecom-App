package com.ecomm.Practice.repository;

import com.ecomm.Practice.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @NotBlank @Size(min=5,message="Category must contain atleast 5 characters")
    Category findByCategoryName(@NotBlank @Size(min=5,message="Category must contain atleast 5 characters") String categoryName);
}
