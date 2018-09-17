package com.example.springboot2sandbox.security;

import com.example.springboot2sandbox.entity.User;
import com.example.springboot2sandbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), createAuthorityList("ROLE_" + user.getRole().name()));
	}
}
