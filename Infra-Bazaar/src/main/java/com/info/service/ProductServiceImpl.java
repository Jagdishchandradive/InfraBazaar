package com.info.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.dao.ProductRepository;
import com.info.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> listProduct() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(long productId) {
		return productRepository.findById(productId);
	}

	@Override
	public List<Product> findByCategory(long categoryId) {
		return productRepository.findByCategory_CategoryId(categoryId);
	}

	@Override
	public void deleteProduct(long productId) {
		productRepository.deleteById(productId);
	}

}
