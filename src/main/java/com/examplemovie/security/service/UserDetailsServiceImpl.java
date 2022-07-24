package com.examplemovie.security.service;

import com.examplemovie.domain.Role;
import com.examplemovie.domain.UserforRole;
import com.examplemovie.exception.ResourceNotFoundException;
import com.examplemovie.repository.IUserforRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserforRoleRepository userforRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserforRole userforRole= userforRoleRepository.findByUserName(userName).
                orElseThrow(()->new ResourceNotFoundException("User not found userName:"+userName));

        if(userforRole!=null) {
            return new org.springframework.security.core.userdetails.
                    User(userforRole.getUserName(), userforRole.getPassword(),buildGrantedAuthorities(userforRole.getRoles()));
        }else {
            throw new UsernameNotFoundException("User not found email:"+userName);
        }
    }
    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles){
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(Role role:roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }
}
