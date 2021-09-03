package com.mpv.rest_client_task;

import com.mpv.rest_client_task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {

    private RestTemplate restTemplate;
    private User user;
    private final String URL = "http://91.241.64.178:7081/api/users";
    private String cookie;
    private final StringBuilder stringBuilder = new StringBuilder();

    public void startCommunicate() {
        getEntities();
        createEntity();
        updateEntity();
        deleteEntity();
    }

    private void getEntities() {
        cookie = restTemplate
                .getForEntity(URL, String.class)
                .getHeaders()
                .getFirst(HttpHeaders.SET_COOKIE);
    }

    private void createEntity() {
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte)99);

        appendResult(restTemplate
                .exchange(URL, HttpMethod.POST, getEntity(), String.class));
    }

    private void updateEntity() {
        user.setName("Thomas");
        user.setLastName("Shelby");

        appendResult(restTemplate
                .exchange(URL, HttpMethod.PUT, getEntity(), String.class));
    }

    private void deleteEntity() {
        appendResult(restTemplate
                .exchange(URL + "/" + user.getId(), HttpMethod.DELETE, getEntity(), String.class));
    }

    private HttpEntity<User> getEntity() {
        return new HttpEntity<>(user, getHeaders());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        return headers;
    }

    private void appendResult(ResponseEntity<String> responseEntity) {
        stringBuilder.append(responseEntity.getBody());
    }

    public void showResult() {
        System.out.println(stringBuilder);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
