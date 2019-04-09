package com.winning.devops.boot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot
 * @date: 2019-04-09 22:33
 *  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 *  为Web 测试环境的端口为随机端口的配置
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @LocalServerPort
    private int port;
    private URL base;
    /**
     * RestTemplate测试类
     * RestTemplate 用于远程调用 HttpAPI 接口
     *
     */
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void init() throws MalformedURLException {
        this.base = new URL("http://localhost:"+port+"/hi");
    }

    @Test
    public void testHi(){
        ResponseEntity<String> response = testRestTemplate.getForEntity(base.toString(),
                String.class);
        Assert.assertEquals(response.getBody(),"{\"status\":\"success\"}");
    }

    @Test
    public void testHealthEndpoints(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/monitor/health",
                String.class);
        System.out.println(response.getBody());
    }
}
