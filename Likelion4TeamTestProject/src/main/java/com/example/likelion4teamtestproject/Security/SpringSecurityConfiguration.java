package com.example.likelion4teamtestproject.Security;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//        http.authenticationManager(authenticationManager);
//
//        RestApiAuthenticationProcessingFilter restApiAuthenticationProcessingFilter = this.restApiAuthenticationProcessingFilter(authenticationManager);
//
//        SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
//
//        simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl("/main");
//
//        restApiAuthenticationProcessingFilter.setAuthenticationSuccessHandler(simpleUrlAuthenticationSuccessHandler);

        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/user/**","/main","/**").permitAll()
                    .anyRequest().authenticated()
                )
                .cors(cors ->
                        cors.configurationSource(getCorsConfiguration())
                )
                .csrf(AbstractHttpConfigurer::disable)
//                .addFilterAt(restApiAuthenticationProcessingFilter,
//                        UsernamePasswordAuthenticationFilter.class)
                .formLogin(
                        login -> login
                                .loginPage("/user/login")
                                .passwordParameter("password")
                                .usernameParameter("email")

                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/main")
                )
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private CorsConfigurationSource getCorsConfiguration() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",config);

        return urlBasedCorsConfigurationSource;
    }

    private RestApiAuthenticationProcessingFilter restApiAuthenticationProcessingFilter(final AuthenticationManager authenticationManager) {
        return new RestApiAuthenticationProcessingFilter("/user/login",
                authenticationManager);
    }




}
