package com.nhsd.components;

import com.nhsd.website.config.Authentication;
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
        final String accessToken = Authentication.getUserAccessToken();

        log.info("Access token: {}", accessToken);
    }
}
