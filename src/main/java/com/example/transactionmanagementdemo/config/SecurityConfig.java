//package com.example.transactionmanagementdemo.config;
//
//import com.example.transactionmanagementdemo.security.JwtFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
////@Configuration
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//// if using annotation method for endpoint protection, uncomment @PreAuthorize in ContentController and @EnableGlobalMethodSecurity here
//public class SecurityConfig {//extends WebSecurityConfigurerAdapter {
//
//    private JwtFilter jwtFilter;
//
//    @Autowired
//    public void setJwtFilter(JwtFilter jwtFilter) {
//        this.jwtFilter = jwtFilter;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/admin/*").hasAuthority("admin")
//                .antMatchers("/user/*").hasAuthority("user")
//                .anyRequest().authenticated();
////                .csrf().disable();
//    }
//}
