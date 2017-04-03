package writer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import writer.services.UserService;
import writer.services.impl.CustomUserDetailsService;

/**
 * Created by Arianna.Raduechel on 3/23/2017.
 */

@EnableWebSecurity
@Profile("customuserdetails")
public class CustomUserDetailsSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/", "/index/", "/#", "/static**").permitAll()
                .and().authorizeRequests().antMatchers("/api**").hasAuthority("user")
                .and().formLogin()
                .usernameParameter("username") // default is username
                .passwordParameter("password") // default is password
                .loginPage("/login") // default is /login with an HTTP get
                .failureUrl("/login?error") // default is /login?error
                .and().logout().permitAll()
//                .and().csrf()
        ;

        //region ADVANCED SETTINGS
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //endregion
    }
}
