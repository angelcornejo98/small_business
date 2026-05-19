package com.knut.vms.config;

import com.knut.vms.service.user.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(http -> {
//                    // Configurar los endpoint públicos
//                    http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
//
//                    // Configurar los endpoints privados
//                    http.requestMatchers(HttpMethod.GET, "auth/hello-secured").hasAuthority("CREATE");
//
//                    // Configurar el resto de endpoint - NO ESPECIFICADOS
//                    http.anyRequest().denyAll();
//                })
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar los endpoints públicos
                    http.requestMatchers(HttpMethod.GET, "/auth/get").permitAll();

                    // Configurar los endpoints privados
//                    http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyAuthority("CREATE", "READ");
                    http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyRole("ADMIN", "DEVELOPER");
                    http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAnyAuthority("REFACTOR");
//                    http.requestMatchers(HttpMethod.POST, "/auth/post").hasAuthority("READ");

                    // Configurar el resto de endpoint - No especificados
                    http.anyRequest().denyAll();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        List<UserDetails> userDetailsList = new ArrayList<>();
//
//        userDetailsList.add(User.withUsername("angel")
//                .password("1234")
//                .authorities("ROLE_ADMIN", "READ", "CREATE")
//                .build());
//
//        userDetailsList.add(User.withUsername("john")
//                .password("1234")
//                .authorities("ROLE_USER", "READ")
//                .build());
//
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public static  void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
//    }

}
