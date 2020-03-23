package com.nhsd.website.provider;

import com.nhsd.website.json.User;
import com.nhsd.website.json.UsersResponse;
import com.nhsd.website.storage.TempStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class GraphProvider {

    private static final String v1BaseUrl = "https://graph.microsoft.com/v1.0/";
    private static final String betaBaseUrl = "https://graph.microsoft.com/beta/";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(GraphProvider.class);

    public static List<User> getUsers(final List<String> searchTerms) {

        final String firstNameFilter = getFirstNameFilter(searchTerms);
        final String lastNameFilter = getLastNameFilter(searchTerms);

        final StringBuilder stringBuilder = new StringBuilder("?$filter=");
        if (firstNameFilter != null) {
            stringBuilder.append(String.format("startsWith(givenName,'%s')", firstNameFilter));
            if (lastNameFilter != null) {
                stringBuilder.append(String.format(" and startsWith(surname,'%s')", lastNameFilter));
            }
        }
        final String queryFilter = stringBuilder.toString();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TempStorage.getAccessToken().getToken());

        HttpEntity<String> httpRequest = new HttpEntity<>(headers);

        final URI url = URI.create(v1BaseUrl + "users" + queryFilter);
        ResponseEntity<UsersResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpRequest, UsersResponse.class);

        log.info("Received response {}", responseEntity.getStatusCode().toString());
        if (responseEntity.getBody() == null) {
            return Collections.emptyList();
        } else {
            return responseEntity.getBody().getValue();
        }
    }

    public static byte[] getPhoto(final String userId) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        headers.setBearerAuth(TempStorage.getAccessToken().getToken());

        HttpEntity<String> httpRequest = new HttpEntity<>(headers);

        final URI url = URI.create(betaBaseUrl + "users/" + userId + "/photo/$value");
        try {
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpRequest, byte[].class);
            log.info("Received response {}", responseEntity.getStatusCode().toString());
            if (responseEntity.getBody() == null) {
                return new byte[] {};
            } else {
                return responseEntity.getBody();
            }
        } catch (final Exception e) {
            log.error(e.getMessage());
            return new byte[] {};
        }
    }

    private static String getLastNameFilter(List<String> searchTerms) {
        return searchTerms.size() != 2 ? null : searchTerms.get(1);
    }

    private static String getFirstNameFilter(List<String> searchTerms) {
        return searchTerms.isEmpty() ? null : searchTerms.get(0);
    }
}
