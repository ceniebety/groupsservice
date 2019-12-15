package com.arisan.online.groupservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arisan.online.groupservice.domain.User;
import com.arisan.online.groupservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**Get Selected User */
	public User getSelectedUser(String username) {
		Optional<User> result = userRepository.findByUsername(username);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	
	/** Save **/
	public void insertUser(User user) {
		userRepository.save(user);
	}
	
	
}
