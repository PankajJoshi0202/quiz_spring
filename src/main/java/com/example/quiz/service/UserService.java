package com.example.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz.entity.UserInfo;
import com.example.quiz.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository ref;

	public UserInfo signUp(UserInfo userinfo) {
		return ref.save(userinfo);
	}

	public UserInfo signIn(String userName, String pass) {

		UserInfo user_obj = ref.findByUserName(userName);

		if (user_obj != null) {
			if (user_obj.getuserName().equals(userName)) {
				if (user_obj.getPassword().equals(pass)) {
					return user_obj;
				}
			}

		} else {
			return null;
		}
		return null;
	}

	public ResponseEntity getIdByUsername(String userName) {
		// TODO Auto-generated method stub
		UserInfo user = ref.findByUserName(userName);
		// Assuming the operation was successful, return a success message
		String successMessage = String.valueOf(user.getId());

		// You can also return a specific HTTP status code (e.g., 200 OK)
		return ResponseEntity.status(HttpStatus.OK).body(successMessage);

	}
}
