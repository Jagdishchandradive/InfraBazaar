package com.info.service;

import java.util.List;
import java.util.Optional;

import com.info.model.Category;

public interface CategoryService {
	
	public void addCategory(Category category);
	
	public List<Category> listCategory();
	
	public void deleteCategory(long categoryId);
	
	public void updateCategory(Category category);
	
	public Optional<Category> getCategory(long categoryId);
	
}
