package com.sakura.dev.service.impl;

import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import com.sakura.dev.service.CpStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by rc452 on 2017/5/26.
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    CpStudentRepository cpStudentRepository;
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        final CpStudent student = cpStudentRepository.findByCpIdCardNoOrCpSno(s,s);
        return new UserDetails() {
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
                return false;
            }

            public boolean isAccountNonLocked() {
                return false;
            }

            public boolean isCredentialsNonExpired() {
                return false;
            }

            public boolean isEnabled() {
                return true;
            }
        };
    }
}


