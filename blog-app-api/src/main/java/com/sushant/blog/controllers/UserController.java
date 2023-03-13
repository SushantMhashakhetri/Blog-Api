package com.sushant.blog.controllers;

import java.util.List;

//import org.hibernate.mapping.Map;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushant.blog.payloads.ApiResponse;
import com.sushant.blog.payloads.UserDto;
import com.sushant.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	
	//POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	
	
	//PUT-Update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	{//If we dont need to chande name then directly  @Pathvariable Integer userId
		UserDto updatedUserDto=this.userService.updateUser(userDto, uid);
	    return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
//	    return ResponseEntity.ok(updatedUserDto);
	}
	
	
	
	//DELETE
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
	
	{
		this.userService.deleteUser(uid);
//		return ResponseEntity.ok(Map.of("message","User Deleted Successfully"));
	
//		return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
	    
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
	 
	}
	
	//GET
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
