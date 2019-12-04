package de.brainactivit.empathica.kunde.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import de.brainactivit.empathica.kunde.security.AudienceValidator;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

  @Value("#{'${allowedAudiences}'.split(',')}")
  List<String> allowedAudiences;

  @Autowired
  public WebSecurityConfiguration(
          OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
      this.oAuth2ResourceServerProperties = oAuth2ResourceServerProperties;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .oauth2ResourceServer()
        .jwt();
  }

  @Bean
  JwtDecoder jwtDecoder() {
      NimbusJwtDecoder jwtDecoder =
              NimbusJwtDecoder.withJwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri())
                      .build();

      OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(allowedAudiences);

      OAuth2TokenValidator<Jwt> withIssuer =
              JwtValidators.createDefaultWithIssuer(
                      oAuth2ResourceServerProperties.getJwt().getIssuerUri());

      OAuth2TokenValidator<Jwt> withAudience =
              new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

      jwtDecoder.setJwtValidator(withAudience);

      return jwtDecoder;
  }
}