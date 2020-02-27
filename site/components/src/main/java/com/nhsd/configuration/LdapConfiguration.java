package com.nhsd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();

        /*
        I found this to be a bit tricky, but got it working eventually. For example:
        if your AD url is adds.nhs.com, your AD domain is nhs.local, your username is test.user
        and your password is pa55word, you would have the following configuration (this assumes that the
        AD server runs on the default port)

        contextSource.setUrl("ldap://adds.nhs.com:389");
        contextSource.setBase("dc=nhs,dc=local");
        contextSource.setUserDn("test.user@nhs.local");
        contextSource.setPassword("pa55word");
         */

        /*
        In production, this will either be dedicated app credentials created by the admin, or,
        if Spring Security is used, you could set the username and password as the Principal user
        details. If the first option is chosen, all of these details should be stored in a properties file
         */
        contextSource.setUrl("ldap://<url>:389");
        contextSource.setBase("dc=<domain>,dc=<domain>");
        contextSource.setUserDn("<username>@<domain>");
        contextSource.setPassword("<password>");

        contextSource.afterPropertiesSet();

        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }
}
