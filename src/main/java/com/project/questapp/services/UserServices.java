package com.project.questapp.services;

import com.project.questapp.entities.User;
import com.project.questapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

	private  final  UserRepository repository;

	public List<User> getAllUsers(){
		return  repository.findAll();
	}

	public User createUser(User user){

		return  repository.save(user);
	}

 public User getOneUserById(long id){
	return 	repository.findById(id).orElse(null);
 }

	public User updateUser(long id,User newUser) {

		Optional<User> user=repository.findById(id);
		if(user.isPresent()){
			User foundUser=user.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setUsername(newUser.getPassword());
			return  repository.save(newUser);

		}else {
			return  null;
		}

	}


	public  void  deleteOneUser(long id){
		repository.deleteById(id);
	}

	public User getOneUserByUserName(String username) {

	 return 	repository.findByUsername(username);
	}
}
