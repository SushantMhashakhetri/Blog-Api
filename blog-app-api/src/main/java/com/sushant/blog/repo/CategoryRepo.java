package com.sushant.blog.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sushant.blog.entitites.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
