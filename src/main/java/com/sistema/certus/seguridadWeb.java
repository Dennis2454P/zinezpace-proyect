package com.sistema.certus;

import com.sistema.certus.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class seguridadWeb extends WebSecurityConfigurerAdapter{
 @Autowired
 public UsuarioServicio usuarioServicio;

@Autowired
public void  configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(usuarioServicio)
            .passwordEncoder(new BCryptPasswordEncoder());
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/perfil").authenticated()

                .antMatchers("/css/*", "/js/*", "/img/*", "/**", "/register")

                  .permitAll()
                  .and()
                /*.formLogin()
                       .loginPage("/login1")
                       .loginProcessingUrl("/logincheck")
                       .usernameParameter("nombre")
                        .passwordParameter("contrasena")
                       .defaultSuccessUrl("/perfil")
                       .permitAll()
                       .and()*/
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/logincheckp")
                    .usernameParameter("nombre")
                     .passwordParameter("contrasena")
                     .defaultSuccessUrl("/perfil")
                     .permitAll()
                     .and()
                .logout()
                  .logoutUrl("/logout")
                  .logoutSuccessUrl("/login")
                   .permitAll()
                .and()
                .csrf().disable();


    }
}
