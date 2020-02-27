package com.nhsd.components;

import com.nhsd.model.User;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service("com.nhsd.components.LdapSearchComponent")
public class LdapSearchComponent extends CommonComponent {

    private final LdapTemplate ldapTemplate;

    @Autowired
    public LdapSearchComponent(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        final String query = getSearchQuery(request);
        if (StringUtils.hasText(query)) {
            request.setAttribute("users", searchUserByName(query));
        }

    }

    /**
     * Fetches search query from request and cleans it
     *
     * @param request HstRequest
     * @return null if query was null or invalid
     */
    protected String getSearchQuery(HstRequest request) {
        return cleanupSearchQuery(getAnyParameter(request, REQUEST_PARAM_QUERY));
    }

    private List<User> searchUserByName(String userName) {
        return ldapTemplate.search(
                query()
                    .where("objectCategory")
                    .is("person")
                    .and(query()
                        .where("objectClass")
                        .is("user"))
                    .and(query().where("displayname").whitespaceWildcardsLike(userName)
                        .or(query().where("cn").is(userName))),
                (AttributesMapper<User>) attrs -> {
                    String name = (String) attrs.get("cn").get();
                    String email = (String) attrs.get("mail").get();
                    String displayName = (String) attrs.get("displayname").get();
                    return new User(name, displayName, email);
                });
    }
}
