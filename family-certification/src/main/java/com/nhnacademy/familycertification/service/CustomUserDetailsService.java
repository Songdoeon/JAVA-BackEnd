package com.nhnacademy.familycertification.service;

import com.nhnacademy.familycertification.entity.Resident;
import com.nhnacademy.familycertification.exception.NotFoundResidentException;
import com.nhnacademy.familycertification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final ResidentRepository residentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundResidentException {
        Resident resident = residentRepository.findByResidentId(username)
            .orElseThrow(NotFoundResidentException::new);

        return new User(resident.getResidentId(), resident.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
