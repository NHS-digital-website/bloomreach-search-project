package com.nhsd.website.provider;

import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;

public class GraphProvider {

    private static IGraphServiceClient graphClient;
    private static HippoAuthenticationProvider authenticationProvider;

    private static void initializeGraphClient(final String accessToken) {
        if (graphClient == null) {
            authenticationProvider = new HippoAuthenticationProvider(accessToken);

            final DefaultLogger defaultLogger = new DefaultLogger();
            defaultLogger.setLoggingLevel(LoggerLevel.ERROR);

            graphClient = GraphServiceClient.builder()
                .authenticationProvider(authenticationProvider)
                .logger(defaultLogger)
                .buildClient();
        }
    }

    public static User getUser(final String accessToken) {
        initializeGraphClient(accessToken);

        return graphClient.me().buildRequest().get();
    }
}
