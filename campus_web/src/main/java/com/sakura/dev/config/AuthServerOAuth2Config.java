package com.sakura.dev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Created by rc452 on 2017/5/28.
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config
        extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
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
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // @formatter:off
        clients.jdbc(dataSource)
                .withClient("teacher")
                .authorizedGrantTypes("password", "authorization_code",
                        "refresh_token", "implicit")
                .authorities("ROLE_TEACHER")
                .scopes("read", "write", "trust")
                //.resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(60)
                .and()
                .withClient("student")
                .authorizedGrantTypes("password", "authorization_code",
                        "refresh_token", "implicit")
                .authorities("ROLE_STUDENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(60);


        // @formatter:on
    }

    @Override
    public void configure(
            AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {


        // @formatter:off
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
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