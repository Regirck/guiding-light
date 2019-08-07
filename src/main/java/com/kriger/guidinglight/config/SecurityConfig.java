package com.kriger.guidinglight.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()

                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()

                .antMatchers(HttpMethod.GET). permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/forgot_password").permitAll()
                .antMatchers("/password_recovery").permitAll()
                .antMatchers("/forum").permitAll()

                .antMatchers("/profile").authenticated()
                .antMatchers("/forum/new_question").authenticated()
                .antMatchers("/forum/question/new_answer/**").authenticated()

                .antMatchers(HttpMethod.POST).hasRole("USER")

                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")

                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login")
                        .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/login?logout")
                                    .permitAll();
        }

}
