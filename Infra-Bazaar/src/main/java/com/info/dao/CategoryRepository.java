package com.info.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
