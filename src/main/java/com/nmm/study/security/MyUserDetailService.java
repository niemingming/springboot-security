package com.nmm.study.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("userDetailService:" + s);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return new MyUserDetails(s,"password",AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
