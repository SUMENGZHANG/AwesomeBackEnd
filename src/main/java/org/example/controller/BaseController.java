package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
@Api(value = "BaseController")
public class BaseController {

    @RequestMapping(value="/",method= RequestMethod.GET)
    @ApiOperation(value = "", notes = "")
    public void index(){

    }

}
