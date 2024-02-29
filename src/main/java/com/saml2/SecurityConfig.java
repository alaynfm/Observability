package com.saml2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
                .antMatchers("/").permitAll()  // Permitir acceso no autenticado a la página de inicio
                .anyRequest().authenticated()  // Requerir autenticación para cualquier otra solicitud
            )
            .saml2Login(saml2Login -> saml2Login
                .loginPage("/")  // Usar la página de inicio como página de login
                .defaultSuccessUrl("/loginSuccess", true)  // Redirigir aquí después de un login exitoso
            )
            .logout(logout -> logout
                .logoutSuccessHandler(logoutSuccessHandler())  // Manejar el logout personalizado
            );
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            // Redirigir a la página de inicio después del logout
            response.sendRedirect("/");
        };
    }
}
