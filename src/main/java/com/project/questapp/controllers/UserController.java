package com.project.questapp.controllers;

import com.project.questapp.entities.User;
import com.project.questapp.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {



	private  final UserServices services;


	@GetMapping
	public List<User> getAllUsers(){

		return  services.getAllUsers();
	}
	@PostMapping
	public User createUser(@RequestBody User user){
		return  services.createUser(user);

	}

	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long id){

		return  services.getOneUserById(id);
	}
	@PutMapping("/{userId}")
	public User updateOneUSer(@PathVariable Long id ,@RequestBody User user){

		return services.updateUser(id,user);

	}
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long id){
		services.deleteOneUser(id);
	}


}
