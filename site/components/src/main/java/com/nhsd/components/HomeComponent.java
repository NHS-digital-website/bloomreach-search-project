package com.nhsd.components;

import com.microsoft.graph.models.extensions.User;
import com.nhsd.website.exception.AuthenticationException;
import com.nhsd.website.provider.AuthenticationProvider;
import com.nhsd.website.provider.GraphProvider;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeComponent extends BaseHstComponent {

    private static final Logger log = LoggerFactory.getLogger(HomeComponent.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
        super.doBeforeRender(request, response);
        final String accessToken;
        try {
            accessToken = AuthenticationProvider.getAccessToken();
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            return;
        }

        User user = GraphProvider.getUser(accessToken);
        log.info("Welcome {}!", user.displayName);
    }
}
