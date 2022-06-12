package com.example.waterprovider.config;

import com.example.waterprovider.service.MyUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/customer").permitAll()
                .antMatchers("/api/v1/update-user/{id}").permitAll()
                .antMatchers("/api/v1/show-cart/{userId}").permitAll()
                .antMatchers("/api/v1/products").permitAll()
                .antMatchers("/api/v1/addToCart/{productId}/{cartId}/{quantity}").permitAll()
                .antMatchers("/api/v1/deleteFromCart/{userId}/{productId}").permitAll()
                .antMatchers("/api/v1/buy/{userId}").permitAll()
                .antMatchers("/api/v1/add-cart").permitAll()
                .antMatchers("/api/v1/update-cart/{id}").permitAll()

                .antMatchers("/api/v1/admin").hasAuthority("Admin")
                .antMatchers("/api/v1/users").hasAuthority("Admin")
                .antMatchers("/api/v1/delete-user/{id}").hasAuthority("Admin")
                .antMatchers("/api/v1/add-history").hasAuthority("Admin")
                .antMatchers("/api/v1/add-product").hasAuthority("Admin")
                .antMatchers("/api/v1/update-product/{id}").hasAuthority("Admin")
                .antMatchers("/api/v1/delete-product/{id}").hasAuthority("Admin")
                .antMatchers("/api/v1/histories").hasAuthority("Admin")
                .antMatchers("/api/v1/carts").hasAuthority("Admin")
                .antMatchers("/api/v1/delete-cart/{id}").hasAuthority("Admin")


                .anyRequest().authenticated()
                .and().httpBasic();

    }
}
