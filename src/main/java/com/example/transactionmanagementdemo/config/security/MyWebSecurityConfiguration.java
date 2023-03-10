package com.example.transactionmanagementdemo.config.security;



import com.example.transactionmanagementdemo.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Resource
	private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

	@Resource
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

	@Resource
	private MyUserDetailsService myUserDetailsService;

	@Resource
	private JwtFilter jwtFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// Disable CSRF, since we don't use sessions
				.csrf().disable()
				// Disable HTTP response headers
				.headers().cacheControl().disable().and()
				// Token-based, so no session is needed
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// Filter requests
				.authorizeRequests()
				// Allow anonymous access to login and register
				.antMatchers("/mongo/*","/file/*","/swagger-ui/*").permitAll()
				// Static resources, can be accessed anonymously
				.antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
				// All requests except the above need authentication and authorization
				//.anyRequest().authenticated()
				.and()
				// Disable caching
				.headers()
				.cacheControl()
				.and()
				.frameOptions().disable();;

		// add Logout filter
	   //http.logout().logoutUrl("/logout").logoutSuccessHandler(new MyLogoutSuccessHandler());
		// add JWT filter
	   http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	   http.exceptionHandling().authenticationEntryPoint(restAuthorizationEntryPoint);


	}

	/**
	 * Strong hash hash encryption implementation
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	/**
	 * Identity authentication interface
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}



}
