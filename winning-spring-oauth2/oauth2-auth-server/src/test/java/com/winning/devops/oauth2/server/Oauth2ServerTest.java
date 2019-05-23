package com.winning.devops.oauth2.server;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.server
 * @date: 2019-05-23 23:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2ServerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testOauth2ByUserWithClient() throws Exception {
        String content = "{\"username\":\"user_1\",\"password\":\"123456\",\"grant_type\":\"password\",\"scope\":\"server\",\"client_id\":\"client_2\",\"client_secret\":\"123456\"}";
        String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/oauth/token")
                        .content(content).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
        /*
        * {
            "access_token": "ea775d2b-a724-4ae6-b9b5-baad2eec0f04",
            "token_type": "bearer",
            "refresh_token": "0a71f7f5-8206-42a1-9b41-718a1d8243ee",
            "expires_in": 41088,
            "scope": "server"
            }
        *
        *
        * */
    }
}
