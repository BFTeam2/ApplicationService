package com.example.transactionmanagementdemo.config.security;

//import com.example.authentication.domain.entity.User;
//import com.example.authentication.security.AuthUserDetail;
//import com.example.authentication.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MyUserDetailsService implements UserDetailsService {
//    @Autowired
//    @Lazy
//    private UserService userService;
//    @Autowired
//    @Lazy
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getUserByUsername(username);
//
//        // verify user
//        validate(user);
//
//        // get permission
//        List<String> permissions = userService.getPermissions(user.getId());
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        for (String permission : permissions){
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
//            authorityList.add(simpleGrantedAuthority);
//        }
//
//        return AuthUserDetail.builder()
//                .user_id(user.getId())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .enabled(true)
//                .authorities(authorityList).build();
        return null;
    }
//
//    private void validate(User user) {
//        if (user == null){
//            throw new RuntimeException("Login failed");
//        }
//        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
//        String username = usernamePasswordAuthenticationToken.getName();
//        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
//
//        if(!bCryptPasswordEncoder.matches(password,  user.getPassword())){
//             throw new RuntimeException("Login failed");
//        }
//    }
}
