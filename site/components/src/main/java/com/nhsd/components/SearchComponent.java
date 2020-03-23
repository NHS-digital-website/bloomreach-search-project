package com.nhsd.components;

import com.microsoft.graph.models.extensions.User;
import com.nhsd.website.provider.GraphProvider;
import com.nhsd.website.storage.TempStorage;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.onehippo.cms7.essentials.components.EssentialsSearchComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchComponent extends CommonComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchComponent.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        LOGGER.info(getSearchQuery(request));

        User user = GraphProvider.getUser(TempStorage.getAccessToken().getToken());
        request.setAttribute("user", user.displayName);
    }

    protected String getSearchQuery(HstRequest request) {
        return cleanupSearchQuery(getAnyParameter(request, REQUEST_PARAM_QUERY));
    }
}
