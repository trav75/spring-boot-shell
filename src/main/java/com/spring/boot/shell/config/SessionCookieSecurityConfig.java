package com.spring.boot.shell.config;

import com.spring.boot.shell.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/*
*
Session based cookie authentication from:  http://www.baeldung.com/securing-a-restful-web-service-with-spring-security

Now let’s see how we can authenticate against the REST API – the URL for login is /login – and a simple curl command performing login would be:

curl -i -X POST -d username=user -d password=cookiepass
http://localhost:8080/login
This request will return the Cookie which will then be used by any subsequent request against the REST Service.

We can use curl to authentication and store the cookie it receives in a file:

curl -i -X POST -d username=user -d password=cookiepass -c /opt/cookies.txt
http://localhost:8080/login
Then we can use the cookie from the file to do further authenticated requests:

curl -i --header "Accept:application/json" -X GET -b /opt/cookies.txt
http://localhost:8080/test
*
* */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.spring.boot.shell.*")
public class SessionCookieSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private SessionCookieMySavedRequestAwareAuthenticationSuccessHandler
            authenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("adminPass").roles("ADMIN").authorities(new SimpleGrantedAuthority("ADMIN"))
//                .and()
//                .withUser("user").password("userPass").roles("USER").authorities(new SimpleGrantedAuthority("USER"));
//    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/test").authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout();
    }

    @Bean
    public SessionCookieMySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
        return new SessionCookieMySavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}