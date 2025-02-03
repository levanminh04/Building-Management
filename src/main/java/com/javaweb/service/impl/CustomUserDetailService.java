package com.javaweb.service.impl;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findOneByUserNameAndStatus(username, 1)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );

        // In giá trị trả về ra console
        System.out.println("UserDetails Username: " + userDetails.getUsername());
        System.out.println("UserDetails Password: " + userDetails.getPassword());
        System.out.println("UserDetails Authorities: " + userDetails.getAuthorities());

        return userDetails;
    }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(RoleDTO role: userDTO.getRoles()){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
//        }
//        MyUserDetail myUserDetail = new MyUserDetail(name,userDTO.getPassword(),true,true,true,true,authorities);
//        BeanUtils.copyProperties(userDTO, myUserDetail);
//        return myUserDetail;

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
//        return user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                .collect(Collectors.toList());
        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
//                .peek(authority -> logger.info("Granted Authority: {}", authority))
                .collect(Collectors.toList());

        return authorities;
    }
}


