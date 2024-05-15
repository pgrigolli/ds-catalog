package com.grigolli.dscatalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grigolli.dscatalog.dto.CategoryDTO;
import com.grigolli.dscatalog.entities.Category;
import com.grigolli.dscatalog.repositories.CategoryRepository;
import com.grigolli.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();

        List<CategoryDTO> listDTO = new ArrayList<>();
        for(Category cat: list){
            listDTO.add(new CategoryDTO(cat));
        }
        

        return listDTO;
    }


    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {

        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);

    }


    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());

        repository.save(entity);

        return new CategoryDTO(entity);



    }
    
}
