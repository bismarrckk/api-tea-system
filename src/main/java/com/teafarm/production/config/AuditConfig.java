package com.teafarm.production.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
	@Bean
	public AuditorAware<String> auditorProvider() {
	    return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
	            .map(auth -> auth.getName());
	}
}

