package com.example.config;

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

/**
 * @author Pasindu Lakmal
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }

}
