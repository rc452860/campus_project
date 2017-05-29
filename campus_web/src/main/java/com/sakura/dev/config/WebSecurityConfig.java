package com.sakura.dev.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by rc452 on 2017/5/26.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    UserDetailsService userDetailService;

    @Autowired
    private AuthenticationManager auth;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }


    @Configuration
    @EnableAuthorizationServer
    protected class AuthServerOAuth2Config
            extends AuthorizationServerConfigurerAdapter {


        @Value("${oauth.paths.token:/oauth/authorize}")
        private String tokenPath = "/oauth/token";

        @Value("${oauth.paths.token_key:/oauth/token_key}")
        private String tokenKeyPath = "/oauth/token_key";

        @Value("${oauth.paths.check_token:/oauth/check_token}")
        private String checkTokenPath = "/oauth/check_token";

        @Value("${oauth.paths.authorize:/oauth/authorize}")
        private String authorizePath = "/oauth/authorize";

        @Value("${oauth.paths.confirm:/oauth/confirm_access}")
        private String confirmPath = "/oauth/confirm_access";

        @Override
        public void configure(
                AuthorizationServerSecurityConfigurer oauthServer)
                throws Exception {
            oauthServer
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients)
                throws Exception {
            // @formatter:off
            clients.jdbc(dataSource)
                    .withClient("my-trusted-client")
                    .authorizedGrantTypes("password", "authorization_code",
                            "refresh_token", "implicit")
                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                    .scopes("read", "write", "trust")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(60).and()
                    .withClient("my-client-with-registered-redirect")
                    .authorizedGrantTypes("authorization_code")
                    .authorities("ROLE_CLIENT").scopes("read", "trust")
                    .resourceIds("oauth2-resource")
                    .redirectUris("http://anywhere?key=value").and()
                    .withClient("my-client-with-secret")
                    .authorizedGrantTypes("client_credentials", "password")
                    .authorities("ROLE_CLIENT").scopes("read")
                    .resourceIds("oauth2-resource").secret("secret");
            // @formatter:on
        }

        @Override
        public void configure(
                AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {


            // @formatter:off
            endpoints.tokenStore(tokenStore())
                    .authenticationManager(auth)
                    .tokenStore(tokenStore())
                    .pathMapping("/oauth/confirm_access", confirmPath)
                    .pathMapping("/oauth/token", tokenPath)
                    .pathMapping("/oauth/check_token", checkTokenPath)
                    .pathMapping("/oauth/token_key", tokenKeyPath)
                    .pathMapping("/oauth/authorize", authorizePath);
            // @formatter:on
        }

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }
        /*@Value("classpath:schema.sql")
        private Resource schemaScript;*/

        /*@Bean
        public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
            DataSourceInitializer initializer = new DataSourceInitializer();
            initializer.setDataSource(dataSource);
            initializer.setDatabasePopulator(databasePopulator());
            return initializer;
        }

        private DatabasePopulator databasePopulator() {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(schemaScript);
            return populator;
        }*/
    }
    @Configurable
    @EnableResourceServer
    protected class ResourceServerOAuth2Config extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore);
        }
    }


}