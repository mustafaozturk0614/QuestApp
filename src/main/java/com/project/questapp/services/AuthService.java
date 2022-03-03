package com.project.questapp.services;


import com.project.questapp.dto.request.UserRequest;
import com.project.questapp.dto.response.AuthResponse;
import com.project.questapp.entities.User;
import com.project.questapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;
private  final  UserServices userServices;
private  final PasswordEncoder passwordEncoder;


	public AuthResponse login(UserRequest userRequest){
		UsernamePasswordAuthenticationToken authenticationToken=
				new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword());
		Authentication auth=authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		String jwtToken= jwtTokenProvider.genaretJwtToken(auth);
		User user=userServices.getOneUserByUserName(userRequest.getUsername());
		AuthResponse authResponse=AuthResponse.builder().message("Bearer "+jwtToken).userId(user.getId()).build();

		return  authResponse;

	}

	public ResponseEntity<AuthResponse> register( UserRequest registerRequest){
		AuthResponse authResponse=AuthResponse.builder().build();

		if (userServices.getOneUserByUserName(registerRequest.getUsername())!=null){
			authResponse.setMessage("Username already in user");
				return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}else{

		}
		User user=
				User.builder().username(registerRequest.getUsername()).password( passwordEncoder.encode (registerRequest.getPassword())).build();
		authResponse.setMessage("User successfully created");
		userServices.createUser(user);
		return  new  ResponseEntity<>(authResponse,HttpStatus.CREATED);

	}

}
