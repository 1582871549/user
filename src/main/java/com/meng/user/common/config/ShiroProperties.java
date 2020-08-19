package com.meng.user.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = ShiroProperties.PREFIX)
public class ShiroProperties {

    static final String PREFIX = "shiro";

    private final ShiroProperties.Password password = new ShiroProperties.Password();

    @Data
    public static class Password {

        private String hashAlgorithm = "md5";
        private int hashIterations = 2;
        private boolean storedCredentialsHexEncoded = true;

        @Override
        public String toString() {
            return "Password{" +
                    "hashAlgorithm='" + hashAlgorithm + '\'' +
                    ", hashIterations=" + hashIterations +
                    ", storedCredentialsHexEncoded=" + storedCredentialsHexEncoded +
                    '}';
        }
    }
}
