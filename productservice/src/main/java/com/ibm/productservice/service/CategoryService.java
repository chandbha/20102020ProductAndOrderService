package com.ibm.productservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.productservice.entity.Category;
import com.ibm.productservice.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories()
	{
		return categoryRepository.findAll();
	}
	public Category getCategory(@PathVariable Long id) {
		try {
		 return categoryRepository.findById(id).get();
		
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
	
	
	
}
