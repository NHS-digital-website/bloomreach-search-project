package com.nhsd.components;

import com.nhsd.website.json.User;
import com.nhsd.website.provider.GraphProvider;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchComponent extends CommonComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        final String searchQuery = getSearchQuery(request);
        final List<String> terms = StringUtils.hasText(searchQuery) ? Arrays.asList(searchQuery.split(" ")) : Collections.emptyList();
        List<User> users = GraphProvider.getUsers(terms);
        List<String> photos = users
            .stream()
            .map(user -> GraphProvider.getPhoto(user.getId()))
            .map(bytes -> Base64.getEncoder().encode(bytes))
            .map(String::new)
            .collect(Collectors.toList());
        request.setAttribute(REQUEST_PARAM_QUERY, searchQuery);
        request.setAttribute("users", users);
        request.setAttribute("photos", photos);
    }

    protected String getSearchQuery(HstRequest request) {
        return cleanupSearchQuery(getAnyParameter(request, REQUEST_PARAM_QUERY));
    }
}
