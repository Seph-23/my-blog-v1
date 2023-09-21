package com.myblog.backend.global.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 *	Spring Security Configuration
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.cors().configurationSource(corsConfigurationSource())
			.and()
			.csrf().disable()
			.formLogin().disable()
			.authorizeRequests()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.headers()
			.frameOptions().sameOrigin();
		return http.build();
	}

	/**
	 *	Cors Configuration for Spring Security
	 *	corsConfiguration.addAllowedOrigin("*")과 corsConfiguration.setAllowCredentials(true)는 동시에 사용할 수 없다.
	 *  그래서 corsConfiguration.addAllowedOriginPattern("*")을 사용한다.
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.setAllowCredentials(true);
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
