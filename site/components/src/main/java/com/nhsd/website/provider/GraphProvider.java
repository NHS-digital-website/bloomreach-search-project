package com.nhsd.website.provider;

import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IUserCollectionPage;
import com.nhsd.website.storage.TempStorage;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphProvider {

    private static IGraphServiceClient graphClient;

    private static void initializeGraphClient(final String accessToken) {
        if (graphClient == null) {
            HippoAuthenticationProvider authenticationProvider = new HippoAuthenticationProvider(accessToken);

            final DefaultLogger defaultLogger = new DefaultLogger();
            defaultLogger.setLoggingLevel(LoggerLevel.ERROR);

            graphClient = GraphServiceClient.builder()
                .authenticationProvider(authenticationProvider)
                .logger(defaultLogger)
                .buildClient();
        }
    }

    public static List<User> getUsers(final List<String> searchTerms) {
        initializeGraphClient(TempStorage.getAccessToken().getToken());

        final String firstNameFilter = getFirstNameFilter(searchTerms);
        final String lastNameFilter = getLastNameFilter(searchTerms);

        final StringBuilder stringBuilder = new StringBuilder();
        if (firstNameFilter != null) {
            stringBuilder.append(String.format("startsWith(givenName,'%s')", firstNameFilter));
            if (lastNameFilter != null) {
                stringBuilder.append(String.format(" and startsWith(surname,'%s')", lastNameFilter));
            }
        }
        final String filter = stringBuilder.toString();
        final List<Option> options = Collections.singletonList(new QueryOption("$filter", filter));

        IUserCollectionPage collectionPage = graphClient.users().buildRequest(options).get();
        return collectionPage.getCurrentPage();
    }

    private static String getLastNameFilter(List<String> searchTerms) {
        return searchTerms.size() != 2 ? null : searchTerms.get(1);
    }

    private static String getFirstNameFilter(List<String> searchTerms) {
        return searchTerms.isEmpty() ? null : searchTerms.get(0);
    }
}
