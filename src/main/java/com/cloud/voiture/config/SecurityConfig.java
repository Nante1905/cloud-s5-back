package com.cloud.voiture.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cloud.voiture.controllers.handlers.AccessDeniedExceptionHandler;
import com.cloud.voiture.controllers.handlers.AuthentificationExceptionHandler;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    // @Autowired
    // JwtEntryPoint jwtEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf((csrf) -> csrf.disable())
                .cors(cors -> cors.configurationSource(this.corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/error/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/annonces/{id}").permitAll()
                            .requestMatchers("/annonces/find").permitAll()
                            .requestMatchers(HttpMethod.POST, "/notif-token").permitAll()
                            .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                            .requestMatchers(HttpMethod.POST, "/categories").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/categories").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/categories").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/etats/**").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/etats").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/etats").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/marques").permitAll()
                            .requestMatchers(HttpMethod.POST, "/marques").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/marques").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/marques").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/modeles/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/modeles").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/modeles").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/modeles").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/couleurs").permitAll()
                            .requestMatchers(HttpMethod.POST, "/couleurs").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/couleurs").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/couleurs").hasAuthority("ADMIN")
                            .requestMatchers("/annonces/estimate").permitAll()
                            .requestMatchers(HttpMethod.POST, "/notif-token").permitAll()
                            .requestMatchers("/socket.io/**").permitAll()
                            .anyRequest().authenticated();

                })
                .addFilterBefore(this.interceptor(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling((c) -> c.authenticationEntryPoint(authenticationExceptionHanlder()))
                .exceptionHandling((c) -> c.accessDeniedHandler(accessDeniedException()))
                .build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationExceptionHanlder() {
        return new AuthentificationExceptionHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedException() {
        return new AccessDeniedExceptionHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public JWTInterceptor interceptor() {
        return new JWTInterceptor();
    }
}
