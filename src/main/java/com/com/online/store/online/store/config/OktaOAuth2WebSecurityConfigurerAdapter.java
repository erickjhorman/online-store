package com.com.online.store.online.store.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/api/v1/orders").authenticated()
                .and()
                .oauth2ResourceServer().jwt();
        http.cors();
        // force a non-empty response body for 401's to make the response more browser friendly
        Okta.configureResourceServer401ResponseBody(http);
    }

     */



     //For some applications, you may want to require the user to be authenticated for all routes.
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
        http.cors();
    }


     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Require authentication for all requests under /api/private
                .antMatchers("/").authenticated()
                .antMatchers("/hello").authenticated()
                .antMatchers("/logout").permitAll()
                .antMatchers("/api/private/**").authenticated()
                // enable OAuth2/OIDC
                .and()
                .oauth2Login();
    }
}
