package com.ecomm.Practice.service;

import com.ecomm.Practice.exceptions.APIException;
import com.ecomm.Practice.exceptions.ResourceNotFoundException;
import com.ecomm.Practice.model.Category;
import com.ecomm.Practice.payload.CategoryDTO;
import com.ecomm.Practice.payload.CategoryResponse;
import com.ecomm.Practice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories= categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category created till now");
        }
        List<CategoryDTO> categoryDTOS=categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .collect(Collectors.toList());
        CategoryResponse response=new CategoryResponse();
        response.setContent(categoryDTOS);
        return response;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDB=categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDB!=null){
            throw new APIException("Category with name " +category.getCategoryName()+"already exists");
        }
        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                    .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepository.delete(category);
        return "Category deleted with ID:"+categoryId;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category savedCategory=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        Category category=modelMapper.map(categoryDTO,Category.class);
        category.setCategoryId(categoryId);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
