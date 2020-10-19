package com.ibm.productservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.ibm.productservice.exception.CustomAccessDeniedHandler;
import com.ibm.productservice.exception.CustomAuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {
	 private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
	
    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;

    public OAuth2ResourceServerConfigJwt(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }
    @Override
    public void configure(final HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
    	http.authorizeRequests()
    	.antMatchers(AUTH_WHITELIST).permitAll()
    	.antMatchers("/console/**").permitAll()
        .antMatchers(HttpMethod.GET,"/console/**").permitAll()
        .antMatchers(HttpMethod.POST,"/console/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler());           
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);

        converter.setSigningKey("123");
        // final Resource resource = new ClassPathResource("public.txt");
        // String publicKey = null;
        // try {
        // publicKey = IOUtils.toString(resource.getInputStream());
        // } catch (final IOException e) {
        // throw new RuntimeException(e);
        // }
        // converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}
