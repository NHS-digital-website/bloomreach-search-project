package com.nhsd.rest;

import com.nhsd.website.model.AccessToken;
import com.nhsd.website.model.TokenResponse;
import com.nhsd.website.storage.TempStorage;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;

@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/auth")
public class AuthenticationResource extends BaseRestResource {

    private static final String applicationId = "465121b4-cb68-47ea-94af-268de5585d75";
    private static final String clientSecret = "-nZ2IPilF/MSH@-w0QfRilij60Y93te3";
    private static final String authority = "https://login.microsoftonline.com/common/";
    private static final String redirectUri = "http://localhost:8080/site/rest/auth/response";
    private static final RestTemplate restTemplate = new RestTemplate();

    @GET
    @Path("/response")
    public String processResponse(
        @Context HttpServletResponse response,
        @QueryParam("code") final String code,
        @QueryParam("state") final String state) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", applicationId);
        map.add("scope", "https://graph.microsoft.com/user.readbasic.all");
        map.add("redirect_uri", redirectUri);
        map.add("grant_type", "authorization_code");
        map.add("client_secret", clientSecret);
        map.add("code", code);

        HttpEntity<MultiValueMap<String, String>> httpRequest = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> responseEntity = restTemplate.postForEntity(URI.create(authority + "oauth2/v2.0/token"), httpRequest, TokenResponse.class);

        TokenResponse tokenResponse = responseEntity.getBody();
        if (responseEntity.getStatusCode().is2xxSuccessful() && tokenResponse != null) {
            TempStorage.setAccessToken(new AccessToken(tokenResponse.getAccess_token(), tokenResponse.getRefresh_token(), tokenResponse.getExpires_in()));
        }

        try {
            response.sendRedirect("http://localhost:8080/site");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //TODO replace this stub to something useful
    }

}
