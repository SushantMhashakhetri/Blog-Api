package com.sushant.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sushant.blog.repo.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {


	@Autowired
	private UserRepo userRepo;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void repoTest() {
		System.out.println(this.userRepo.getClass().getName()+"   "+this.userRepo.getClass().getPackageName());
	}

}
