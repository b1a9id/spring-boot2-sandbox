package com.example.springboot2sandbox.security;

import com.example.springboot2sandbox.enums.Role;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalTime;
import java.util.Collection;

public class TimeAccessDecitionVoter implements AccessDecisionVoter {

	@Override
	public int vote(Authentication authentication, Object object, Collection collection) {
		String authority = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.findFirst()
				.orElse(null);
		if (authority == null) {
			return ACCESS_DENIED;
		}
		if (authority.equals(Role.ADMIN.name())) {
			return ACCESS_GRANTED;
		}
		if (authority.equals(Role.STAFF.name())) {
			LocalTime now = LocalTime.now();
			if (now.getHour() > 0 && now.getHour() < 5) {
				return ACCESS_DENIED;
			}
			return ACCESS_GRANTED;
		}
		return ACCESS_DENIED;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class clazz) {
		return true;
	}
}
