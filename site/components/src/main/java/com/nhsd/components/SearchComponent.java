package com.nhsd.components;

import com.microsoft.graph.models.extensions.User;
import com.nhsd.website.provider.GraphProvider;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchComponent extends CommonComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchComponent.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        final String searchQuery = getSearchQuery(request);
        final List<String> terms = StringUtils.hasText(searchQuery) ? Arrays.asList(searchQuery.split(" ")) : Collections.emptyList();
        List<User> users = GraphProvider.getUsers(terms);
        List<String> userNames = users.stream().map(user -> user.displayName).collect(Collectors.toList());
        request.setAttribute(REQUEST_PARAM_QUERY, searchQuery);
        request.setAttribute("users", userNames);
    }

    protected String getSearchQuery(HstRequest request) {
        return cleanupSearchQuery(getAnyParameter(request, REQUEST_PARAM_QUERY));
    }
}
