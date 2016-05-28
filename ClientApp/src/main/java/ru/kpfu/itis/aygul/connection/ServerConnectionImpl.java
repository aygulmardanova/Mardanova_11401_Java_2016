package ru.kpfu.itis.aygul.connection;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.aygul.connection.interfaces.ServerConnection;
import ru.kpfu.itis.aygul.model.ClassClient;
import ru.kpfu.itis.aygul.model.UserClient;

import java.io.IOException;
import java.util.*;

/**
 * Connect to server and get necessary objects
 */
public class ServerConnectionImpl implements ServerConnection {

    private RestTemplate restTemplate;
    private String serverURL = "http://localhost:8080/rest/api";

    public ServerConnectionImpl() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(new MappingJacksonHttpMessageConverter()));
    }

    @Override
    public UserClient getUser(String login, String password) throws IOException {

        String url = serverURL + "/" + login;
        System.out.println(login + " " + password);

        UserClient user = restTemplate.getForObject(url, UserClient.class);
        ObjectMapper mapper = new ObjectMapper();
        byte[] json = mapper.writeValueAsBytes(user);
        UserClient userClient = mapper.readValue(json, new TypeReference<UserClient>(){});

        if (userClient != null && userClient.getPassword().equals(password)) {
            return userClient;
        } else {
            return null;
        }
    }

    @Override
    public List<ClassClient> getClasses() {
        String url = serverURL + "/classes";
        List<ClassClient> classClientList = restTemplate.getForObject(url, List.class);
        System.out.println("ClassClientList Server Conn: " + classClientList);
        return classClientList;
    }
}
