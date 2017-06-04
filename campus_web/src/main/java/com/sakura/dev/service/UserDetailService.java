package com.sakura.dev.service;

import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import com.sakura.dev.service.CpStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by rc452 on 2017/5/26.
 */
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    CpStudentRepository cpStudentRepository;
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        final CpStudent student = cpStudentRepository.findByCpIdCardNoOrCpSno(s,s);
        HashSet<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        set.add(new SimpleGrantedAuthority("user"));
        return new User(s,student.getPassword()==null?s:student.getPassword(),
                true,
                true,
                true,
                true, set);
        /*return new UserDetails() {
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            public String getPassword() {
                return student.getPassword() == null?student.getCpIdCardNo():student.getPassword();
            }

            public String getUsername() {
                return s;
            }

            public boolean isAccountNonExpired() {
                return true;
            }

            public boolean isAccountNonLocked() {
                return true;
            }

            public boolean isCredentialsNonExpired() {
                return true;
            }

            public boolean isEnabled() {
                return true;
            }
        };*/
    }
}


