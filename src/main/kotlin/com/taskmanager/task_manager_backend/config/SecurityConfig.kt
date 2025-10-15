package com.taskmanager.task_manager_backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // 1. Desabilita o CSRF (necessário para APIs stateless)
            .csrf { csrf -> csrf.disable() } 
            
            // 2. Configura a autorização de requisições
            .authorizeHttpRequests { auth ->
                auth
                    // Permite acesso irrestrito (permitAll) a todos os endpoints (/api/**)
                    .anyRequest().permitAll() 
            }
            
        // Retorna a cadeia de filtros configurada
        return http.build()
    }
}