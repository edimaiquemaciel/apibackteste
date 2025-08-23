package vortex.backend.config;

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
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Habilita a configuração de CORS que definimos abaixo
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // 2. Desabilita o CSRF
            .csrf(csrf -> csrf.disable())
            
            // 3. Define a política de sessão como STATELESS
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // 4. Configura as regras de autorização para os endpoints
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/usuarios/cadastrar").permitAll()
                .requestMatchers("/h2-console/**").permitAll() // Permite acesso ao console H2
                .anyRequest().authenticated()
            );

        // Permite que o H2 Console seja exibido em um frame no navegador
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 5. Defina aqui a origem do seu front-end
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000", // React
            "http://localhost:4200", // Angular
            "http://localhost:5173"  // Vite
        ));
        
        // Permite todos os métodos (GET, POST, PUT, DELETE, etc.)
        configuration.setAllowedMethods(Arrays.asList("*")); 
        
        // Permite todos os cabeçalhos
        configuration.setAllowedHeaders(Arrays.asList("*")); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a configuração para todos os endpoints
        
        return source;
    }
}