package com.sushant.blog.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.sushant.blog.entitites.User;


public interface UserRepo extends JpaRepository<User,Integer>{
	

}
