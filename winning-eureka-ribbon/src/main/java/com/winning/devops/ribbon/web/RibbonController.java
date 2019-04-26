package com.winning.devops.ribbon.web;

import com.winning.devops.ribbon.base.Constants;
import com.winning.devops.ribbon.services.RibbonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.ribbon.web
 * @date: 2019-04-26 16:29
 */
@RestController
public class RibbonController {

    @Autowired
    private RibbonServices ribbonServices;

    @GetMapping(value = "/hi/{username}")
    public Map<String, Object> hi(@PathVariable String username){
       Map<String, Object> resultMap = new HashMap<>(6);
       resultMap.put("status", Constants.SUCCESS);
       resultMap.put("data",ribbonServices.hi(username));
       return resultMap;
    }
}
