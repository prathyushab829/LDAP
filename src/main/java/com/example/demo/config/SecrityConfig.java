package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecrityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
         security.authorizeHttpRequests(authorize -> authorize.anyRequest().fullyAuthenticated()).formLogin(Customizer.withDefaults());
        return security.build();
    }

   /* @Bean
    public LdapTemplate ldapTemplate(){
        return new LdapTemplate(contextSource());
    }

    public LdapContextSource contextSource(){
        LdapContextSource source = new LdapContextSource();
        source.setUrl("ldap://localhost:10839");
        source.setUserDn("uid=admin,ou=system");
        source.setPassword("secret");
        return source;
    }*/

    @Bean
    public AuthenticationManager authenticationManager(BaseLdapPathContextSource source){
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
        factory.setUserDnPatterns("cn={0}");//,ou=users,ou=system
        return factory.createAuthenticationManager();
    }
}
