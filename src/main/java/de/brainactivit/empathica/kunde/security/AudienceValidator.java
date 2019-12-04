package de.brainactivit.empathica.kunde.security;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;

@Configuration
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  private List<String> allowedAudiences;

  public AudienceValidator(List<String> allowedAudiencesParameter) {
    allowedAudiences = allowedAudiencesParameter;
  }

  public OAuth2TokenValidatorResult validate(Jwt jwt) {

    if (CollectionUtils.containsAny(jwt.getAudience(), allowedAudiences))
      return OAuth2TokenValidatorResult.success();
    else
      return OAuth2TokenValidatorResult.failure(
        new OAuth2Error("invalid_token", "No allowed audience found in token", null));
  }
}
