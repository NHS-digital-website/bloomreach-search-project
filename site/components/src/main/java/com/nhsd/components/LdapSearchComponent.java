package com.nhsd.components;

import com.nhsd.model.User;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service("com.nhsd.components.LdapSearchComponent")
public class LdapSearchComponent extends BaseHstComponent {

    private static final Logger log = LoggerFactory.getLogger(LdapSearchComponent.class);

    private final LdapTemplate ldapTemplate;

    @Autowired
    public LdapSearchComponent(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        request.setAttribute("users", getAllUsers());
    }

    public List<User> getAllUsers() {
        return ldapTemplate.search(
                query().where("objectCategory").is("person").and(query().where("objectClass").is("user")),
                (AttributesMapper<User>) attrs -> {
                    String name = (String) attrs.get("cn").get();
                    User user = new User();
                    user.setDisplayName(name);
                    user.setEmail("test");
                    return user;
                });
    }
}
