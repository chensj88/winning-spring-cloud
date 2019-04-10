package com.winning.devops.boot;

import com.alibaba.fastjson.JSON;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot
 * @date: 2019-04-10 16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JenkinsTest {

    @Autowired
    private JenkinsServer jenkinsServer;

    @Test
    public void testJenkinsJobs() throws IOException {
        Map<String, Job> jobs = jenkinsServer.getJobs();
        for (String s : jobs.keySet()) {
            System.out.println(s+":"+jobs.get(s));
        }
        JobWithDetails job = jobs.get("mbk-java").details();
        System.out.println(JSON.toJSONString(job));
    }
}
