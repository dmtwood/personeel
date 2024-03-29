package be.vdab.personeel.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/11/2020
 * Defines user authorization using email-addresses and encrypted passwords.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email as username, paswoord as password, true as enabled" +
                                " from werknemers where email=?"
                )
                .authoritiesByUsernameQuery(
                        "select ?, 'gebruiker'"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // set login URI as login page
        http.formLogin(
                login -> login.loginPage("/login")
        );

        http.authorizeRequests(
                requests -> requests
                        // root and login URI have global access
                        .mvcMatchers("/","/login").permitAll()
                        .mvcMatchers("/jobtitels","/werknemer").hasAuthority("gebruiker")
        );
        http.logout().logoutSuccessUrl("/");
    }


}
